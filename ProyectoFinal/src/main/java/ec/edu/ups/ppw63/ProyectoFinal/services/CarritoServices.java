package ec.edu.ups.ppw63.ProyectoFinal.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ec.edu.ups.ppw63.ProyectoFinal.business.GestionCarrito;
import ec.edu.ups.ppw63.ProyectoFinal.business.GestionDetallesCarrito;
import ec.edu.ups.ppw63.ProyectoFinal.dto.CarritoDTO;
import ec.edu.ups.ppw63.ProyectoFinal.dto.DetalleCarritoDTO;
import ec.edu.ups.ppw63.ProyectoFinal.model.Carrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleCarrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.Producto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("carritos")
public class CarritoServices {

    @Inject
    private GestionCarrito gestionCarrito;
    
    @Inject
    private GestionDetallesCarrito gestionDetallesCarrito;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getCarritos() {
        List<Carrito> carritos = gestionCarrito.getCarritos();
        List<CarritoDTO> carritosDTO = carritos.stream()
                                               .map(CarritoDTO::new) // Utiliza el constructor de CarritoDTO
                                               .collect(Collectors.toList());

        if (!carritosDTO.isEmpty()) {
            return Response.ok(carritosDTO).build();
        } else {
            ErrorMessage error = new ErrorMessage(6, "No se registran carritos en la base");
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(error)
                           .build();
        }
    }
    
    @POST
    @Path("/{codigoCarrito}/productos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarProductoAlCarrito(@PathParam("codigoCarrito") int codigoCarrito, DetalleCarritoDTO detalleDTO) {
        // Verificar si el carrito existe
        Carrito carrito = gestionCarrito.obtenerCarrito(codigoCarrito); // Asumiendo que este método existe
        if (carrito == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Carrito no encontrado").build();
        }

        // Verificar si el producto existe
        Producto producto = gestionDetallesCarrito.obtenerProductoCompleto(detalleDTO.getProducto().getCodigo());
        if (producto == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Producto no encontrado").build();
        }

        // Convertir el DTO a entidad y agregar el producto al carrito
        DetalleCarrito detalle = detalleDTO.toEntity();
        detalle.setCarrito(carrito);
        detalle.setProducto(producto);
        
        gestionDetallesCarrito.agregarDetalle(detalle);
        Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("mensaje", "Producto agregado al carrito");
		respuesta.put("codigoDetalle", detalle.getCodigo()); // Devuelve el código del detalle
		return Response.status(Response.Status.OK).entity(respuesta).build();
    }

    @GET
    @Path("/{codigoCliente}/carrito")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCarritoCliente(@PathParam("codigoCliente") int codigoCliente) {
        Carrito carrito = gestionCarrito.obtenerCarritoPorCliente(codigoCliente);
        if (carrito == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Carrito no encontrado").build();
        }

        // Aquí asumimos que tienes una forma de convertir tu carrito y detalles a un DTO
        CarritoDTO carritoDTO = new CarritoDTO(carrito);
        return Response.ok(carritoDTO).build();
    }
    
    @DELETE
    @Path("/borrar/{codigoDetalleCarrito}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrar(@PathParam("codigoDetalleCarrito") int codigoDetalleCarrito) 
	{
		try 
		{
			gestionCarrito.eliminarDetalleCarrito(codigoDetalleCarrito);
			Map<String, String> respuesta = new HashMap<>();
	        respuesta.put("mensaje", "Detalle carrito eliminado correctamente");
	        
	        return Response.status(Response.Status.OK).entity(respuesta).build();
		}
		catch (Exception e) 
		{
			ErrorMessage error = new ErrorMessage(6, "No se pudo eliminar el detalle o no existe");
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(error)
                           .build();
		}
	}
}