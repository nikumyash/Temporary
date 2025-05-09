import java.rmi.*;

// Declare the interface as extending Remote to make it suitable for RMI
public interface ServerIntf extends Remote {
    
    // Declare the methods with the correct syntax, including throws RemoteException
    public double Addition(double num1, double num2) throws RemoteException;

    public double Subtraction(double num1, double num2) throws RemoteException;

    public double Multiplication(double num1, double num2) throws RemoteException;

    public double Division(double num1, double num2) throws RemoteException;
}

