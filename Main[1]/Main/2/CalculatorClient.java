import CalculatorApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            // Initialize the ORB
            ORB orb = ORB.init(args, null);

            // Resolve the name service reference
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Resolve the Calculator object from the naming service
            Calculator calc = CalculatorHelper.narrow(ncRef.resolve_str("Calculator"));

            // Perform calculations
            System.out.println("Add: " + calc.add(10, 5));
            System.out.println("Subtract: " + calc.subtract(10, 5));
            System.out.println("Multiply: " + calc.multiply(10, 5));
            System.out.println("Divide: " + calc.divide(10, 5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

