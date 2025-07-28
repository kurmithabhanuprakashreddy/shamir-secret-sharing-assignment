#!/bin/bash

# Interactive Nuclear Code Recovery System

echo "🚀 Nuclear Code Recovery System - Interactive Mode"
echo "=================================================="

# Check if Java is available
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 11 or higher."
    exit 1
fi

# Check if compiled classes exist
if [ ! -f "MainInteractive.class" ]; then
    echo "📦 Compiling Java files..."
    javac *.java
    if [ $? -ne 0 ]; then
        echo "❌ Compilation failed!"
        exit 1
    fi
fi

echo "✅ Running Interactive Nuclear Code Recovery..."
echo ""

# Run the interactive application
java MainInteractive

echo ""
echo "✅ Interactive session completed!" 