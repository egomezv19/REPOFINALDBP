# Forutec PT1

## Contexto

En el dinámico mundo de la tecnología y la información, Forutec PT1 surge como una solución integral para gestionar usuarios, comentarios, perfiles, publicaciones y suscripciones. Este proyecto está diseñado para ofrecer una plataforma robusta que permita la interacción fluida entre los diferentes componentes del sistema, asegurando una experiencia de usuario eficiente y satisfactoria.

## Objetivos del Proyecto

1. **Gestión de Usuarios**: Permitir la creación, actualización y eliminación de usuarios, asegurando la integridad y seguridad de la información personal.
2. **Manejo de Comentarios**: Facilitar la publicación y moderación de comentarios en diversas publicaciones, promoviendo una comunicación activa y constructiva.
3. **Perfiles de Usuario**: Proporcionar a los usuarios la capacidad de gestionar sus perfiles, incluyendo información adicional relevante.
4. **Administración de Publicaciones**: Permitir a los usuarios crear, actualizar y eliminar publicaciones de manera sencilla y eficiente.
5. **Gestión de Suscripciones**: Implementar un sistema de suscripciones que permita a los usuarios seguir categorías y publicaciones de interés.

## Funcionalidades Clave

- **CRUD de Usuarios**: Operaciones de creación, lectura, actualización y eliminación de usuarios.
- **Publicaciones**: Sistema de gestión de publicaciones, incluyendo fechas automáticas de creación.
- **Comentarios**: Sistema de gestión de comentarios con relaciones a publicaciones y usuarios.
- **Perfiles**: Gestión de perfiles de usuario con información adicional.
- **Suscripciones**: Sistema de suscripciones para seguir categorías y publicaciones.

## Tecnologías Utilizadas

- **Spring Boot**: Framework para la creación de aplicaciones Java robustas y escalables.
- **Maven**: Herramienta de gestión y construcción de proyectos.
- **JPA/Hibernate**: Frameworks para el manejo de la persistencia de datos.
- **Postman**: Herramienta para pruebas de API.

## Instalación

Para instalar y ejecutar este proyecto localmente, sigue estos pasos:

1. Clona el repositorio:
    ```bash
    git clone https://github.com/tu_usuario/forutec_pt1.git
    ```

2. Navega al directorio del proyecto:
    ```bash
    cd forutec_pt1
    ```

3. Configura tu base de datos en el archivo `application.properties`.

4. Ejecuta el proyecto:
    ```bash
    mvn spring-boot:run
    ```

## Endpoints Principales

### Usuarios
- `GET /api/usuarios`
- `GET /api/usuarios/{id}`
- `POST /api/usuarios`
- `PUT /api/usuarios/{id}`
- `PATCH /api/usuarios/{id}`
- `DELETE /api/usuarios/{id}`

### Comentarios
- `GET /api/comentarios`
- `GET /api/comentarios/{id}`
- `POST /api/comentarios`
- `PUT /api/comentarios/{id}`
- `PATCH /api/comentarios/{id}`
- `DELETE /api/comentarios/{id}`

### Perfiles
- `GET /api/perfiles`
- `GET /api/perfiles/{id}`
- `POST /api/perfiles`
- `PUT /api/perfiles/{id}`
- `PATCH /api/perfiles/{id}`
- `DELETE /api/perfiles/{id}`

### Publicaciones
- `GET /api/publicaciones`
- `GET /api/publicaciones/{id}`
- `POST /api/publicaciones`
- `PUT /api/publicaciones/{id}`
- `PATCH /api/publicaciones/{id}`
- `DELETE /api/publicaciones/{id}`

### Suscripciones
- `GET /api/suscripciones`
- `GET /api/suscripciones/{id}`
- `POST /api/suscripciones`
- `DELETE /api/suscripciones/{id}`

## Gestión del Proyecto con GitHub

En Forutec PT1, hemos utilizado varias funcionalidades de GitHub para gestionar nuestro proyecto de manera eficiente y colaborativa:

- **Issues**: Utilizamos issues para rastrear tareas, bugs y nuevas funcionalidades. Cada issue está claramente etiquetado y asignado a los miembros del equipo correspondientes.
- **Pull Requests**: Las pull requests nos permiten revisar y discutir cambios antes de fusionarlos en la rama principal. Usamos revisiones de código para mantener la calidad y consistencia del código.
- **Actions**: Implementamos GitHub Actions para automatizar flujos de trabajo como pruebas y despliegues, asegurando que cada cambio pase por un proceso de integración continua.
- **Projects**: Utilizamos GitHub Projects para organizar y planificar nuestro trabajo. Creamos tableros con columnas como "To Do", "In Progress" y "Done" para visualizar el progreso.
- **Flujo de Trabajo (Workflow)**: Seguimos un flujo de trabajo basado en Gitflow, utilizando ramas específicas para desarrollo.

## Contribuyendo

¡Las contribuciones son bienvenidas! Si deseas contribuir, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz un commit (`git commit -m 'Agregar nueva funcionalidad'`).
4. Empuja tus cambios a la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.



