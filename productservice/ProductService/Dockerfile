# Use an official OpenJDK runtime as the base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/ProductService-0.0.1-SNAPSHOT.jar /app/ProductService-0.0.1-SNAPSHOT.jar


# Expose the port the app will run on (default is 8080 for Spring Boot)
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "/app/ProductService-0.0.1-SNAPSHOT.jar"]
