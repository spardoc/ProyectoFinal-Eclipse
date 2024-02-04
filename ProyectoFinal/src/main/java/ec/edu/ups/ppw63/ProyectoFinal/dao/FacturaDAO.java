
package ec.edu.ups.ppw63.ProyectoFinal.dao;

import java.util.List;

import ec.edu.ups.ppw63.ProyectoFinal.model.Cliente;
import ec.edu.ups.ppw63.ProyectoFinal.model.Factura;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class FacturaDAO 
{
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Factura factura) 
	{
		em.persist(factura);
	}
	
	public void update(Factura factura) 
	{
		em.merge(factura);
	}
	
	public void remove(int codigo) 
	{
		Factura factura = em.find(Factura.class, codigo);
	}
	
	public Factura read(int codigo) 
	{
		Factura factura = em.find(Factura.class, codigo);
		return factura;
	}
	
	public List<Factura> getAll()
	{
		String jpql = "SELECT f FROM Factura f"; //Sentencias a las entidades
		Query q = em.createQuery(jpql, Factura.class);
		return q.getResultList();
	}
	
	public Factura getFacturaPorNumero(String numero) 
	{
		String jpql = "SELECT f FROM Factura f WHERE f.numero = :numero"; //Sentencias a las entidades
		Query q = em.createQuery(jpql, Factura.class);
		q.setParameter("numero", numero);
		List<Factura> facturas = q.getResultList();
		if(facturas.size() > 0) 
		{
			return facturas.get(0);
		}
		return null;
	}
	
}
