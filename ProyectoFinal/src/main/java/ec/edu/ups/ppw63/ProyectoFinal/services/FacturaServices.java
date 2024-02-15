package ec.edu.ups.ppw63.ProyectoFinal.services;
import java.io.FileNotFoundException;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import ec.edu.ups.ppw63.ProyectoFinal.business.GestionCarrito;
import ec.edu.ups.ppw63.ProyectoFinal.business.GestionFacturas;
import ec.edu.ups.ppw63.ProyectoFinal.business.GestionProductos;
import ec.edu.ups.ppw63.ProyectoFinal.dto.FacturaDTO;
import ec.edu.ups.ppw63.ProyectoFinal.model.Carrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.Factura;
import ec.edu.ups.ppw63.ProyectoFinal.model.Producto;
import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleCarrito;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

@Stateless
@Path("/facturas")
public class FacturaServices {

    @Inject
    private GestionFacturas gestionFacturas;

    @Inject
    private GestionCarrito gestionCarrito;
    
    @Inject
    private GestionProductos gestionProductos;

    @POST
    @Path("/generar/{codigoCarrito}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generarFactura(@PathParam("codigoCarrito") int codigoCarrito) {
        try {
            Carrito carrito = gestionCarrito.obtenerCarrito(codigoCarrito);
            if (carrito == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Carrito no encontrado").build();
            }

         // Disminuir el stock del producto
            for (DetalleCarrito detalle : carrito.getDetalles()) {
                Producto producto = detalle.getProducto();
                int cantidadComprada = detalle.getCantidad();
                producto.setStock(producto.getStock() - cantidadComprada); // Asumiendo que Producto tiene un método setStock
                gestionProductos.actualizarProducto(producto); // Asumiendo que tienes un método para actualizar productos
            }
            
            Factura factura = gestionFacturas.guardarFacturaDesdeCarrito(carrito);

            carrito.vaciarCarrito();
            gestionCarrito.actualizarCarrito(carrito);
            FacturaDTO facturaDTO = new FacturaDTO(factura);

            // Generar el PDF
            String dest = "C:\\Users\\ADMIN\\OneDrive\\Desktop\\factura_" + factura.getNumero() + ".pdf"; 
            generarPDF(dest, factura);

            // Enviar el PDF por correo
            String correoDestino = factura.getCliente().getCorreo(); // Asegúrate de tener el correo del cliente
            enviarCorreoConPDF(correoDestino, dest);

            return Response.ok(facturaDTO).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    
    public void generarPDF(String dest, Factura factura) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Factura N°: " + factura.getNumero()));
        document.add(new Paragraph("Fecha: " + factura.getFechaEmision()));
        document.add(new Paragraph("Cliente: " + factura.getCliente().getNombre()));
        document.add(new Paragraph(" "));

        // Crear una tabla para los detalles de los productos
        float[] columnWidths = {1, 2, 1, 1, 1, 1, 1}; // Ajusta el ancho de las columnas según necesites
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();

        // Agregar cabeceras a la tabla
        table.addHeaderCell("Código");
        table.addHeaderCell("Producto");
        table.addHeaderCell("Cantidad");
        table.addHeaderCell("Talla");
        table.addHeaderCell("Precio Unitario");
        table.addHeaderCell("Subtotal");
        table.addHeaderCell("IVA (%)");

        // Agregar los detalles de cada producto a la tabla
        factura.getDetalles().forEach(detalle -> {
            table.addCell(String.valueOf(detalle.getCodigo()));
            table.addCell(detalle.getProductos().stream().findFirst().orElse(new Producto()).getNombre()); // Ajusta esta línea si la forma de obtener el nombre del producto es diferente
            table.addCell(String.valueOf(detalle.getCantidad()));
            table.addCell(detalle.getTalla());
            table.addCell(String.format("$%.2f", detalle.getPrecio()));
            table.addCell(String.format("$%.2f", detalle.getSubtotal()));
            table.addCell(String.valueOf(detalle.getIva()) + "%");
        });

        document.add(table);

        // Agregar el total de la factura al final
        document.add(new Paragraph("Total Factura: $" + factura.getTotal()));

        document.close();
    }
	
    public void enviarCorreoConPDF(String destino, String rutaPDF) {
        final String username = "samuelpardo1997@gmail.com"; // Reemplaza esto con tu correo de Gmail
        final String password = "arrf ybqu iawm resg"; // Reemplaza esto con tu contraseña o contraseña de aplicación de Gmail

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("samuelpardo1997@gmail.com")); // Reemplaza esto con tu correo de Gmail
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
            message.setSubject("Factura TIENDA GALLITO");

            // Adjunta el PDF
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.attachFile(rutaPDF);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Correo enviado exitosamente");

        } catch (MessagingException | java.io.IOException e) {
            e.printStackTrace();
        }
    }


}
