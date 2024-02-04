
package ec.edu.ups.ppw63.ProyectoFinal.dao;

import java.util.List;

import ec.edu.ups.ppw63.ProyectoFinal.model.Cliente;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class ClienteDAO 
{
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Cliente cliente) 
	{
		em.persist(cliente);
	}
	
	public void update(Cliente cliente) 
	{
		em.merge(cliente);
	}
	
	public void remove(int codigo) 
	{
		Cliente cliente = em.find(Cliente.class, codigo);
		em.remove(cliente);
	}
	
	public Cliente read(int codigo) 
	{
		Cliente cliente = em.find(Cliente.class, codigo);
		return cliente;
	}
	
	public List<Cliente> getAll()
	{
		String jpql = "SELECT c FROM Cliente c"; //Sentencias a las entidades
		Query q = em.createQuery(jpql, Cliente.class);
		return q.getResultList();
	}
	
	public Cliente getClientePorCedula(String cedula) 
	{
		String jpql = "SELECT c FROM Cliente c WHERE c.dni = :cedula"; //Sentencias a las entidades
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter("cedula", cedula);
		List<Cliente> clientes = q.getResultList();
		if(clientes.size() > 0) 
		{
			return clientes.get(0);
		}
		return null;
	}
	
	public Cliente getClientePorCorreo(String correo) 
	{
		String jpql = "SELECT c FROM Cliente c WHERE c.correo = :correo"; //Sentencias a las entidades
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter("correo", correo);
		List<Cliente> clientes = q.getResultList();
		if(clientes.size() > 0) 
		{
			return clientes.get(0);
		}
		return null;
	}
	
	public boolean verificarCorreo(String correo) {
	    String jpql = "SELECT c FROM Cliente c WHERE c.correo = :correo";
	    Query q = em.createQuery(jpql, Cliente.class).setParameter("correo", correo);
	    try {
	        Cliente cliente = (Cliente) q.getSingleResult();
	        // Si el cliente se encuentra, devuelve true
	        return true;
	    } catch (NoResultException e) {
	        // Si no hay resultado, devuelve false
	        return false;
	    }
	}

	
	public boolean verificarClave(String clave) {
		String jpql = "SELECT c FROM Cliente c WHERE c.clave = :clave";
	    Query q = em.createQuery(jpql, Cliente.class).setParameter("clave", clave);
	    try {
	        Cliente cliente = (Cliente) q.getSingleResult();
	        // Si el cliente se encuentra, devuelve true
	        return true;
	    } catch (NoResultException e) {
	        // Si no hay resultado, devuelve false
	        return false;
	    }
	}
}
