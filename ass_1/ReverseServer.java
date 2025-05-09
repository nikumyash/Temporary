import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ReverseClient{
    public static void main(String[] args){
        Registry reg = LocateRegistry.createRegisty(1099);
        ReverseImpl res = new ReverseImpl();
        reg.rebind("Helloservice",res);
        String response = res.sayHello();
        
    }
}