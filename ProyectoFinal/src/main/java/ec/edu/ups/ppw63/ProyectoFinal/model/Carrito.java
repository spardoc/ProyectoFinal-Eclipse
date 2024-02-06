
package ec.edu.ups.ppw63.ProyectoFinal.model;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "carritos")
public class Carrito {
	@Id
    @GeneratedValue
    @Column(name = "codigo")
    private int codigo;
	
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DetalleCarrito> detalles = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_codigo")
    private Cliente cliente;

    // Método para añadir un producto al carrito
    public void añadirDetalleCarrito(DetalleCarrito detalle) {
    	detalles.add(detalle);
    }

    // Método para remover un producto del carrito
    public void removerDetalleCarrito(DetalleCarrito detalle) {
    	detalles.remove(detalle);
    }

    // Método para vaciar el carrito
    public void vaciarCarrito() {
    	detalles.clear();
    }

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public List<DetalleCarrito> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleCarrito> detalles) {
		this.detalles = detalles;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Carrito [codigo=" + codigo + ", detalles=" + detalles + ", cliente=" + cliente + "]";
	}
}
