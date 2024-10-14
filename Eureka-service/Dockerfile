# Use a base image with Java
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/Eureka-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on
<<<<<<< HEAD
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
=======
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
>>>>>>> 805e628007244038449314416068d003a5021a74
