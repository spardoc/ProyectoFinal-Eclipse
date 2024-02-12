package ec.edu.ups.ppw63.ProyectoFinal.dto;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ec.edu.ups.ppw63.ProyectoFinal.model.Factura;

public class FacturaDTO {

    private int codigo;
    private String numero;
    private Date fechaEmision;
    private Double total;
    private ClienteDTO cliente;
    private List<DetalleFacturaDTO> detalles;

    public FacturaDTO() {
    }

    public FacturaDTO(Factura factura) {
        this.codigo = factura.getCodigo();
        this.numero = factura.getNumero();
        this.fechaEmision = factura.getFechaEmision();
        this.total = factura.getTotal();
        this.cliente = new ClienteDTO(factura.getCliente());
        this.detalles = factura.getDetalles().stream()
                               .map(DetalleFacturaDTO::new)
                               .collect(Collectors.toList());
    }

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public List<DetalleFacturaDTO> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleFacturaDTO> detalles) {
		this.detalles = detalles;
	}
}

