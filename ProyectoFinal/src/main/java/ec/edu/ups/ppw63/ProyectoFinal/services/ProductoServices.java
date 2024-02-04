package ec.edu.ups.ppw63.ProyectoFinal.services;

import java.util.List;

import ec.edu.ups.ppw63.ProyectoFinal.business.GestionProductos;
import ec.edu.ups.ppw63.ProyectoFinal.model.Producto;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("productos")
public class ProductoServices {
	
	@Inject
	private GestionProductos gProductos;

	@GET
	@Produces(MediaType.APPLICATION_JSON) //Devuelve en formato JSON
	//@Produces("application/json")
	public Response leer(@QueryParam("codigo") int codigo, @QueryParam("nombre") String nombre) 
	{
		try 
		{
			System.out.println("nom "+nombre);
			Producto prod = gProductos.getProductoPorCodigo(codigo);
			return Response.ok(prod).build();
		}
		catch (Exception e) 
		{
			ErrorMessage error = new ErrorMessage(4, "Producto no existe");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@GET
	@Path("{codigo}/{nombre}")
	@Produces(MediaType.APPLICATION_JSON) //Devuelve en formato JSON
	//@Produces("application/json")
	public Response leer2(@PathParam("codigo") int codigo, @PathParam("nombre") String nombre)  
	{
		try 
		{
			System.out.println("nom "+nombre);
			Producto prod = gProductos.getProductoPorCodigo(codigo);
			return Response.ok(prod).build();
		}
		catch (Exception e) 
		{
			ErrorMessage error = new ErrorMessage(4, "Producto no existe");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getProductos(){
		List<Producto> productos = gProductos.getProductos();
		if(productos.size()>0)
			return Response.ok(productos).build();
		
		ErrorMessage error = new ErrorMessage(6, "No se registran productos");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(error)
				.build();
		
	}
}
