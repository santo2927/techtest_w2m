# Prueba Técnica Spring Boot

## Elementos Implementados:

### Dependencias:

- **Java 17**
- **Spring Boot 3.2.2**
- **Spring Data JPA**
- **H2 Database**
- **Spring Cache**
- **Spring AOP**
- **Spring Exception Handling**
- **JUnit 5**
- **Mockito**
- **Lombok**
- **Spring Security**
- **Spring AoP**

### Estructura del Proyecto:

El proyecto está estructurado en paquetes de acuerdo a la arquitectura de capas, con la siguiente estructura:

1. **Aspect:** Contiene los aspectos de Spring AOP para interceptar llamadas a métodos y realizar acciones adicionales.
   Principalmente se ha implementado un aspecto para registrar logs cuando se solicita una nave con un id negativo y
   un aspecto para enviar a kafka la información de las naves que se crean, modifican o eliminan.
    - [Aspecto de Log](/src/main/java/world/to/meet/techtest/aspect/ShipAspect.java)
    - [Aspecto de Kafka](/src/main/java/world/to/meet/techtest/aspect/KafkaAspect.java)
2. **Config:** Contiene las clases de configuración de Kafka y de Spring Security.
    - [Configuración de Kafka](/src/main/java/world/to/meet/techtest/config/KafkaConfig.java)
    - [Configuración de Spring Security](/src/main/java/world/to/meet/techtest/config/SecurityConfig.java)
3. **Controller:** Contiene los controladores REST de la aplicación y accede a los servicios para realizar operaciones.
    - [Controlador de Naves](/src/main/java/world/to/meet/techtest/controller/ShipController.java)
4. **Exception:** Contiene las clases de excepción personalizadas y un controlador de excepciones global para manejar
   todas las excepciones lanzadas por la aplicación y devolver respuestas HTTP apropiadas.
    - [Excepciones Personalizadas](/src/main/java/world/to/meet/techtest/exception)
    - [Controlador de Excepciones Global](/src/main/java/world/to/meet/techtest/exception/GlobalExceptionHandler.java)
5. **Model:** Contiene las entidades de la base de datos, no he visto necesaria la implementación de DTOs. (De necesitarlo
   se implementaría con facilidad con MapStruct).
    - [Entidades de la Base de Datos](/src/main/java/world/to/meet/techtest/model)
6. **Repository:** Contiene las interfaces de repositorio de Spring Data JPA para interactuar con la base de datos.
    - [Repositorio de Naves](/src/main/java/world/to/meet/techtest/repository/ShipRepository.java)
7. **Service:** Contiene la lógica de negocio de la aplicación y accede a los repositorios para realizar operaciones de base de datos.
    - [Servicio de Naves](/src/main/java/world/to/meet/techtest/service/ShipService.java)

Todo lo pedido ha sido implementado, aquí una explicación de como se ha implementado cada punto:

1. **Consultar todas las naves utilizando paginación:**
    - He utilizado Spring Data JPA para interactuar con la base de datos y habilitar la paginación en los métodos de consulta.
   En este caso el endpoint es `GET /api/ships/pageable` y se puede enviar como parámetros `page` y `size` para paginar los resultados.

2. **Consultar una única nave por id:**
    - He implementado un endpoint REST que acepte el parámetro del id de la nave y busque la nave correspondiente en la base de datos.
    En este caso el endpoint es `GET /api/ships/{id}` y se puede enviar como parámetro el id de la nave que se quiere consultar.

3. **Consultar todas las naves que contienen, en su nombre, el valor de un parámetro enviado en la petición:**
    - He implementado un endpoint REST que acepte el parámetro de búsqueda y busque todas las naves cuyo nombre contenga el valor del parámetro.
    En este caso el endpoint es `GET /api/ships/findByName` y se puede enviar como parámetro `name` para buscar naves cuyo nombre contenga el valor del parámetro
   ignorando mayúsculas y minúsculas.

4. **Crear una nueva nave:**
    - He implementado un endpoint para crear nuevas naves utilizando un método POST y validación de datos. Además, 
   he implementado un aspecto de Spring que intercepta las llamadas al método de creación de una nave y envía 
   la información de la nave creada a un tópico de Kafka.

5. **Modificar una nave:**
    - He implementado un endpoint para modificar naves existentes utilizando un método PUT y validación de datos. Además,
    he implementado un aspecto de Spring que intercepta las llamadas al método de modificación de una nave y envía
    la información de la nave modificada a un tópico de Kafka.
   
6. **Eliminar una nave:**
    - He implementado un endpoint para eliminar naves existentes utilizando un método DELETE. Además,
    he implementado un aspecto de Spring que intercepta las llamadas al método de eliminación de una nave y envía
    la información de la nave eliminada a un tópico de Kafka.

Todos los endpoints se han desarrollado en el [controlador de naves](/src/main/java/world/to/meet/techtest/controller/ShipController.java).

7. **Test unitario de como mínimo de una clase:**
    - He escrito test unitarios con JUnit5 y Mockito para todas las clases de la aplicación que contenían lógica.
    - [Test de Servicio de Naves](/src/test/java/world/to/meet/techtest/unit)
   
8. **Desarrollar un @Aspect que añada una línea de log cuando nos piden una nave con un id negativo:**
    - He implementado un aspecto de Spring que intercepta las llamadas al método de consulta de una nave y registra un log
    cuando se solicita una nave con un id negativo.
    - [Aspecto de Log](/src/main/java/world/to/meet/techtest/aspect/ShipAspect.java)

9. **Gestión centralizada de excepciones:**
    - He implementado un controlador de excepciones global que maneja todas las excepciones lanzadas por la aplicación y
    devuelve respuestas HTTP apropiadas.
    - [Controlador de Excepciones Global](/src/main/java/world/to/meet/techtest/exception/GlobalExceptionHandler.java)
   
10. **Utilizar cachés de algún tipo:**
    - He implementado caché de Spring para las consultas de naves por id y por nombre. He utilizado la anotación `@Cacheable`
    para habilitar la caché en los métodos de consulta y la anotación `@CacheEvict` para limpiar la caché cuando se modifica
    o elimina una nave.
    - [Servicio de Naves](/src/main/java/world/to/meet/techtest/service/ShipService.java)

11. Utilizar alguna librería que facilite el mantenimiento de los scripts DDL de base de datos:
    - He utilizado la librería `flyway` para mantener los scripts DDL de la base de datos. 
    - [Scripts DDL de Base de Datos](/src/main/resources/db)

12. Test de integración:
    - He implementado test de integración end to end con MockMvc para probar los controladores REST de la aplicación.
    - [Test de Integración](/src/test/java/world/to/meet/techtest/integration)

13. Presentar la aplicación dockerizada:
    - He implementado un Dockerfile para empaquetar la aplicación en un contenedor de Docker. Luego también
    tengo un docker-compose.yml para levantar la aplicación junto con un contenedor de Kafka.
    - [Dockerfile](/Dockerfile)
    - [docker-compose.yml](/docker-compose.yml)

14. Documentación de la API:
    - He implementado la documentación de la API con Swagger. La documentación de la API se puede encontrar en
    `http://localhost:8080/swagger-ui.html` una vez que la aplicación está levantada.

15. Seguridad del API:
    - He implementado seguridad del API con Spring Security. He habilitado la autenticación básica con un usuario administrador
    por defecto y he asegurado todos los endpoints de la aplicación.
    - [Configuración de Spring Security](/src/main/java/world/to/meet/techtest/config/SecurityConfig.java)

16. Implementar algún consumer/producer para algún broker (Rabbit, Kafka, etc):
    - He implementado un producer para Kafka que envía la información de las naves creadas, modificadas o eliminadas a un tópico de Kafka.
    - [Aspecto de Kafka](/src/main/java/world/to/meet/techtest/aspect/KafkaAspect.java)

## Cosas que se podrían mejorar:

Anoto por aquí cosas rapidas que se podrian añadir a mi aplicación para mejorarla:

- Implementar un servicio de autenticación y autorización más robusto con JWT.
- Automatizar CI/CD con GitHub Actions.
- Implementar verificacion con SonarQube.
