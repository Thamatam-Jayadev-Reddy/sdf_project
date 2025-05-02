# Custom Arithmetic Library

This repository implements a custom Java-based arithmetic library for arbitrary-precision arithmetic. It includes two main classes:

- `AInteger` – for arbitrary-precision integer operations
- `AFloat` – for arbitrary-precision floating-point operations

##  Features

- Manual implementation of addition, subtraction, multiplication, and division
- String-based number storage for 1000 precision
- Handles negative numbers and normalization (removal of leading/trailing zeros)
- Floating-point logic that simulates real decimal behavior
- Fully object-oriented and immutable operations

##  Classes

### AInteger
Handles large integers with support for:
- `add(AInteger)`
- `subtract(AInteger)`
- `multiply(AInteger)`
- `divide(AInteger)`

### AFloat
Handles floating-point arithmetic with support for:
- `add(AFloat)`
- `subtract(AFloat)`
- `multiply(AFloat)`
- `divide(AFloat)`

##  How to Run

1. Compile and run using:
   ```bash
   javac src/AFloat.java src/AInteger.java MyInfArith.java
   java MyInfArith int add 123 456
   ```

2. Or use the provided Python script:
   ```bash
   python3 run.py int add 123 456
   ```


```



---

Made  by Thamatam Jayadev Reddy
