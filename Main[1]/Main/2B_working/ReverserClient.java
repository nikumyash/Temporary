import StringOperations.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

import java.util.Scanner;

public class ReverserClient {
    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            Reverser reverserImpl = ReverserHelper.narrow(ncRef.resolve_str("Reverse"));

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a string to reverse: ");
            String input = scanner.nextLine();

            String result = reverserImpl.reverseString(input);
            System.out.println("Reversed string: " + result);

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
}

