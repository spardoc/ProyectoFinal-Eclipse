# Nombre del Proyecto
Proyecto Final web de compras

## Descripción
una web de sompras implementando angular como fronted y de backend uso de eclipse asi como
de base de datos mysql

## Características
-registro y auntentificacion de usuarios
-busquedas de productos
-Carrito de compras
-detalles de factura
-facturas
-chat de whatsapp
-adicion y eliminacion de productos
## Tecnologías Utilizadas
-Fronted angular 
-Backend uso de Eclipse Java Development Tools (JDT)
-Base de datos mysql

## Requisitos Previos
instalar las librerias necesarias de angular

## Cómo Empezar
crear base de datos mysql, y encender el servidor de wildfly, posteriormente dar un ng serve -o o un firebase deploy

## Estructura del Proyecto
toda la parte logica asi como los servicios de Métodos HTTP en Servicios Web y APIs RESTful estan en el backend
y la parte grafica tanto funcional como de diseño esta en el fronted

## Documentación de API
Métodos HTTP en Servicios Web y APIs RESTful

GET: Se utiliza para solicitar datos de un recurso especificado. GET es el método más común y se usa para obtener datos sin afectarlos.
estos metodos los utilzamos para el llamdo clientes,productos,detalles de factura y para obtener la factura

POST: Se emplea para enviar datos a un servidor para crear o actualizar un recurso. Los datos enviados al servidor con el método POST se almacenan en el cuerpo de la solicitud HTTP.
Estos metodos los utilizamos para  creacion de nuevos usuarios, carritos, detalles de facturas y la misma factura

PUT: Utilizado para actualizar un recurso existente o crear un nuevo recurso si no existe en el servidor. A diferencia de POST, PUT es idempotente, lo que significa que realizar la misma solicitud varias veces produce el mismo resultado que hacerlo una sola vez.
lo utilizamos en creacion del carrito y tambien en añadido de nuevos productos al carrito

DELETE: Se usa para eliminar un recurso especificado.
a la hora de vaciar el carrito quitamos los productos respectivos.

## Contacto y Soporte

jsalazarc10@est.ups.edu.ec
spardoc@est.ups.edu.ec 
