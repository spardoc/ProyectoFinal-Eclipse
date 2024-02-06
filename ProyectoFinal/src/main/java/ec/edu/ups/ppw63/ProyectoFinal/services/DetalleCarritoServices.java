package ec.edu.ups.ppw63.ProyectoFinal.services;

import ec.edu.ups.ppw63.ProyectoFinal.business.GestionDetallesCarrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleCarrito;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/detallesCarrito")
public class DetalleCarritoServices {

    @Inject
    private GestionDetallesCarrito gestionDetallesCarrito;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarDetalle(DetalleCarrito detalle) {
        gestionDetallesCarrito.agregarDetalle(detalle);
        return Response.ok(detalle).build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDetalle(@PathParam("codigo") int codigo) {
        DetalleCarrito detalle = gestionDetallesCarrito.obtenerDetalle(codigo);
        if (detalle != null) {
            return Response.ok(detalle).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Puedes añadir más métodos según sea necesario
}
