package ec.edu.ups.ppw63.ProyectoFinal.business;

import java.util.List;

import ec.edu.ups.ppw63.ProyectoFinal.dao.FacturaDAO;
import ec.edu.ups.ppw63.ProyectoFinal.model.Factura;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionFacturas 
{
	@Inject
	private FacturaDAO daoFactura;
	
	public void guardarFactura(Factura factura) 
	{
		Factura fac = daoFactura.read(factura.getCodigo());
		if (fac != null) 
		{
			daoFactura.update(factura);
		}
		else 
		{
			daoFactura.insert(factura);
		}
	}
	
	public void actualizarFactura(Factura factura) throws Exception 
	{
		Factura fac = daoFactura.read(factura.getCodigo());
		if (fac != null) 
		{
			daoFactura.update(factura);
		}
		else 
		{
			throw new Exception("Cliente no existe");
		}
	}
	
	public Factura getFacturaPorNumero(String numero) throws Exception 
	{
		return daoFactura.getFacturaPorNumero(numero);
	}
	
	public void borrarFactura(int codigo) 
	{
		daoFactura.remove(codigo);
	}
	
	public List<Factura> getFacturas()
	{
		return daoFactura.getAll();
	}
}
