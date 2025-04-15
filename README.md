#  Microservicio: Autenticación y Usuarios (auth-service)

Este microservicio gestiona el **registro, login, validación de tokens y roles de los usuarios** (profesores y estudiantes) del sistema.  
Genera tokens JWT que permiten el acceso controlado a otros microservicios.

---

## Inicialización del Microservicio

Para compilar y ejecutar este microservicio de forma local, se usa uno de los siguientes comandos:

```bash
mvn clean package
java -jar target/*.jar
```

O usando el wrapper de Maven:

```bash
./mvnw spring-boot:run
```

## Colección Postman

Este microservicio cuenta con una colección de Postman incluida en el proyecto para facilitar las pruebas de los endpoints.

## Ejecución de Tests

Este proyecto incluye pruebas automáticas (unitarias y/o de integración).  
Las ejecutaremos con:

```bash
./mvnw test
```


##  Funcionalidades

- Registro de usuarios (estudiante o profesor)
- Login con generación de token JWT
- Validación del token desde otros microservicios
- Gestión de roles (`USER` y `ADMIN`)
- Acceso a rutas restringidas según el rol (como `/admin/**` y `/usuarios/**`)

---

##  Endpoints

| Método | Ruta              | Descripción                       |
|--------|-------------------|-----------------------------------|
| POST   | /auth/register     | Registra un nuevo usuario         |
| POST   | /auth/login        | Inicia sesión y genera token      |
| GET    | /auth/validate-token | Valida el token recibido        |
| GET    | /admin/secret         | Devuelve datos restringidos para administradores |
| GET    | /usuarios             | Obtiene todos los usuarios registrados           |
| GET    | /usuarios/{id}        | Obtiene un usuario por su ID                     |
| POST   | /usuarios             | Crea un nuevo usuario con rol y vínculo de auth  |
| GET    | /auth/validate-token  | Valida el token recibido        |

** La interaccion con los demás microservicios debe ser con el ID de usuarios, el ID generado por AUTH solo se usa cuando se crea un usuario


---

##  Seguridad

- Usa JWT con firma HS512
- Los tokens incluyen email y rol
- Se validan en cada microservicio cliente con `JwtFilter` o `FeignClient`

---

##  Entidades

- `AuthUsuario`: usuario con email, password, tipo y rol
- `Rol`: ADMIN o USER

---

##  Tecnologías

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- jjwt
- PostgreSQL
- Lombok

---

##  Autores

- Juan Esteban Rodríguez Murillo
- Juan Sebastián Rodríguez Avendaño

Desarrollado como parte de práctica universitaria de arquitectura de microservicios.
