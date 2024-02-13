package ec.edu.ups.ppw63.ProyectoFinal.dto;

import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleFactura;

public class DetalleFacturaDTO {
    private int cantidad;
    private double precio;
    private int iva;
    private String talla;

    public DetalleFacturaDTO() {
    }

    public DetalleFacturaDTO(DetalleFactura detalle) {
        this.cantidad = detalle.getCantidad();
        this.precio = detalle.getPrecio();
        this.iva = detalle.getIva();
        this.talla = detalle.getTalla();
    }

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}
}
