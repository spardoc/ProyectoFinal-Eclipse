package ec.edu.ups.ppw63.ProyectoFinal.business;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import ec.edu.ups.ppw63.ProyectoFinal.dao.ClienteDAO;
import ec.edu.ups.ppw63.ProyectoFinal.dao.ProductoDAO;
import ec.edu.ups.ppw63.ProyectoFinal.model.Cliente;
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
	private ProductoDAO daoProducto;
	
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
		cliente.setDni("1105919169");
		cliente.setNombre("Samuel Pardo");
		cliente.setCorreo("samuelpardo1997@outlook.com");
		cliente.setClave("1234");
		cliente.setDireccion("Loja");
		
		daoCliente.insert(cliente);
		
		cliente = new Cliente();
		cliente.setDni("1105919388");
		cliente.setNombre("Cristian Timbi");
		cliente.setCorreo("ctimbi@ups.edu.ec");
		cliente.setClave("5678");
		cliente.setDireccion("Cuenca");
		
		daoCliente.insert(cliente);
		
		Producto prod = new Producto();
		prod.setNombre("Camiseta de rayas");
		prod.setCategoria("Hombre");
		prod.setPrecio(14.99);
		prod.setImagen("https://www.etafashion.com/medias/5000000916476-900x1200-0.jpg?context=bWFzdGVyfGltYWdlc3w0MTk3MDN8aW1hZ2UvanBlZ3xhR0U1TDJnd05DOHlPRFk1T0RVek5EWTBNVFk1TkM4MU1EQXdNREF3T1RFMk5EYzJMVGt3TUhneE1qQXdYekF1YW5CbnwzYTJkMDgxZTNiZDQ1Nzk1ODU3MDI3OWVkZGIwYThiYTYwMjgzZDFkMWM4ODQyZDk5MGFkNGM0OGY5Mjk5MWVl");
		prod.setStock(50);
		prod.setDescripcion("Esta camiseta de rayas combina estilo clásico con confort moderno. Diseñada con rayas horizontales en colores contrastantes, ofrece un look versátil que se adapta a cualquier ocasión, desde salidas casuales hasta encuentros más formales.");
		
		daoProducto.insert(prod);
		
		prod = new Producto();
		prod.setNombre("Camiseta PIXAR Elemental");
		prod.setCategoria("Hombre");
		prod.setPrecio(12.30);
		prod.setImagen("https://images.deprati.com.ec/sys-master/images/hd7/h2d/11717335842846/16937855-0_product_1200Wx1800H");
		prod.setStock(20);
		prod.setDescripcion("Celebra tus películas favoritas con nuestra camiseta PIXAR Elemental. Este diseño único presenta un collage creativo de personajes icónicos de PIXAR, perfecto para los aficionados de todas las edades que desean llevar un pedazo de su infancia a donde quiera que vayan.");
		
		daoProducto.insert(prod);

		prod = new Producto();
		prod.setNombre("Camiseta One Piece");
		prod.setCategoria("Hombre");
		prod.setPrecio(15.60);
		prod.setImagen("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcKbp6h1PwB1clTEfdaD6cjH61EY0aPVpmW907fkePyA&s");
		prod.setStock(20);
		prod.setDescripcion("One Piece: Para los fanáticos del anime, esta camiseta con estampado de One Piece captura la esencia de la aventura y el espíritu de libertad del famoso manga y anime. Con ilustraciones detalladas de Luffy y su tripulación, es la elección perfecta para mostrar tu pasión por el mar y la amistad.");
		
		daoProducto.insert(prod);
		
		prod = new Producto();
		prod.setNombre("Camiseta Rosada");
		prod.setCategoria("Mujer");
		prod.setPrecio(10.99);
		prod.setImagen("https://lucattistore.com/wp-content/uploads/2021/08/Camiseta-con-estampado-de-bolsillo-Montanita-mujer.jpg");
		prod.setStock(20);
		prod.setDescripcion("Esta camiseta rosada es la adición perfecta a tu guardarropa, ofreciendo un toque de color suave pero impactante. Ideal para combinar con diferentes atuendos, su tono pastel complementa a la perfección tanto estilos casuales como más arreglados.");
		
		daoProducto.insert(prod);
		
		prod = new Producto();
		prod.setNombre("Camiseta con estampado de mujer");
		prod.setCategoria("Mujer");
		prod.setPrecio(10.80);
		prod.setImagen("https://i.pinimg.com/736x/ce/4c/e9/ce4ce9824af0b548d703b31bdbdc75b4.jpg");
		prod.setStock(20);
		prod.setDescripcion("Inspiradora y poderosa, esta camiseta presenta un estampado que celebra la belleza y la fuerza femenina. Diseñada para empoderar y hacer una declaración de estilo, es perfecta para aquellos que desean expresar su apoyo a la igualdad de género con moda consciente.");
		
		daoProducto.insert(prod);
		
		prod = new Producto();
		prod.setNombre("Camiseta Stich");
		prod.setCategoria("Mujer");
		prod.setPrecio(15.60);
		prod.setImagen("https://i.ebayimg.com/images/g/seEAAOSwPHdkB5xS/s-l1200.webp");
		prod.setStock(20);
		prod.setDescripcion("Lleva contigo a tu alienígena favorito con esta camiseta de Stitch. Con un diseño divertido y colorido que captura la esencia juguetona de Stitch, esta camiseta es ideal para los fans de todas las edades que buscan añadir un toque de diversión a su vestuario diario.");
		
		daoProducto.insert(prod);
		
		prod = new Producto();
		prod.setNombre("Camiseta LUCATTI");
		prod.setCategoria("Hombre");
		prod.setPrecio(12.00);
		prod.setImagen("https://lucattistore.com/wp-content/uploads/2021/08/Camiseta-con-estampado-de-bolsillo-Guayaquil-Parque-Las-Iguanas-mujer.jpg");
		prod.setStock(10);
		prod.setDescripcion("La camiseta LUCATTI redefine el estilo urbano con su diseño minimalista y moderno. Perfecta para los amantes de la moda que buscan piezas con carácter y sofisticación, esta camiseta combina calidad superior con una estética impecable.");
		
		daoProducto.insert(prod);
		
		prod = new Producto();
		prod.setNombre("Camiseta básica LONG BEACH");
		prod.setCategoria("Hombre");
		prod.setPrecio(10.50);
		prod.setImagen("https://patprimoecuador.vteximg.com.br/arquivos/ids/218429-950-1140/44090593-9399_1.jpg?v=637528027199400000");
		prod.setStock(32);
		prod.setDescripcion("Esta camiseta captura la esencia relajada de LONG BEACH con un diseño simple pero elegante. Perfecta para días soleados o como recuerdo de tus vacaciones en la playa, su estampado evoca la tranquilidad y la belleza de la costa.");
		
		daoProducto.insert(prod);
		
		prod = new Producto();
		prod.setNombre("Camiseta RUGRATS");
		prod.setCategoria("Mujer");
		prod.setPrecio(13.60);
		prod.setImagen("https://falabella.scene7.com/is/image/FalabellaCO/11591800_1?wid=800&hei=800&qlt=70");
		prod.setStock(15);
		prod.setDescripcion("Revive tu infancia con esta camiseta de RUGRATS. Con estampados nostálgicos de Tommy, Chuckie, y el resto de la pandilla, esta camiseta es perfecta para los que desean recordar los días de aventuras infantiles con estilo.");
		
		daoProducto.insert(prod);
		
	}
}
