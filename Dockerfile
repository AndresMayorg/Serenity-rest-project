# Usa una imagen base oficial de Jenkins
FROM jenkins/jenkins:lts

# Instala Java 8 o mayor (es necesario para ejecutar Serenity y Cucumber)
USER root
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    git \
    && apt-get clean

# Configura el entorno para Java
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Instala dependencias de Maven y ejecuta las pruebas
WORKDIR /usr/share/jenkins/ref

# Clona el repositorio de tu proyecto (puedes hacer esto también fuera del Dockerfile)
RUN git clone https://github.com/tu_usuario/tu_proyecto.git

# Cambia al directorio de tu proyecto clonado
WORKDIR /usr/share/jenkins/ref/tu_proyecto

# Instala las dependencias de Maven (esto descargará las librerías necesarias)
RUN mvn clean install

# Copia los archivos de configuración de Jenkins y plugins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

# Expone el puerto donde Jenkins escucha
EXPOSE 8080

# Comando para ejecutar Jenkins al iniciar el contenedor
CMD ["java", "-jar", "/usr/share/jenkins/jenkins.war"]
