package ec.edu.ups.ppw63.ProyectoFinal.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ec.edu.ups.ppw63.ProyectoFinal.model.Carrito;
import ec.edu.ups.ppw63.ProyectoFinal.model.Cliente;
import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleCarrito;

public class CarritoDTO {
    private int codigo;
    private int clienteCodigo; // Sólo el identificador del cliente
    private List<DetalleCarritoDTO> detalles;

    // Constructor por defecto necesario para la deserialización JSON
    public CarritoDTO() {
        this.detalles = new ArrayList<>();
    }

    // Getters y setters para todas las propiedades
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getClienteCodigo() {
        return clienteCodigo;
    }

    public void setClienteCodigo(int clienteCodigo) {
        this.clienteCodigo = clienteCodigo;
    }

    public List<DetalleCarritoDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCarritoDTO> detalles) {
        this.detalles = detalles;
    }

    // Método para convertir el DTO a la entidad Carrito
    public Carrito toEntity() {
        Carrito carrito = new Carrito();
        carrito.setCodigo(this.codigo);
        
        if (this.clienteCodigo > 0) {
            Cliente cliente = new Cliente();
            cliente.setCodigo(this.clienteCodigo);
            carrito.setCliente(cliente);
        }
        
        if (this.detalles != null) {
            List<DetalleCarrito> detalleEntities = this.detalles.stream()
                                                               .map(DetalleCarritoDTO::toEntity)
                                                               .collect(Collectors.toList());
            carrito.setDetalles(detalleEntities);
        }
        
        return carrito;
    }

    // Constructor que acepta un Carrito para convertir de la entidad al DTO
    public CarritoDTO(Carrito carrito) {
        this.codigo = carrito.getCodigo();
        this.clienteCodigo = carrito.getCliente().getCodigo(); // Usa solo el identificador del cliente
        this.detalles = carrito.getDetalles().stream()
                               .map(DetalleCarritoDTO::new) // Convierte cada detalle a DTO
                               .collect(Collectors.toList());
    }
}
