package ec.edu.ups.ppw63.ProyectoFinal.business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import ec.edu.ups.ppw63.ProyectoFinal.dao.ClienteDAO;
import ec.edu.ups.ppw63.ProyectoFinal.dao.DetalleFacturaDAO;
import ec.edu.ups.ppw63.ProyectoFinal.dao.FacturaDAO;
import ec.edu.ups.ppw63.ProyectoFinal.dao.ProductoDAO;
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
	
	@Inject
	private ProductoDAO daoProducto;
	
	@Inject
	private DetalleFacturaDAO daoDetalleFactura;
	
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
		cliente.setCorreo("samuelpardo1997@gmail.com");
		cliente.setClave("1234");
		cliente.setDireccion("Loja");
		
		daoCliente.insert(cliente);
		
		cliente = new Cliente();
		cliente.setCodigo(2);
		cliente.setDni("1105919388");
		cliente.setNombre("Cristian Timbi");
		cliente.setCorreo("ctimbi@ups.edu.ec");
		cliente.setClave("1234");
		cliente.setDireccion("Cuenca");
		
		daoCliente.insert(cliente);
		
		Producto prod = new Producto();
		prod.setNombre("Camiseta de rayas");
		prod.setCategoria("Hombre");
		prod.setPrecio(20.50);
		prod.setImagen("https://www.etafashion.com/medias/5000000916476-900x1200-0.jpg?context=bWFzdGVyfGltYWdlc3w0MTk3MDN8aW1hZ2UvanBlZ3xhR0U1TDJnd05DOHlPRFk1T0RVek5EWTBNVFk1TkM4MU1EQXdNREF3T1RFMk5EYzJMVGt3TUhneE1qQXdYekF1YW5CbnwzYTJkMDgxZTNiZDQ1Nzk1ODU3MDI3OWVkZGIwYThiYTYwMjgzZDFkMWM4ODQyZDk5MGFkNGM0OGY5Mjk5MWVl");
		prod.setStock(50);
		
		daoProducto.insert(prod);
		
		prod = new Producto();
		prod.setNombre("Camiseta con estampado PIXAR");
		prod.setCategoria("Hombre");
		prod.setPrecio(15.60);
		prod.setImagen("https://images.deprati.com.ec/sys-master/images/hd7/h2d/11717335842846/16937855-0_product_1200Wx1800H");
		prod.setStock(20);
		
		daoProducto.insert(prod);

		prod = new Producto();
		prod.setNombre("Camiseta con estampado Asiatico");
		prod.setCategoria("Hombre");
		prod.setPrecio(15.60);
		prod.setImagen("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcKbp6h1PwB1clTEfdaD6cjH61EY0aPVpmW907fkePyA&s");
		prod.setStock(20);
		
		daoProducto.insert(prod);
		
		prod = new Producto();
		prod.setNombre("Camiseta Rosada");
		prod.setCategoria("Mujer");
		prod.setPrecio(15.60);
		prod.setImagen("https://lucattistore.com/wp-content/uploads/2021/08/Camiseta-con-estampado-de-bolsillo-Montanita-mujer.jpg");
		prod.setStock(20);
		
		daoProducto.insert(prod);
		
		prod = new Producto();
		prod.setNombre("Camiseta con estampado de mujer");
		prod.setCategoria("Mujer");
		prod.setPrecio(15.60);
		prod.setImagen("https://i.pinimg.com/736x/ce/4c/e9/ce4ce9824af0b548d703b31bdbdc75b4.jpg");
		prod.setStock(20);
		
		daoProducto.insert(prod);
		
		prod = new Producto();
		prod.setNombre("Camiseta con estampado de Stich");
		prod.setCategoria("Mujer");
		prod.setPrecio(15.60);
		prod.setImagen("https://i.ebayimg.com/images/g/seEAAOSwPHdkB5xS/s-l1200.webp");
		prod.setStock(20);
		
		daoProducto.insert(prod);
		
		System.out.println("\n------------- Clientes");
		List<Cliente> list = daoCliente.getAll();
		for (Cliente cli: list) {
			System.out.println(cli);
		}
		
		System.out.println("\n------------- Productos");
		List<Producto> list2 = daoProducto.getAll();
		for (Producto producto: list2) {
			System.out.println(producto);
		}
		
//		System.out.println("\n------------- Facturas2");
//		List<Factura> list2 = daoFactura.getAll();
//		for (Factura fac: list2) {
//			System.out.println(fac);
//		}
	
		
		
	}
}
