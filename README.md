# Serenity Rest Automation Framework

![Java Version](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)
![Serenity BDD](https://img.shields.io/badge/Serenity_BDD-4.x-green?style=flat-square)
![Build](https://img.shields.io/badge/Build-Maven-blue?style=flat-square&logo=apache-maven)

## Descripción
Este proyecto es un framework de automatización para pruebas de servicios **REST**, construido sobre **Serenity BDD**, **Cucumber** y **Java 21**. Implementa un modelo de capas inspirado en el patrón **Page Object Model (POM)** adaptado a servicios, asegurando que el código sea mantenible, escalable y que genere documentación viva de alta calidad.

---

## Stack Tecnológico
* **Lenguaje:** Java 21 (LTS).
* **Gestor de Dependencias:** Maven.
* **Framework de Pruebas:** Serenity BDD.
* **BDD:** Cucumber con soporte para Gherkin.
* **Librería REST:** Serenity Rest (Rest-Assured wrapper).
* **Validaciones:** Hamcrest y AssertJ.
* **Manejo de Datos:** Apache POI (Soporte para Excel).

---

## Estructura del Proyecto
El proyecto sigue una organización lógica para separar la definición de los servicios de la lógica de las pruebas:

```text
src/
└── test/
    ├── java/
    │   ├── api.endpoints   # Definición de rutas, payloads y lógica de los servicios.
    │   ├── steps           # Implementación de los pasos Gherkin (Glue code).
    │   ├── runners         # Orquestadores para la ejecución de pruebas.
    │   └── utils           # Clases de apoyo (Lectura de Excel, configuraciones).
    └── resources/
        ├── data            # Datasets externos (DataFile.xlsx).
        ├── features        # Escenarios de negocio escritos en Gherkin.
        └── serenity.conf   # Configuración centralizada de ambientes y propiedades.
```

## Instalación y Configuración
### Prerrequisitos
- JDK 21 instalado y configurado en el JAVA_HOME.

- Maven 3.8+ instalado.

### Instalación
1. Clonar el repositorio:
   ```
   git clone <URL_DEL_REPOSITORIO>
   ```
2. Navegar al directorio del proyecto:
   ```
   cd serenity-rest-project
   ```
3. Descargar dependencias y compilar:
   ```
   mvn clean install -DskipTests
   ```

## Ejecución de Pruebas

Comandos de Consola

Para ejecutar todos los escenarios y generar el reporte
```
mvn clean verify -Dcucumber.filter.tags="@login"
```

Para ejecutar en un ambiente específico (si está configurado en serenity.conf):
```
mvn clean verify -Denvironment=staging
```

## Reportes de Ejecución
Serenity genera reportes detallados que incluyen los pasos ejecutados, el tiempo de respuesta y el detalle de los Requests/Responses de la API.

El reporte interactivo se encuentra en:
```
target/site/serenity/index.html
```
Para abrirlo automáticamente (macOS/Linux)::
```
open target/site/serenity/index.html
```

## Contribución
Si deseas contribuir al proyecto:
1. Crea un **fork** del repositorio.
2. Crea una nueva rama (`git checkout -b feature-nueva-funcionalidad`).
3. Realiza tus cambios y haz un commit (`git commit -m 'Añadir nueva funcionalidad'`).
4. Sube los cambios (`git push origin feature-nueva-funcionalidad`).
5. Crea un **Pull Request**.
