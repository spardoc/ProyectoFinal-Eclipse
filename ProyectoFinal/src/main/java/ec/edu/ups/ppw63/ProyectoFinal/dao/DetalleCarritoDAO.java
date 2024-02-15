package ec.edu.ups.ppw63.ProyectoFinal.dao;

import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleCarrito;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class DetalleCarritoDAO {
	
    @PersistenceContext
    private EntityManager em;

    public void insert(DetalleCarrito detalle) {
        em.persist(detalle);
    }

    public void update(DetalleCarrito detalle) {
        em.merge(detalle);
    }

    public DetalleCarrito read(int codigo) {
        return em.find(DetalleCarrito.class, codigo);
    }

    public void remove(int codigo) {
        DetalleCarrito detalle = read(codigo);
        if (detalle != null) {
            em.remove(detalle);
        }
    }

    public List<DetalleCarrito> getAll() {
        String jpql = "SELECT d FROM DetalleCarrito d";
        Query q = em.createQuery(jpql, DetalleCarrito.class);
        return q.getResultList();
    }

    public List<DetalleCarrito> obtenerDetallesPorCarrito(int codigoCarrito) {
        String jpql = "SELECT d FROM DetalleCarrito d WHERE d.carrito.codigo = :codigoCarrito";
        Query q = em.createQuery(jpql, DetalleCarrito.class);
        q.setParameter("codigoCarrito", codigoCarrito);
        return q.getResultList();
    }
}
