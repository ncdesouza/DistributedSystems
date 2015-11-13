package asmt2.Hello;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * HelloClient:
 * <brief description of class>
 */
public class HelloClient extends UnicastRemoteObject implements ClientInterface {
    private Registry registry;
    private Hello stub;

    public HelloClient(String host, int port) throws RemoteException, NotBoundException {
        registry = LocateRegistry.getRegistry(host, port);
        stub = (Hello) registry.lookup("Hello");
    }

    public void register() throws RemoteException {
//        registry.rebind("Callback", this);
        stub.registerForCallback(this);
    }

    public void unregister() throws RemoteException {
        stub.unregisterForCallback(this);
    }

    public void sayHello() throws RemoteException {
        String response = stub.sayHello();
        System.out.println("response: " + response);
    }

    @Override
    public String notify(String message) throws RemoteException {
        System.out.println(message);
        return message;
    }

    public static void main(String[] args) {
        //args[0]: hostname of server where registry resides
        //args[1]: port of registry on that server
        String host = "localhost";
        int port = 1099;
        if (args.length > 0) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        try {
            HelloClient client = new HelloClient(host, port);
            client.register();
            client.sayHello();

        } catch(Exception e) {
            System.err.println("HelloClient exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

