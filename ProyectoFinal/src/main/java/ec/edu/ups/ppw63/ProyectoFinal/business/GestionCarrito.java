package ec.edu.ups.ppw63.ProyectoFinal.business;

import java.util.List;

import ec.edu.ups.ppw63.ProyectoFinal.dao.CarritoDAO;
import ec.edu.ups.ppw63.ProyectoFinal.model.Carrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleCarrito;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionCarrito {

    @Inject
    private CarritoDAO carritoDAO;

    public Carrito agregarDetallesACarrito(int codigoCarrito, List<DetalleCarrito> detalles) {
        Carrito carrito = carritoDAO.read(codigoCarrito);

        if (carrito == null) {
            throw new IllegalStateException("El carrito con código " + codigoCarrito + " no existe.");
        }

        for (DetalleCarrito detalle : detalles) {
            detalle.setCarrito(carrito); // Asocia el carrito al detalle
            carritoDAO.agregarDetalleCarrito(detalle); // Persiste el detalle
        }

        return carrito;
    }

    public Carrito obtenerCarrito(int codigoCarrito) {
        return carritoDAO.read(codigoCarrito);
    }
    
    public Carrito obtenerCarritoPorCliente(int codigoCliente) {
        Carrito carrito = carritoDAO.obtenerCarritoPorCliente(codigoCliente);

        // Si el carrito no existe, significa que algo inesperado ha ocurrido,
        // ya que se supone que el carrito se crea automáticamente al crear el cliente.
        if (carrito == null) {
            // Aquí puedes manejar esta situación, por ejemplo, lanzando una excepción,
            // o crear un nuevo carrito y asociarlo al cliente (aunque esto último 
            // debería ser innecesario si la lógica de creación de clientes funciona correctamente).
            throw new IllegalStateException("El carrito no fue creado automáticamente para el cliente con código: " + codigoCliente);
        }

        return carrito;
    }
    
    public List<Carrito> getCarritos() {
       return carritoDAO.getAll();
    }
}
