
package ec.edu.ups.ppw63.ProyectoFinal.dao;

import java.util.List;

import ec.edu.ups.ppw63.ProyectoFinal.model.Producto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class ProductoDAO {
    @PersistenceContext
    private EntityManager em;

    public void insert(Producto producto) {
        em.persist(producto);
    }

    public void update(Producto producto) {
        em.merge(producto);
    }

    public void remove(int codigo) {
        Producto producto = em.find(Producto.class, codigo);
        em.remove(producto);
    }

    public Producto read(int codigo) {
        return em.find(Producto.class, codigo);
    }

    public List<Producto> getAll() {
        String jpql = "SELECT p FROM Producto p";
        Query q = em.createQuery(jpql, Producto.class);
        return q.getResultList();
    }
    
    public Producto getProductoPorCodigo(int codigo) 
	{
		String jpql = "SELECT p FROM Producto p WHERE p.codigo = :codigo"; //Sentencias a las entidades
		Query q = em.createQuery(jpql, Producto.class);
		q.setParameter("codigo", codigo);
		List<Producto> productos = q.getResultList();
		if(productos.size() > 0) 
		{
			return productos.get(0);
		}
		return null;
	}
}

