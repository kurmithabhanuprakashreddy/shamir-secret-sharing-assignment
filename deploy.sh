#!/bin/bash

# Nuclear Code Recovery Deployment Script
# This script builds and deploys the application

set -e  # Exit on any error

echo "🚀 Starting Nuclear Code Recovery deployment..."

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 11 or higher."
    exit 1
fi

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven 3.6 or higher."
    exit 1
fi

echo "✅ Prerequisites check passed"

# Clean and build the project
echo "📦 Building project with Maven..."
mvn clean package

if [ $? -eq 0 ]; then
    echo "✅ Build successful!"
else
    echo "❌ Build failed!"
    exit 1
fi

# Check if JAR file was created
if [ ! -f "target/nuclear-code-recovery-1.0.0.jar" ]; then
    echo "❌ JAR file not found. Build may have failed."
    exit 1
fi

echo "🎯 JAR file created successfully!"

# Ask user for deployment method
echo ""
echo "Choose deployment method:"
echo "1) Run locally with Java"
echo "2) Deploy with Docker"
echo "3) Deploy with Docker Compose"
echo "4) Exit"

read -p "Enter your choice (1-4): " choice

case $choice in
    1)
        echo "🏃 Running application locally..."
        java -jar target/nuclear-code-recovery-1.0.0.jar
        ;;
    2)
        echo "🐳 Building Docker image..."
        docker build -t nuclear-code-recovery .
        echo "🚀 Running Docker container..."
        docker run --rm nuclear-code-recovery
        ;;
    3)
        echo "🐳 Deploying with Docker Compose..."
        docker-compose up --build
        ;;
    4)
        echo "👋 Exiting..."
        exit 0
        ;;
    *)
        echo "❌ Invalid choice. Exiting..."
        exit 1
        ;;
esac

echo "✅ Deployment completed!" 