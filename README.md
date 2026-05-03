# Microservicio de Necesidades - Proyecto Donaton

Este repositorio contiene el microservicio de **Necesidades**, un componente modular desarrollado con **Spring Boot 3.x**. Su objetivo principal es la gestión independiente de los requerimientos críticos solicitados por la comunidad, asegurando que la solución técnica esté alineada con las necesidades del cliente.

## 🏗️ Arquitectura y Patrones de Diseño
Este microservicio se ha construido utilizando un arquetipo **Maven**, garantizando una estructura escalable y eficiente según los estándares de la evaluación

*   **📦 Arquetipo Maven**: Estructura estandarizada que asegura coherencia técnica en todo el ecosistema de backend
*   **📂 Repository Pattern**: Implementado para asegurar el desacoplamiento entre la lógica de negocio y la persistencia de datos mediante **Spring Data JPA**
*   **🌐 Microservices Architecture**: Permite el despliegue y escalado independiente de la lógica de requerimientos críticos

## 🛠️ Tecnologías Utilizadas
*   **☕ Java 17**: Lenguaje base para una ejecución robusta y moderna.
*   **🍃 Spring Boot 3.x**: Framework especializado en la creación de microservicios de fácil configuración.
*   **📊 Spring Data JPA**: Utilizado para una gestión de datos ágil y eficiente.
*   **🔨 Maven**: Herramienta principal para la gestión de dependencias y construcción del proyecto.

## 📂 Estructura del Componente
El código fuente está organizado siguiendo el formato de entrega solicitado para facilitar la mantenibilidad:
*   `controller/`: Endpoints REST que permiten la comunicación con el BFF.
*   `service/`: Capa que contiene la lógica de negocio específica para la gestión de necesidades.
*   `repository/`: Interfaces dedicadas a la persistencia y consulta de datos.
*   `model/`: Clases de entidad que representan el modelo de datos.

## 🚀 Instalación y Ejecución

1.  **📋 Requisitos**:
    *   Java 17 instalado.
    *   Maven 3.8+.

2.  **⚙️ Configuración**:
    El servicio está configurado para operar de forma predeterminada en el puerto `8083`. Puedes revisar o ajustar este valor en `src/main/resources/application.properties`.

3.  **▶️ Ejecución**:
    ```bash
    mvn spring-boot:run
    ```

## 🧪 Pruebas y Calidad
*   **✅ Pruebas Unitarias**: El proyecto incluye validaciones unitarias para asegurar que el componente backend sea eficiente y robusto.
*   **🧹 Clean Code**: Se aplica una nomenclatura clara y una estructura ordenada para favorecer la colaboración en equipo y el control de versiones.

---
