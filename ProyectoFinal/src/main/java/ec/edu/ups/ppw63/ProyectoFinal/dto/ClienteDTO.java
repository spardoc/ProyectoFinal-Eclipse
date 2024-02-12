package ec.edu.ups.ppw63.ProyectoFinal.dto;

import ec.edu.ups.ppw63.ProyectoFinal.model.Cliente;


public class ClienteDTO {
    private int codigo;
    private String dni;
    private String nombre;
    private String correo;
    private String clave;
    private String direccion;
    private Integer carritoCodigo;

    // Constructor por defecto necesario para la deserialización JSON
    public ClienteDTO() {
    }

    // Getters y setters para todas las propiedades
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Método para convertir DTO a entidad
    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setCodigo(this.codigo);
        cliente.setDni(this.dni);
        cliente.setNombre(this.nombre);
        cliente.setCorreo(this.correo);
        cliente.setClave(this.clave);
        cliente.setDireccion(this.direccion);
        return cliente;
    }

 // Getter y setter nuevo para carritoCodigo
    public Integer getCarritoCodigo() {
        return carritoCodigo;
    }

    public void setCarritoCodigo(Integer carritoCodigo) {
        this.carritoCodigo = carritoCodigo;
    }

    // Constructor que acepta un Cliente para convertir de la entidad al DTO
    public ClienteDTO(Cliente cliente) {
        this.codigo = cliente.getCodigo();
        this.dni = cliente.getDni();
        this.nombre = cliente.getNombre();
        this.correo = cliente.getCorreo();
        this.clave = cliente.getClave();
        this.direccion = cliente.getDireccion();
        this.carritoCodigo = cliente.getCarrito() != null ? cliente.getCarrito().getCodigo() : null; // Asigna el código del Carrito si existe
    }
}
