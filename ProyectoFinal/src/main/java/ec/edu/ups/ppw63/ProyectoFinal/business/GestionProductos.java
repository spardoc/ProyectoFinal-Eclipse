package ec.edu.ups.ppw63.ProyectoFinal.business;

import java.util.List;

import ec.edu.ups.ppw63.ProyectoFinal.dao.ProductoDAO;
import ec.edu.ups.ppw63.ProyectoFinal.model.Producto;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionProductos
{
	@Inject
	private ProductoDAO daoProducto;
	
	public void guardarProducto(Producto producto) 
	{
		Producto prod = daoProducto.read(producto.getCodigo());
		if (prod != null) 
		{
			daoProducto.update(producto);
		}
		else 
		{
			daoProducto.insert(producto);
		}
	}
	
	public void actualizarProducto(Producto producto) throws Exception 
	{
		Producto prod = daoProducto.read(producto.getCodigo());
		if (prod != null) 
		{
			daoProducto.update(producto);
		}
		else 
		{
			throw new Exception("Producto no existe");
		}
	}
	
	public Producto getProductoPorCodigo(int codigo) throws Exception 
	{
		return daoProducto.getProductoPorCodigo(codigo);
	}
	
	public void borrarProducto(int codigo) 
	{
		daoProducto.remove(codigo);
	}
	
	public List<Producto> getProductos()
	{
		return daoProducto.getAll();
	}
}