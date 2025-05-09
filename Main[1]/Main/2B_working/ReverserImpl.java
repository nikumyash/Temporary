package StringOperations;

public class ReverserImpl extends ReverserPOA {
    public String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}

