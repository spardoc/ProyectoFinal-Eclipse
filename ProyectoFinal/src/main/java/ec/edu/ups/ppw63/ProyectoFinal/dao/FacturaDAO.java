
package ec.edu.ups.ppw63.ProyectoFinal.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ec.edu.ups.ppw63.ProyectoFinal.model.Carrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.Cliente;
import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleFactura;
import ec.edu.ups.ppw63.ProyectoFinal.model.Factura;
import ec.edu.ups.ppw63.ProyectoFinal.model.Producto;
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
	
	public Factura crearYGuardarFacturaDesdeCarrito(Carrito carrito, double porcentajeIVA) {
        Factura factura = new Factura();
        factura.setCliente(carrito.getCliente());
        factura.setFechaEmision(new Date());

        double totalFactura = 0;
        List<DetalleFactura> detallesFactura = new ArrayList<>();

        for (DetalleCarrito detalleCarrito : carrito.getDetalles()) {
            DetalleFactura detalleFactura = new DetalleFactura();
            detalleFactura.setCantidad(detalleCarrito.getCantidad());
            detalleFactura.setPrecio(detalleCarrito.getProducto().getPrecio());
            detalleFactura.setIva(porcentajeIVA);
            detalleFactura.setProductos(Arrays.asList(detalleCarrito.getProducto()));
            detalleFactura.setFactura(factura);

            double subtotal = detalleFactura.getCantidad() * detalleFactura.getPrecio();
            double totalDetalle = subtotal + (subtotal * detalleFactura.getIva() / 100);
            totalFactura += totalDetalle;

            detallesFactura.add(detalleFactura);
        }

        factura.setTotal(totalFactura);
        factura.setDetalles(detallesFactura);

        em.persist(factura);
        detallesFactura.forEach(em::persist);

        // Opcional: Vaciar el carrito después de generar la factura
        carrito.vaciarCarrito();
        em.merge(carrito);

        return factura;
    }
}
