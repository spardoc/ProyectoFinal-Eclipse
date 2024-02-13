
package ec.edu.ups.ppw63.ProyectoFinal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto 
{
	@Id
	@GeneratedValue
    @Column(name = "codigo")
    private int codigo;
    
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "categoria")
    private String categoria;
    
    @Column(name = "stock")
    private int stock;
    
    @Column(name = "precio")
    private double precio;
    
    @Column(name = "descripcion",length = 2000)
    private String descripcion;
    
    @Column(name = "imagen", length = 2000) // Aumenta la longitud de la columna 'imagen' según tus necesidades
    private String imagen;

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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", categoria=" + categoria + ", stock=" + stock
				+ ", precio=" + precio + ", descripcion=" + descripcion + ", imagen=" + imagen + "]";
	}
	
}
