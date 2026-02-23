# Arquitectura Monolítica - Spring Boot

## Descripción del Proyecto

Este proyecto corresponde al ejercicio de implementación de una **Arquitectura Monolítica** utilizando **Spring Boot**.

Se desarrolla una API REST básica para la gestión de productos, aplicando el patrón de arquitectura en capas:

- Controller
- Service
- Repository
- Entity

El sistema utiliza una base de datos embebida **H2**, permitiendo ejecutar y probar el proyecto sin configuraciones externas.

---

## Objetivos del Ejercicio

- Implementar una arquitectura monolítica.
- Aplicar separación de responsabilidades por capas.
- Utilizar Spring Data JPA.
- Implementar base de datos embebida.
- Exponer endpoints REST.
- Validar funcionamiento mediante pruebas.

---

## Tecnologías Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven
- Lombok

---

## La estructura del ejercico
```
monolitico
│
├── pom.xml
│
└── src
    └── main
        ├── java
        │   └── com
        │       └── udb
        │           └── monolitico
        │               ├── MonoliticoApplication.java
        │               │
        │               ├── controller
        │               │   └── ProductoController.java
        │               │
        │               ├── service
        │               │   └── ProductoService.java
        │               │
        │               ├── repository
        │               │   └── ProductoRepository.java
        │               │
        │               └── entity
        │                   └── Producto.java
        │
        └── resources
            ├── application.properties
            └── data
                └── monolitico_db.mv.db
```
---

## Explicación de Cada Carpeta

### entity
Contiene las clases que representan las tablas de la base de datos.

Ejemplo:
- `Producto.java` → Representa la entidad Producto.

---

### repository
Contiene interfaces que permiten acceder a la base de datos usando JPA.

Ejemplo:
- `ProductoRepository.java` → Permite realizar operaciones CRUD automáticamente.

---

### service
Contiene la lógica del sistema (del ejercicio).

Ejemplo:
- `ProductoService.java` → Gestiona la lógica para guardar y listar productos.

---

### controller
Expone los endpoints REST del sistema.

Ejemplo:
- `ProductoController.java` → Maneja solicitudes HTTP GET y POST.

---

## ¡ Cómo Ejecutar el Proyecto !

### 1️ Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
