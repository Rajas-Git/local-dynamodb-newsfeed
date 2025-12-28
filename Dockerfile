# Dockerfile
# start with a base image - linux with Java 21 installed
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy the JAR (produced by mvn package)
COPY target/*.jar app.jar

# Run as non-root for better security
USER 1000

# when the container starts run the Spring Boot app on port 8080
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]