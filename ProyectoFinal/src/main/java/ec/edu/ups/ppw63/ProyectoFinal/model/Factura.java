
package ec.edu.ups.ppw63.ProyectoFinal.model;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "facturas")
public class Factura 
{
    @Id
    @GeneratedValue
    @Column(name = "codigo")
    private int codigo;
    
    @Column(name = "numero")
    private String numero;

    @Column(name = "fechaEmision")
    private Date fechaEmision;

    @Column(name = "total")
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_cliente")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="codigo_factura")
	private List<DetalleFactura> detalles;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public List<DetalleFactura> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleFactura> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "Factura [codigo=" + codigo + ", numero=" + numero + ", fechaEmision=" + fechaEmision + ", total="
				+ total + ", cliente=" + cliente + ", detalles=" + detalles + "]";
	}
}