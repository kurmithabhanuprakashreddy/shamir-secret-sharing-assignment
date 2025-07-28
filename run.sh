#!/bin/bash

# Simple run script for Nuclear Code Recovery

echo "🚀 Nuclear Code Recovery System"
echo "================================"

# Check if Java is available
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 11 or higher."
    exit 1
fi

# Check if compiled classes exist
if [ ! -f "Main.class" ]; then
    echo "📦 Compiling Java files..."
    javac *.java
    if [ $? -ne 0 ]; then
        echo "❌ Compilation failed!"
        exit 1
    fi
fi

echo "✅ Running Nuclear Code Recovery..."
echo ""

# Run the application
java Main

echo ""
echo "✅ Execution completed!" 