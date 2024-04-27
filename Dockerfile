# Use a base image with OpenJDK
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container at /app
COPY target/currency-0.0.1-SNAPSHOT.jar /app/currency-service.jar

# Expose the port your application runs on
EXPOSE 8080

# Specify the command to run your Spring Boot application
ENTRYPOINT ["java","-jar","/app/currency-service.jar"]
