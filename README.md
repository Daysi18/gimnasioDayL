# API de Gestión de Gimnasio

Aplicación backend desarrollada con **Spring Boot** y **PostgreSQL** para administrar socios, clases grupales y máquinas del gimnasio.

## Tecnologías
- Java & Spring Boot (Spring Web, Spring Data JPA, Lombok)
- Maven
- PostgreSQL
- Postman

## Configuración de base de datos
1. Crea la base de datos `gimnasiodb` en PostgreSQL.
2. Ejecuta el script SQL del repositorio para crear el usuario, tablas y datos iniciales.
3. Revisa `src/main/resources/application.properties` para confirmar credenciales y el puerto (`8082`).

## Ejecución
Abre el proyecto en IntelliJ IDEA y ejecuta `GimnasioApplication`, o corre desde terminal en la raíz del proyecto:

```bash
./mvnw spring-boot:run