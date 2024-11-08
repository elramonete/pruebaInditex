# PruebaInditex
Este es el proyecto realizado para la prueba de inditex

Para acceder a la base de datos al levantar el programa acceder a http://localhost:8050/h2-console

Los datos necesarios para ver la tabla son

Driver class: org.h2.Driver

JDBC url: jdbc:h2:mem:testdb

user: sa
rar
sin password

![image](https://user-images.githubusercontent.com/7735554/233590317-0035b22f-e5d6-41dd-beaf-9134589d8c40.png)

Teneis una interfaz swagger disponible para hacer las pruebas necesarias en http://localhost:8050/swagger-ui/

![image](https://user-images.githubusercontent.com/7735554/233591202-9f26d139-71a0-4464-8977-c7bc8a31ed0b.png)

En este proyecto he intentado realizar una arquitectura hexagonal, aunque no lleve mucho tiempo trabajando con este tipo de arquitectura,
queria demostrarme a mi mismo que podia crear una arquitectura como esta desde cero. En esta arquitectura hay 3 partes diferenciadas, el **domain**, donde se encuentra
el dominio de la **aplicacion**, otra parte llamada aplicacion que es el corazon del aplicativo y donde se encuentra toda la lógica de esta, y otra de **infraestructura** donde 
he metido tanto los repositorios con acceso a bbdd incluidas las entidades como los controladores a traves de los cuales se conecta la applicacion con el exterior.

A partir de la parte de la applicacion que como os decia era el centro de la applicacion es donde se encuentran los servicios que realizan toda la logica y estos servicios
se conectan por una parte al acceso a base de datos a traves de unos puertos que son unas interfaces que definen la funcionalidad de los repositorios que son unos adaptadores
que se conectaran en ultima instancia con la bbdd, en este proyecto mediante spring data jpa. 

Los servicios utilizaran otro puerto ( y otro adaptador) para tener acceso a los controladores que son los encargados de comunicar la applicacion con el exterior 
mediante un servicio web, que para facilitar las pruebas he integrado swagger en estos controladores.

En la parte de los tests tenemos test unitarios con mockito para los servicios y los adaptadores de los repositorios y he utilizado RestAssured para hacer una prueba 
endToEnd de los controladores. 

Las 5 pruebas que indicais que hay que realizar en la prueba estan implementadas en la clase **ArticleControllerAdapterTest**.



