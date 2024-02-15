package ec.edu.ups.ppw63.ProyectoFinal.business;

import ec.edu.ups.ppw63.ProyectoFinal.dao.DetalleCarritoDAO;
import ec.edu.ups.ppw63.ProyectoFinal.dao.ProductoDAO;
import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleCarrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.Producto;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class GestionDetallesCarrito {

    @Inject
    private DetalleCarritoDAO detalleCarritoDAO;

    @Inject
    private ProductoDAO productoDAO;
    
    public void agregarDetalle(DetalleCarrito detalle) {
        detalleCarritoDAO.insert(detalle);
    }

    public void actualizarDetalle(DetalleCarrito detalle) {
        detalleCarritoDAO.update(detalle);
    }

    public DetalleCarrito obtenerDetalle(int codigo) {
        return detalleCarritoDAO.read(codigo);
    }

    public void eliminarDetalle(int codigo) {
        detalleCarritoDAO.remove(codigo);
        System.out.println("DETALLE ELIMINADO");
    }

    public List<DetalleCarrito> obtenerDetallesPorCarrito(int codigoCarrito) {
        return detalleCarritoDAO.obtenerDetallesPorCarrito(codigoCarrito);
    }
    
    public Producto obtenerProductoCompleto(int codigoProducto) {
        return productoDAO.read(codigoProducto); // Suponiendo que este método devuelve toda la información del producto
    }
}
