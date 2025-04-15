#  Punto 8 - Despliegue con Docker

Este apartado corresponde al punto 8 del parcial, que consiste en preparar los microservicios del sistema educativo para ser ejecutados usando **Docker** y **Docker Compose**. Esto permite levantar todos los servicios fácilmente, sin tener que ejecutarlos manualmente uno por uno.

---

##  ¿Qué se hizo?

- Se creó un archivo `Dockerfile` en la raíz de cada microservicio (`auth`, `asignaturas`, `matriculas`, `eureka`, `config`).
- Se generaron los archivos `.jar` de cada microservicio usando `mvn clean package`.
- Se creó un archivo `docker-compose.yml` que define todos los contenedores y permite levantarlos de forma automática.
- Se organizaron las carpetas para que el archivo `docker-compose.yml` esté en la raíz del proyecto, y cada microservicio tenga su respectivo `Dockerfile`.

---

##  ¿Cómo se corre?

1. En cada microservicio, ejecutar:
   ```bash
   mvn clean package
   ```
   Esto generará el `.jar` dentro de la carpeta `/target`.

2. Desde la carpeta raíz del proyecto (donde está el `docker-compose.yml`), ejecutar:
   ```bash
   docker-compose up --build
   ```

3. Docker va a construir las imágenes, crear los contenedores y arrancar todos los microservicios automáticamente.

---

##  Verificación

Una vez levantados, se puede acceder a:

- `http://localhost:8761` → Eureka Server
- `http://localhost:8888` → Config Server
- `http://localhost:8080` → Auth Service
- `http://localhost:8081` → Asignaturas Service
- `http://localhost:8082` → Matrículas Service

---

##  Estructura esperada

```
/sistema-educativo/
├── auth-service/
│   └── Dockerfile
├── asignaturas-service/
│   └── Dockerfile
├── matriculas-service/
│   └── Dockerfile
├── eureka-server/
│   └── Dockerfile
├── config-server/
│   └── Dockerfile
├── docker-compose.yml
├── README-docker.md (este archivo)
```

---

##  Conclusión

El uso de Docker permite que todo el sistema educativo pueda desplegarse rápidamente y sin depender del entorno del desarrollador.  
Gracias a `docker-compose`, se logra un entorno totalmente replicable, ideal para pruebas, demostraciones o despliegue en producción.

 *Este README documenta la implementación del punto 8 del parcial*
