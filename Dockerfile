# Use Amazon Corretto's OpenJDK 17 base image for ARM64 architecture
FROM amazoncorretto:17-alpine

# Set the working directory
WORKDIR /app

# Copy the Maven build file
COPY pom.xml .

# Copy the source code
COPY src ./src

# Install Maven
RUN apk add --no-cache maven

# Build the application
RUN mvn clean package

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
