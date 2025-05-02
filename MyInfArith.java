import src.AFloat;
import src.AInteger;

public class MyInfArith {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Enter a valid input.");
            return;
        }

        String type = args[0];
        String operation = args[1];
        String num1 = args[2];
        String num2 = args[3];

        if (type.equals("int")) {
            AInteger int1 = new AInteger(num1);
            AInteger int2 = new AInteger(num2);

            if (operation.equals("add")) {
                System.out.println(int1.add(int2).int_string);
            } else if (operation.equals("sub")) {
                System.out.println(int1.subtract(int2).int_string);
            } else if (operation.equals("mul")) {
                System.out.println(int1.multiply(int2).int_string);
            } else if (operation.equals("div")) {
                System.out.println(int1.divide(int2).int_string);
            } else {
                System.out.println("Unsupported operation for int type.");
            }

        } else if (type.equals("float")) {
            AFloat float1 = new AFloat(num1);
            AFloat float2 = new AFloat(num2);

            if (operation.equals("add")) {
                System.out.println(float1.add(float2).float_string);
            } else if (operation.equals("sub")) {
                System.out.println(float1.subtract(float2).float_string);
            } else if (operation.equals("mul")) {
                System.out.println(float1.multiply(float2).float_string);
            } else if (operation.equals("div")) {
                System.out.println(float1.divide(float2).float_string);
            } else {
                System.out.println("Unsupported operation for float type.");
            }

        } else {
            System.out.println("Unsupported type. Use 'int' or 'float'.");
        }
    }
}
