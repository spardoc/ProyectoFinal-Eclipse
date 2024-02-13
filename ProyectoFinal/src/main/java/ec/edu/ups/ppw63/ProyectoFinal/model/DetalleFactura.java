
package ec.edu.ups.ppw63.ProyectoFinal.model;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "detalles_factura")
public class DetalleFactura {
	
    @Id
    @GeneratedValue
    @Column(name = "codigo")
	private int codigo;
	
    @Column(name = "cantidad")
	private int cantidad;
	
    @Column(name = "precio_unitario")
	private double precio;
    
    @Column(name = "talla")
	private String talla;
	
    @Column(name = "subtotal")
	private double subtotal;
    
    @Column(name = "iva")
	private int iva;
    
    @Column(name = "total")
	private double total;
	
	@ManyToOne
    @JoinColumn(name = "factura_codigo")
    private Factura factura;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="codigo_detalleFactura")
    private List<Producto> productos;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "DetalleFactura [codigo=" + codigo + ", cantidad=" + cantidad + ", precio="
				+ precio + ", iva=" + iva + ", factura=" + factura + ", productos=" + productos + "]";
	}
}
