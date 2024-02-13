package ec.edu.ups.ppw63.ProyectoFinal.dto;

import ec.edu.ups.ppw63.ProyectoFinal.model.Producto;

public class ProductoDTO {
    private int codigo;
    private String nombre;
    private String categoria;
    private int stock;
    private double precio;
    private String imagen;
    private String descripcion;

    // Constructor por defecto, getters y setters

    public Producto toEntity() {
        Producto producto = new Producto();
        producto.setCodigo(this.codigo);
        producto.setNombre(this.nombre);
        producto.setCategoria(this.categoria);
        producto.setStock(this.stock);
        producto.setPrecio(this.precio);
        producto.setImagen(this.imagen);
        producto.setDescripcion(this.descripcion);
        return producto;
    }

    // Constructor por defecto
    public ProductoDTO() {
        // Este constructor está vacío, pero es necesario para la deserialización JSON
    }

    // Constructor que acepta un Producto
    public ProductoDTO(Producto producto) {
        this.codigo = producto.getCodigo();
        this.nombre = producto.getNombre();
        this.categoria = producto.getCategoria();
        this.stock = producto.getStock();
        this.precio = producto.getPrecio();
        this.imagen = producto.getImagen();
        this.descripcion = producto.getDescripcion();
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
}
