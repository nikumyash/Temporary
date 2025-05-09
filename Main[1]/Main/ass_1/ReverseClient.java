import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ReverseClient{
    public static void main(String[] args){
        Registry reg = LocateRegistry.getRegistry("localhost",1099);
        ReverseImpl res = (ReverseImpl) reg.lookup("HelloService");
        String response = res.sayHello();
        
    }
}