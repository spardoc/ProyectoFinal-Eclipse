package ec.edu.ups.ppw63.ProyectoFinal.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ec.edu.ups.ppw63.ProyectoFinal.business.GestionCarrito;
import ec.edu.ups.ppw63.ProyectoFinal.business.GestionClientes;
import ec.edu.ups.ppw63.ProyectoFinal.dto.ClienteDTO;
import ec.edu.ups.ppw63.ProyectoFinal.model.Carrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.Cliente;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("clientes")
public class ClienteServices 
{
	@Inject
	private GestionClientes gClientes;
	
	@Inject
	private GestionCarrito gCarritos;
	
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response crear(Cliente cliente) {
		    try {
		        gClientes.guardarClientes(cliente);
		        ClienteDTO clienteDTO = new ClienteDTO(cliente); // Convierte a DTO
		        return Response.ok(clienteDTO).build(); // Devuelve el DTO
		    } catch (Exception e) {
		        ErrorMessage error = new ErrorMessage(99, e.getMessage());
		        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
		                .entity(error)
		                .build();
		    }
		}

	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	public Response verificarCliente(@QueryParam("correo") String correo, @QueryParam("clave") String clave) {
	    try {
	        Cliente cliente = gClientes.verificarCredenciales(correo, clave);
	        if (cliente != null) {
	            Carrito carrito = gCarritos.obtenerCarritoPorCliente(cliente.getCodigo());

	            // Crear un mapa para la respuesta
	            Map<String, Object> respuesta = new HashMap<>();
	            respuesta.put("mensaje", "Acceso concedido");
	            respuesta.put("codigoCarrito", carrito.getCodigo());

	            return Response.ok(respuesta).build();
	        } else {
	            ErrorMessage em = new ErrorMessage(4, "Correo o contraseña incorrecta");
	            return Response.status(Response.Status.UNAUTHORIZED).entity(em).build();
	        }
	    } catch (Exception e) {
	        ErrorMessage em = new ErrorMessage(4, "Correo o contraseña incorrecta");
	        return Response.status(Response.Status.UNAUTHORIZED).entity(em).build();
	    }
	}
		 
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Cliente cliente) 
	{
		try 
		{
			gClientes.actualizarCliente(cliente);
			return Response.ok(cliente).build();
		}
		catch (Exception e) 
		{
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("id") int codigo) 
	{
		try 
		{
			gClientes.borrarCliente(codigo);
			return "OK";
		}
		catch (Exception e) 
		{
			return "ERROR";
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) //Devuelve en formato JSON
	//@Produces("application/json")
	public Response leer(@QueryParam("dni") String cedula, @QueryParam("nombre") String nombre) 
	{
		try 
		{
			System.out.println("cedula "+cedula+"nom "+nombre);
			Cliente cli = gClientes.getClientePorCedula(cedula);
			return Response.ok(cli).build();
		}
		catch (Exception e) 
		{
			ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@GET
	@Path("{dni}/{nombre}")
	@Produces(MediaType.APPLICATION_JSON) //Devuelve en formato JSON
	//@Produces("application/json")
	public Response leer2(@PathParam("dni") String cedula, @PathParam("nombre") String nombre) 
	{
		try 
		{
			System.out.println("cedula "+cedula+"nom "+nombre);
			Cliente cli = gClientes.getClientePorCedula(cedula);
			return Response.ok(cli).build();
		}
		catch (Exception e) 
		{
			ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getClientes(){
		List<Cliente> clientes = gClientes.getClientes();
		List<ClienteDTO> clientesDTO = clientes.stream()
                .map(ClienteDTO::new) // Convierte cada Cliente a ClienteDTO
                .collect(Collectors.toList());
		if((!clientesDTO.isEmpty()))
			return Response.ok(clientesDTO).build();
		ErrorMessage error = new ErrorMessage(6, "No se registran clientes");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(error)
				.build();
		
	}
}
