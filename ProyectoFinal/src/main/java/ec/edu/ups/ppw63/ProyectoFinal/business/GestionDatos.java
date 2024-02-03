package ec.edu.ups.ppw63.ProyectoFinal.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import ec.edu.ups.ppw63.ProyectoFinal.dao.ClienteDAO;
import ec.edu.ups.ppw63.ProyectoFinal.dao.FacturaDAO;
import ec.edu.ups.ppw63.ProyectoFinal.model.Cliente;
import ec.edu.ups.ppw63.ProyectoFinal.model.DetalleFactura;
import ec.edu.ups.ppw63.ProyectoFinal.model.Factura;
import ec.edu.ups.ppw63.ProyectoFinal.model.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup //Al momento de levantar la aplicacion se crea
public class GestionDatos 
{
	@Inject
	private ClienteDAO daoCliente;
	
	@Inject
	private FacturaDAO daoFactura;
	
	@Resource(lookup = "java:/ProyectoFinalDS")
    private DataSource dataSource;
	
	@PostConstruct //Posterior a la creacion se llama a este metodo
	public void init() 
	{
        try (Connection connection = dataSource.getConnection()) {
            // Imprimir un mensaje si la conexión es exitosa
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            // Capturar y mostrar cualquier excepción que ocurra
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
		
		System.out.println("Iniciando...");
		
		Cliente cliente = new Cliente();
		cliente.setCodigo(1);
		cliente.setDni("1105919169");
		cliente.setNombre("Samuel Pardo");
		cliente.setDireccion("Loja");
		
		daoCliente.insert(cliente);
		
		cliente = new Cliente();
		cliente.setCodigo(2);
		cliente.setDni("1105919388");
		cliente.setNombre("Cristian Timbi");
		cliente.setDireccion("Cuenca");
		
		daoCliente.insert(cliente);
		
		Factura factura = new Factura();
		factura.setCliente(cliente);
		factura.setNumero("11111-111-1111");
		factura.setFechaEmision(new Date());
		factura.setTotal(1000.52);
		
		DetalleFactura det = new DetalleFactura();
		det.setNombre("Chaquetas");
		det.setCantidad(2);
		det.setPrecio(100.50);
		
		factura.addDetalle(det);
		
		det = new DetalleFactura();
		det.setNombre("Hoodies");
		det.setCantidad(1);
		det.setPrecio(150.50);
		
		factura.addDetalle(det);
		
		Producto prod = new Producto();
		prod.setNombre("Americana");
		prod.setCategoria("Hombre");
		prod.setPrecio(20.50);
		prod.setStock(50);
		
		prod = new Producto();
		prod.setNombre("Floreada");
		prod.setCategoria("Mujer");
		prod.setPrecio(15.60);
		prod.setStock(20);
		
		
		
		
		daoFactura.insert(factura);
		
		/*System.out.println("\n------------- Clientes");
		List<Cliente> list = daoCliente.getAll();
		for (Cliente cli: list) {
			System.out.println(cli.getCodigo() + "\t" + cli.getNombre());
		}*/
		System.out.println("\n------------- Facturas2");
		List<Factura> list2 = daoFactura.getAll();
		for (Factura fac: list2) {
			System.out.println(fac);
		}
	
	}
}
