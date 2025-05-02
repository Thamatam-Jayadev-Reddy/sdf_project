# Custom Arithmetic Library

This repository implements a custom Java-based arithmetic library for arbitrary-precision arithmetic. It includes two main classes:

- `AInteger` – for arbitrary-precision integer operations
- `AFloat` – for arbitrary-precision floating-point operations

##  Features

- Manual implementation of addition, subtraction, multiplication, and division
- String-based number storage for unlimited precision
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

##  Example

```java
AInteger a = new AInteger("12345678901234567890");
AInteger b = new AInteger("987654321");
System.out.println(a.multiply(b).int_string);

AFloat f1 = new AFloat("12.345");
AFloat f2 = new AFloat("3.14");
System.out.println(f1.divide(f2).float_string);
```

##  Limitations

- Division by zero returns a string instead of throwing an exception
- Floating-point precision is manually controlled (fixed precision depth)
- No support yet for negative exponents or scientific notation


---

Made  by Thamatam Jayadev Reddy
