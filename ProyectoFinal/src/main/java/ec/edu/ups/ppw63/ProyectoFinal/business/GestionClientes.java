package ec.edu.ups.ppw63.ProyectoFinal.business;

import java.util.List;
import ec.edu.ups.ppw63.ProyectoFinal.dao.ClienteDAO;
import ec.edu.ups.ppw63.ProyectoFinal.model.Cliente;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


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
			cliente.setClave(hashPassword(cliente.getClave()));
			daoCliente.insert(cliente);
		}
	}
	
	public boolean verificarCorreo(String correo) throws Exception {
	    boolean validado = daoCliente.verificarCorreo(correo);
	    if (validado) {
	        return true;
	    }
	    else 
	    {
	    	throw new Exception("Correo incorrecto");
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

	private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
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
