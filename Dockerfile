
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/veterinary_clinic-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]