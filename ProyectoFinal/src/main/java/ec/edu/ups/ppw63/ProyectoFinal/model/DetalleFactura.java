package ec.edu.ups.ppw63.ProyectoFinal.model;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "detalles_factura")
public class DetalleFactura {
	
	@GeneratedValue
    @Id
	private int codigo;
	private String nombre; 
	private int cantidad;
	private double precio;
	private int iva;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="codigo_detalleFactura")
    private List<Producto> productos;

    public void addProducto(Producto producto) 
    {
        if (productos == null) {
            productos = new ArrayList<Producto>();
        }
        productos.add(producto);
    }
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	@Override
	public String toString() {
		return "DetalleFactura [codigo=" + codigo + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio="
				+ precio + ", iva=" + iva + ", productos=" + productos + "]";
	}
	
}
