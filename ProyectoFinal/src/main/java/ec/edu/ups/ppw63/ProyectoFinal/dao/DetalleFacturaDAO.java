
package ec.edu.ups.ppw63.ProyectoFinal.dao;

import java.util.List;
import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleFactura;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class DetalleFacturaDAO {
    @PersistenceContext
    private EntityManager em;

    public void insert(DetalleFactura detalleFactura) {
        em.persist(detalleFactura);
    }

    public void update(DetalleFactura detalleFactura) {
        em.merge(detalleFactura);
    }

    public void remove(int codigo) {
        DetalleFactura detalleFactura = em.find(DetalleFactura.class, codigo);
        em.remove(detalleFactura);
    }

    public DetalleFactura read(int codigo) {
        return em.find(DetalleFactura.class, codigo);
    }

    public List<DetalleFactura> getAll() {
        String jpql = "SELECT d FROM DetalleFactura d";
        Query q = em.createQuery(jpql, DetalleFactura.class);
        return q.getResultList();
    }
    
    
}
