
package ec.edu.ups.ppw63.ProyectoFinal.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ec.edu.ups.ppw63.ProyectoFinal.model.Carrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleCarrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleFactura;
import ec.edu.ups.ppw63.ProyectoFinal.model.Factura;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.Random;

@Stateless
public class FacturaDAO 
{
	@PersistenceContext
	private EntityManager em;
	
	private static final Random random = new Random();
	
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
	
	public Factura crearYGuardarFacturaDesdeCarrito(Carrito carrito) {
        final int porcentajeIVA = 12; // IVA del 12%

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
            detalleFactura.setTalla(detalleCarrito.getTalla());

            double subtotal = detalleFactura.getCantidad() * detalleFactura.getPrecio();
            double totalDetalle = subtotal + (subtotal * porcentajeIVA / 100.0); // Asegúrate de dividir entre 100.0 para obtener el porcentaje
            totalFactura += totalDetalle;

            detallesFactura.add(detalleFactura);
            detalleFactura.setSubtotal(subtotal);
            detalleFactura.setTotal(totalDetalle);
        }

        factura.setTotal(totalFactura);
        factura.setDetalles(detallesFactura);
        factura.setNumero(generarNumeroFactura());

        em.persist(factura);
        detallesFactura.forEach(em::persist);

        // Opcional: Vaciar el carrito después de generar la factura
        carrito.vaciarCarrito();
        em.merge(carrito);

        return factura;
    }
	
	private String generarNumeroFactura() {
        // Generar cada parte del número de factura de manera aleatoria
        int parte1 = random.nextInt(10000); // Número entre 0 y 9999
        int parte2 = random.nextInt(1000);  // Número entre 0 y 999
        int parte3 = random.nextInt(100000); // Número entre 0 y 99999

        // Formatear el número de factura
        return String.format("%04d-%03d-%05d", parte1, parte2, parte3);
    }
}
