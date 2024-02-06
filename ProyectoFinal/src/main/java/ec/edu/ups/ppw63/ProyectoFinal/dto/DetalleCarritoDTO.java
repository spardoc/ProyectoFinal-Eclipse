package ec.edu.ups.ppw63.ProyectoFinal.dto;

import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleCarrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.Producto;

public class DetalleCarritoDTO {
    private int cantidad;
    private ProductoDTO producto;

    // Constructor por defecto necesario para la deserialización JSON
    public DetalleCarritoDTO() {
    }

    // Getters y setters para todas las propiedades
    public int getCantidad() {
        return cantidad;
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

    // Método para convertir DTO a entidad
    public DetalleCarrito toEntity() {
        DetalleCarrito detalle = new DetalleCarrito();
        detalle.setCantidad(this.cantidad);
        if (this.producto != null) {
            Producto productoEntity = this.producto.toEntity();
            detalle.setProducto(productoEntity);
        }
        // Asumiendo que DetalleCarrito tiene otros campos, establecerlos aquí
        return detalle;
    }

    // Constructor que acepta un DetalleCarrito para convertir de la entidad al DTO
    public DetalleCarritoDTO(DetalleCarrito detalle) {
        this.cantidad = detalle.getCantidad();
        this.producto = new ProductoDTO(detalle.getProducto());
        // Asumiendo que DetalleCarrito tiene otros campos, inicializarlos aquí
    }
}