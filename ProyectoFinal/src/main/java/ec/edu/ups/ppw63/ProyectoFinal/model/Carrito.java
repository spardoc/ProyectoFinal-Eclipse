
package ec.edu.ups.ppw63.ProyectoFinal.model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Producto> productos;
    private int codigoCliente; // Almacenamos solo el código del cliente

    public Carrito(int codigoCliente) {
        this.codigoCliente = codigoCliente;
        productos = new ArrayList<>();
    }

    // Método para añadir un producto al carrito
    public void añadirProducto(Producto producto) {
        productos.add(producto);
    }

    // Método para remover un producto del carrito
    public void removerProducto(Producto producto) {
        productos.remove(producto);
    }

    // Método para vaciar el carrito
    public void vaciarCarrito() {
        productos.clear();
    }

    // Getters y Setters
    public List<Producto> getProductos() {
        return productos;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }
}
