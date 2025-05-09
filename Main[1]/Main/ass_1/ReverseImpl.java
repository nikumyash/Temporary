import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class ReverseImpl extends UnicastRemoteObject implements Reverse{
    ReverseImpl() throws RemoteException{
        super();
    }
    public  String SayHello() throws RemoteException{
        return "Hello";
    }
}