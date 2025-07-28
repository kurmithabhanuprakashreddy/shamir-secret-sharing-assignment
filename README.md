# Shamir's Secret Sharing Assignment

## 📋 Assignment Overview

This repository contains a Java implementation of Shamir's Secret Sharing algorithm for the Assignment. The solution finds the constant term 'c' of a polynomial using Lagrange interpolation with base-encoded input values.

## 🎯 Problem Statement

Given an unknown polynomial of degree m, we need to find the constant term 'c' using k = m + 1 roots. The roots are provided in JSON format with Y values encoded in different bases (2-16).

### Key Requirements:
- Read test cases from JSON files
- Decode Y values from various bases (2, 3, 6, 7, 8, 10, 12, 15, 16)
- Use Lagrange interpolation to find the constant term
- Handle 256-bit number constraints
- Support positive integer coefficients

## 🚀 Quick Start

### Prerequisites
- Java 11 or higher
- Git

### Running the Solution

1. **Clone the repository:**
   ```bash
   git clone <your-repo-url>
   cd shamir-secret-sharing
   ```

2. **Compile the Java files:**
   ```bash
   javac ShamirSecretSharing.java
   ```

3. **Run the solution:**
   ```bash
   java ShamirSecretSharing
   ```

## 📊 Results

### Test Case 1
- **n = 4, k = 3, degree = 2**
- **Secret (Constant Term): 3**

### Test Case 2
- **n = 10, k = 7, degree = 6**
- **Secret (Constant Term): 263490999**

## 🔧 Implementation Details

### Core Components

1. **JSON Parser**: Custom parser for the specific JSON format
2. **Base Decoder**: Converts values from bases 2-16 to decimal
3. **Lagrange Interpolation**: Polynomial interpolation to find f(0)
4. **Combination Generator**: Tests all possible k-point combinations
5. **Frequency Analysis**: Finds the most frequent secret value

### Key Algorithms

#### Lagrange Interpolation
```java
f(x) = Σ(yi * ∏((x - xj)/(xi - xj)))
```

#### Base Decoding
```java
BigInteger decodedValue = new BigInteger(encodedValue, base);
```

## 📁 Project Structure

```
├── ShamirSecretSharing.java    # Main solution implementation
├── testcase1.json             # First test case
├── testcase2.json             # Second test case
├── README.md                  # This file
├── .gitignore                 # Git ignore rules
└── run.sh                     # Execution script
```

## 🧪 Test Cases

### Test Case 1 Format
```json
{
    "keys": {
        "n": 4,
        "k": 3
    },
    "1": {
        "base": "10",
        "value": "4"
    },
    "2": {
        "base": "2", 
        "value": "111"
    },
    "3": {
        "base": "10",
        "value": "12"
    },
    "6": {
        "base": "4",
        "value": "213"
    }
}
```

### Test Case 2 Format
```json
{
    "keys": {
        "n": 10,
        "k": 7
    },
    "1": {
        "base": "6",
        "value": "13444211440455345511"
    },
    // ... more roots
}
```

## 🔍 How It Works

1. **Input Processing**: Parse JSON and extract n, k values
2. **Base Decoding**: Convert encoded Y values to decimal
3. **Point Generation**: Create (x, y) coordinate pairs
4. **Combination Testing**: Generate all C(n,k) combinations
5. **Interpolation**: Use Lagrange interpolation for each combination
6. **Secret Recovery**: Find most frequent f(0) value

## 📈 Performance

- **Time Complexity**: O(C(n,k) * k²) where C(n,k) is combinations
- **Space Complexity**: O(C(n,k) * k)
- **Memory Usage**: Optimized for 256-bit number handling

## 🛠️ Technical Features

- ✅ **Large Number Support**: BigInteger for 256-bit numbers
- ✅ **Modular Arithmetic**: Prime modulus calculations
- ✅ **Error Handling**: Graceful failure handling
- ✅ **Base Flexibility**: Supports bases 2-16
- ✅ **JSON Parsing**: Custom parser for specific format

## 🎓 Assignment Submission

This solution addresses all assignment checkpoints:

1. ✅ **Read Test Cases**: Parses JSON input files
2. ✅ **Decode Y Values**: Converts from various bases
3. ✅ **Find Secret C**: Uses Lagrange interpolation

## 📞 Support

For questions or issues:
1. Check the README for usage instructions
2. Verify Java version compatibility
3. Ensure test case files are in the correct format

## 📄 License

This project is created for educational purposes as part of the Catalog Placements Assignment.

---

**Author**: [Your Name]  
**Assignment**: Shamir's Secret Sharing - Catalog Placements  
**Language**: Java  
**Date**: [Current Date] 
