package ec.edu.ups.ppw63.ProyectoFinal.business;

import ec.edu.ups.ppw63.ProyectoFinal.dao.DetalleFacturaDAO;
import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleFactura;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionDetallesFactura {
    @Inject
    private DetalleFacturaDAO detalleFacturaDAO;

    public void agregarDetalleFactura(DetalleFactura detalle) {
        detalleFacturaDAO.insert(detalle);
    }

    public void actualizarDetalleFactura(DetalleFactura detalle) {
        detalleFacturaDAO.update(detalle);
    }

    public DetalleFactura obtenerDetalleFactura(int codigo) {
        return detalleFacturaDAO.read(codigo);
    }

    public void eliminarDetalleFactura(int codigo) {
        detalleFacturaDAO.remove(codigo);
    }

    public DetalleFactura obtenerDetallesFacturaPorCodigo(int codigoDetalleFactura) {
    	return detalleFacturaDAO.read(codigoDetalleFactura);
    }
}

