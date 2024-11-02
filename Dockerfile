# Use Amazon Corretto's OpenJDK 17 base image for ARM64 architecture
FROM amazoncorretto:17-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose ports for the application and remote debugging
EXPOSE 8080 5005

# Run the application with remote debugging options
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]
