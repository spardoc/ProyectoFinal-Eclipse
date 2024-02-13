package ec.edu.ups.ppw63.ProyectoFinal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalles_carrito")
public class DetalleCarrito {
	
    @Id
    @GeneratedValue
    private int codigo;

    @ManyToOne
    @JoinColumn(name = "producto_codigo")
    private Producto producto;

    @Column(name = "cantidad")
    private int cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrito_codigo")
    private Carrito carrito;
    
    @Column(name = "talla")
    private String talla;
    
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
    
	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	@Override
	public String toString() {
		return "DetalleCarrito [codigo=" + codigo + ", producto=" + producto + ", cantidad=" + cantidad + ", carrito="
				+ carrito + "]";
	}
}
