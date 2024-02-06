
package ec.edu.ups.ppw63.ProyectoFinal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
	
    @Id
    @GeneratedValue
    @Column(name = "codigo")
    private int codigo;
    
    @Column(name = "dni")
    private String dni;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "correo")
    private String correo;
    
    @Column(name = "clave")
    private String clave;

    @Column(name = "direccion")
    private String direccion;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Carrito carrito;
    
    public Cliente() {
        this.carrito = new Carrito();
        this.carrito.setCliente(this);
    }

    public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}
	
}
