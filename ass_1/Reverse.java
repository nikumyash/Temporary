import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Hello extends Remote{
    String SayHello(String hello) throws RemoteException
}


