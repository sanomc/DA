
FROM openjdk:22

COPY target/da-backend.jar .

EXPOSE 8080

# Startbefehl
ENTRYPOINT ["java", "-jar", "da-backend.jar"]
