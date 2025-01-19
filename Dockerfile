# Use a Maven image for building the application
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy the source code
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Use a lightweight JRE image for running the application
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the .jar file from the builder stage
COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]
