# Use the official Maven image to build the application
FROM maven:3.8.4-openjdk-17-slim AS build

# Copiar el proyecto al contenedor
COPY ./workdir /usr/src/app

# Establecer el directorio de trabajo
WORKDIR /usr/src/app

# Ejecutar el build de Maven
RUN mvn clean install package -U -DskipTests

# Crear una nueva imagen que solo contenga el JAR
FROM openjdk:17-slim

# Explonemos el puerto 8080
EXPOSE 8080

# Copiar el JAR construido anteriormente al contenedor
COPY --from=build /usr/src/app/target/techtest-0.0.1-SNAPSHOT.jar /usr/exec/techtest-0.0.1-SNAPSHOT.jar

# Dejar solo el jar
RUN rm -rf /usr/src/app

# Establecer el directorio de trabajo
WORKDIR /usr/exec

# Ejecutar el JAR cuando se inicie el contenedor
CMD ["java", "-jar", "techtest-0.0.1-SNAPSHOT.jar"]