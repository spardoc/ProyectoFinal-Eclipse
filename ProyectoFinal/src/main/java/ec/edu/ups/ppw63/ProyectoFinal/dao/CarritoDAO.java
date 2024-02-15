package ec.edu.ups.ppw63.ProyectoFinal.dao;

import ec.edu.ups.ppw63.ProyectoFinal.model.Carrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleCarrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.Producto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class CarritoDAO {
    @PersistenceContext
    private EntityManager em;

    public Carrito insert(Carrito carrito) {
        // Aquí ya no necesitas buscar el cliente, se asume que carrito ya lo tiene asociado.
        em.persist(carrito);
        return carrito;
    }


    
    public void update(Carrito carrito) {
        // Al actualizar, asegúrate de que cualquier cambio en la relación con el cliente
        // se maneje adecuadamente.
        if (carrito.getCliente() != null) {
            carrito.setCliente(em.merge(carrito.getCliente()));
        }

        em.merge(carrito);
    }

    public Carrito read(int codigo) {
        return em.find(Carrito.class, codigo);
    }

    public void remove(int codigo) {
        Carrito carrito = em.find(Carrito.class, codigo);
        em.remove(carrito);
    }

    public List<Carrito> getAll() {
        String jpql = "SELECT c FROM Carrito c";
        Query q = em.createQuery(jpql, Carrito.class);
        return q.getResultList();
    }

    public void agregarDetalleCarrito(DetalleCarrito detalle) {
        // Se asume que 'detalle' contiene solo el 'codigo' del producto.
        if (detalle.getProducto() != null && detalle.getProducto().getCodigo() > 0) {
            Producto producto = em.find(Producto.class, detalle.getProducto().getCodigo());
            if (producto == null) {
                throw new EntityNotFoundException("Producto con código " + detalle.getProducto().getCodigo() + " no encontrado.");
            }
            detalle.setProducto(producto); // Asigna el producto recuperado al detalle
        }

        // Aquí vendría el resto de la lógica para persistir el DetalleCarrito
        em.persist(detalle);
    }


    public void removerDetalleCarrito(int codigoDetalle) {
        DetalleCarrito detalle = em.find(DetalleCarrito.class, codigoDetalle);
        if (detalle != null) {
            Carrito carrito = detalle.getCarrito();
            if (carrito != null) {
                carrito.getDetalles().remove(detalle); // Desvincular el detalle del carrito
                em.merge(carrito); // Guardar el cambio en el carrito
            }
            em.remove(detalle); // Ahora eliminar el detalle
        }
    }
    
    public Carrito obtenerCarritoPorCliente(int codigoCliente) {
        try {
            String jpql = "SELECT c FROM Carrito c WHERE c.cliente.codigo = :codigoCliente";
            Query query = em.createQuery(jpql, Carrito.class);
            query.setParameter("codigoCliente", codigoCliente);
            return (Carrito) query.getSingleResult();
        } catch (NoResultException e) {
            return null; // No existe carrito para este cliente
        }
    }
    
}
