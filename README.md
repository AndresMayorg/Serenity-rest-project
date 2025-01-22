# Serenity Rest Project

## Descripción
Este proyecto de automatización de pruebas de servicios REST utiliza Serenity BDD con Cucumber y Java. Implementa el patrón de diseño Page Object Model (POM) para una mejor organización del código y reutilización de los componentes.

## Stack Tecnológico
- **Java 17**: Lenguaje de programación utilizado.
- **Maven**: Gestión de dependencias y construcción del proyecto.
- **Serenity BDD (4.2.0)**: Framework de pruebas para la generación de reportes y ejecución de pruebas BDD.
- **Cucumber con Serenity (4.2.0)**: Framework para la escritura de pruebas en lenguaje Gherkin.
- **Serenity Rest**: Extensión de Serenity para pruebas de servicios REST.
- **Hamcrest**: Librería para validaciones y aserciones.
- **Patrón de diseño POM (Page Object Model)**: Organización de las pruebas para mejorar la mantenibilidad.

## Estructura del Proyecto
```
serenity-rest-project/
│-- .idea/                           # Configuración del entorno de desarrollo
│-- src/
│   ├── test/
│   │   ├── java/
│   │   │   ├── runners/             # Contiene los archivos de ejecución de las pruebas (TestRunner.java)
│   │   │   ├── steps/               # Implementación de los pasos definidos en los archivos feature
│   │   │   ├── task/                # Lógica de interacción con las APIs
│   │   │   ├── utils/               # Clases de utilidad y configuración
│   │   ├── resources/
│   │   │   ├── data.SongsDetectRequests/  # Archivos de datos de prueba
│   │   │   ├── features/            # Archivos de características en Gherkin (BDD)
│-- .gitignore                       # Archivo para ignorar archivos en Git
│-- Dockerfile                       # Archivo para la creación de contenedores Docker
│-- Jenkinsfile                      # Archivo de configuración para CI/CD con Jenkins
│-- pom.xml                           # Archivo de configuración de Maven con dependencias
│-- serenity.properties               # Configuración de Serenity
```

## Instalación y Configuración
### Prerrequisitos
- Tener **Java 17** instalado y configurado en el `PATH`.
- Tener **Maven** instalado y configurado.
- Tener un IDE como IntelliJ IDEA o Eclipse.

### Instalación
1. Clonar el repositorio:
   ```sh
   git clone <URL_DEL_REPOSITORIO>
   ```
2. Navegar al directorio del proyecto:
   ```sh
   cd serenity-rest-project
   ```
3. Instalar dependencias con Maven:
   ```sh
   mvn clean install
   ```

## Ejecución de Pruebas
### Ejecutar pruebas con Maven
Para ejecutar todas las pruebas del proyecto:
```sh
mvn clean verify
```

Para ejecutar una suite específica:
```sh
mvn clean verify -Dcucumber.options="--tags @SongsDetect"
```

## Reportes de Serenity
Después de la ejecución de las pruebas, Serenity genera un reporte en la siguiente ruta:
```
target/site/serenity/index.html
```
Para abrirlo en un navegador:
```sh
open target/site/serenity/index.html
```

## Contribución
Si deseas contribuir al proyecto:
1. Crea un **fork** del repositorio.
2. Crea una nueva rama (`git checkout -b feature-nueva-funcionalidad`).
3. Realiza tus cambios y haz un commit (`git commit -m 'Añadir nueva funcionalidad'`).
4. Sube los cambios (`git push origin feature-nueva-funcionalidad`).
5. Crea un **Pull Request**.
