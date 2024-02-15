package ec.edu.ups.ppw63.ProyectoFinal.dto;

import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleCarrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.Producto;

public class DetalleCarritoDTO {
	private int codigo;
    private int cantidad;
    private ProductoDTO producto;
    private String talla;

    // Constructor por defecto necesario para la deserialización JSON
    public DetalleCarritoDTO() {
    }

    // Getters y setters para todas las propiedades
    public int getCantidad() {
        return cantidad;
    }

    public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	// Método para convertir DTO a entidad
    public DetalleCarrito toEntity() {
        DetalleCarrito detalle = new DetalleCarrito();
        detalle.setCantidad(this.cantidad);
        if (this.producto != null) {
            Producto productoEntity = this.producto.toEntity();
            detalle.setProducto(productoEntity);
        }
        detalle.setTalla(this.talla);
        detalle.setCodigo(this.codigo);
        return detalle;
    }

    // Constructor que acepta un DetalleCarrito para convertir de la entidad al DTO
    public DetalleCarritoDTO(DetalleCarrito detalle) {
    	this.codigo = detalle.getCodigo();
        this.cantidad = detalle.getCantidad();
        this.producto = new ProductoDTO(detalle.getProducto());
        this.talla = detalle.getTalla();
    }
}
