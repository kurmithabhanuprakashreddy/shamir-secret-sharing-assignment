# Use OpenJDK 11 slim image as base
FROM openjdk:11-jre-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file
COPY target/nuclear-code-recovery-1.0.0.jar app.jar

# Expose port (if needed for future web interface)
EXPOSE 8080

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "app.jar"] 