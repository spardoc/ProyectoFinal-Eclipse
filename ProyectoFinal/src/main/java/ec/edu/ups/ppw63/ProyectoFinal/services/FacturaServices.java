package ec.edu.ups.ppw63.ProyectoFinal.services;
import ec.edu.ups.ppw63.ProyectoFinal.business.GestionCarrito;
import ec.edu.ups.ppw63.ProyectoFinal.business.GestionFacturas;
import ec.edu.ups.ppw63.ProyectoFinal.dto.FacturaDTO;
import ec.edu.ups.ppw63.ProyectoFinal.model.Carrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.Factura;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Stateless
@Path("/facturas")
public class FacturaServices {

    @Inject
    private GestionFacturas gestionFacturas;

    @Inject
    private GestionCarrito gestionCarrito;

    @POST
    @Path("/generar/{codigoCarrito}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generarFactura(@PathParam("codigoCarrito") int codigoCarrito) {
        try {
        	Carrito carrito = gestionCarrito.obtenerCarrito(codigoCarrito);
            if (carrito == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Carrito no encontrado").build();
            }
            
            Factura factura = gestionFacturas.guardarFacturaDesdeCarrito(carrito);
            
//            gestionFacturas.guardarFactura(factura);

            carrito.vaciarCarrito();
            gestionCarrito.actualizarCarrito(carrito);
            // Suponiendo que tienes un método para convertir la factura a un DTO
            FacturaDTO facturaDTO = new FacturaDTO(factura);

            return Response.ok(facturaDTO).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
