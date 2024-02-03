package ec.edu.ups.ppw63.ProyectoFinal.dao;

import java.util.List;
import ec.edu.ups.ppw63.ProyectoFinal.model.Carrito;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class CarritoDAO {
    @PersistenceContext
    private EntityManager em;

    public void insert(Carrito carrito) {
        em.persist(carrito);
    }

    public void update(Carrito carrito) {
        em.merge(carrito);
    }

    public void remove(int codigo) {
        Carrito carrito = em.find(Carrito.class, codigo);
        em.remove(carrito);
    }

    public Carrito read(int codigo) {
        return em.find(Carrito.class, codigo);
    }

    public List<Carrito> getAll() {
        String jpql = "SELECT c FROM Carrito c";
        Query q = em.createQuery(jpql, Carrito.class);
        return q.getResultList();
    }
}
