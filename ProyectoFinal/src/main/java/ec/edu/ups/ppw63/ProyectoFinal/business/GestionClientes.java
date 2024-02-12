package ec.edu.ups.ppw63.ProyectoFinal.business;

import java.util.List;
import ec.edu.ups.ppw63.ProyectoFinal.dao.ClienteDAO;
import ec.edu.ups.ppw63.ProyectoFinal.model.Cliente;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;


@Stateless
public class GestionClientes 
{
	@Inject
	private ClienteDAO daoCliente;
	
	public void guardarClientes(Cliente cliente) 
	{
		Cliente cli = daoCliente.read(cliente.getCodigo());
		if (cli != null) 
		{
			daoCliente.update(cliente);
		}
		else 
		{
			daoCliente.insert(cliente);
		}
	}
	
	public Cliente verificarCredenciales(String correo, String clave) throws Exception {
        Cliente cliente = daoCliente.getClientePorCorreo(correo);

        if (cliente != null && cliente.getClave().equals(clave)) {
            return cliente;
        } else {
            throw new Exception("Correo o contraseña incorrecta");
        }
    }
	
	public boolean verificarClave(String clave) throws Exception {
		boolean validado = daoCliente.verificarClave(clave);
		if (validado) {
	        return true;
	    }
	    else 
	    {
	    	throw new Exception("Correo incorrecto");
	    }
    }
	
	public void actualizarCliente(Cliente cliente) throws Exception 
	{
		Cliente cli = daoCliente.read(cliente.getCodigo());
		if (cli != null) 
		{
			daoCliente.update(cliente);
		}
		else 
		{
			throw new Exception("Cliente no existe");
		}
	}
	
	public Cliente getClientePorCedula(String cedula) throws Exception 
	{
		if(cedula.length() != 10) 
		{
			throw new Exception("Cedula incorrecta");
		}
		return daoCliente.getClientePorCedula(cedula);
	}
	
	public void borrarCliente(int codigo) 
	{
		daoCliente.remove(codigo);
	}
	
	public List<Cliente> getClientes()
	{
		return daoCliente.getAll();
	}
}
