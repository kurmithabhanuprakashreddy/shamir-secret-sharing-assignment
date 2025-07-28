# Deployment Guide - Nuclear Code Recovery System

## 🚀 Quick Start (Recommended)

### Option 1: Simple Run (No Maven Required)
```bash
./run.sh
```

### Option 2: Manual Execution
```bash
javac *.java
java Main
```

### Option 3: Using JAR File
```bash
java -jar nuclear-code-recovery.jar
```

## 📋 Prerequisites

- **Java 11 or higher** ✅ (Detected: OpenJDK 11.0.27)
- **Maven 3.6+** (Optional - for advanced builds)

## 🛠️ Installation Options

### 1. Install Maven (Optional)
```bash
# macOS with Homebrew
brew install maven

# Ubuntu/Debian
sudo apt update
sudo apt install maven

# CentOS/RHEL
sudo yum install maven
```

### 2. Install Docker (Optional)
```bash
# macOS
brew install --cask docker

# Ubuntu/Debian
sudo apt install docker.io
```

## 🎯 Deployment Methods

### Method 1: Local Execution (Current Setup)
```bash
# Compile and run
javac *.java
java Main

# Expected output:
# Recovered Nuclear Code Secret: 12
```

### Method 2: JAR Deployment
```bash
# Create JAR file
jar cf nuclear-code-recovery.jar *.class

# Run JAR
java -jar nuclear-code-recovery.jar
```

### Method 3: Maven Build (If Maven is installed)
```bash
# Build with Maven
mvn clean package

# Run the built JAR
java -jar target/nuclear-code-recovery-1.0.0.jar
```

### Method 4: Docker Deployment
```bash
# Build Docker image
docker build -t nuclear-code-recovery .

# Run container
docker run --rm nuclear-code-recovery
```

### Method 5: Docker Compose
```bash
# Deploy with Docker Compose
docker-compose up --build
```

## ☁️ Cloud Deployment

### AWS Lambda
1. Package as JAR file
2. Upload to AWS Lambda
3. Configure Java 11 runtime
4. Set handler: `Main::main`

### Google Cloud Functions
1. Use Google Cloud CLI
2. Deploy with Java 11 runtime
3. Set entry point to `Main.main`

### Azure Functions
1. Use Azure CLI
2. Configure for Java 11
3. Set function entry point

### Kubernetes
```bash
# Apply Kubernetes deployment
kubectl apply -f deployment.yaml

# Check deployment status
kubectl get pods
```

## 🔧 Configuration

### Environment Variables
```bash
# Set Java options
export JAVA_OPTS="-Xmx512m -Xms256m"

# Run with custom options
java $JAVA_OPTS Main
```

### Custom Parameters
Edit `Main.java` to modify:
- Number of shares (N)
- Required shares (K)
- Share expressions
- Prime modulus

## 📊 Monitoring & Logging

### Basic Logging
```bash
# Run with output redirection
java Main > output.log 2>&1
```

### Performance Monitoring
```bash
# Run with JVM monitoring
java -XX:+PrintGC -XX:+PrintGCTimeStamps Main
```

## 🧪 Testing

### Unit Testing
```bash
# If using Maven
mvn test

# Manual testing
java Main
# Verify output: "Recovered Nuclear Code Secret: 12"
```

### Integration Testing
```bash
# Test with different share combinations
# Modify rawShares array in Main.java
```

## 🔒 Security Considerations

1. **Input Validation**: Add validation for share expressions
2. **Error Handling**: Implement proper exception handling
3. **Logging**: Add security logging for production
4. **Access Control**: Implement proper access controls

## 📈 Performance Optimization

### JVM Tuning
```bash
# Optimize for performance
java -Xms1g -Xmx2g -XX:+UseG1GC Main

# Optimize for memory
java -Xms256m -Xmx512m -XX:+UseSerialGC Main
```

### Application Tuning
- Optimize combinatorial generation
- Implement caching for repeated calculations
- Use parallel processing for large datasets

## 🚨 Troubleshooting

### Common Issues

1. **"command not found: mvn"**
   - Install Maven or use manual compilation
   - Use `./run.sh` for simple execution

2. **"command not found: java"**
   - Install Java 11 or higher
   - Set JAVA_HOME environment variable

3. **Compilation errors**
   - Check Java version compatibility
   - Verify all source files are present

4. **Runtime errors**
   - Check for missing dependencies
   - Verify input data format

### Debug Mode
```bash
# Run with debug information
java -Djava.util.logging.config.file=logging.properties Main
```

## 📞 Support

For deployment issues:
1. Check prerequisites
2. Verify file permissions
3. Review error logs
4. Test with simple execution first

## 🎉 Success Criteria

Your deployment is successful when:
- ✅ Application compiles without errors
- ✅ Application runs and produces output
- ✅ Output shows: "Recovered Nuclear Code Secret: 12"
- ✅ No runtime exceptions occur 