# Usa una imagen base oficial de Jenkins
FROM jenkins/jenkins:lts

# Instala Java 17 y otras dependencias necesarias
USER root
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    git \
    && apt-get clean

# Configura el entorno para Java
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Cambia al usuario de Jenkins
USER jenkins

# Establece el directorio de trabajo
WORKDIR /usr/share/jenkins/ref

# Clona el repositorio de tu proyecto (puedes hacer esto también fuera del Dockerfile)
RUN git clone https://github.com/AndresMayorg/Serenity-rest-project.git

# Cambia al directorio de tu proyecto clonado
WORKDIR /usr/share/jenkins/ref/Serenity-rest-project

# Instala las dependencias de Maven (esto descargará las librerías necesarias)
RUN mvn clean install

# Expone el puerto donde Jenkins escucha
EXPOSE 8080

# Comando para ejecutar Jenkins al iniciar el contenedor
CMD ["java", "-jar", "/usr/share/jenkins/jenkins.war"]
