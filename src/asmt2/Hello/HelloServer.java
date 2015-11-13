package asmt2.Hello;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class HelloServer extends UnicastRemoteObject implements Hello {

    private List<ClientInterface> clients;

    private HelloServer() throws RemoteException {
        clients = new ArrayList<ClientInterface>();
    }

    public HelloServer(String host) throws RemoteException {
        System.setProperty("java.rmi.server.hostname", host);
        HelloServer obj = new HelloServer();

        // Bind the remote object's stub in the registry
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry();
            registry.list();
        }catch (RemoteException e) {
            System.out.println("RMI registry cannot be located at port " + Registry.REGISTRY_PORT);
            registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            System.out.println("RMI registry created at port " + Registry.REGISTRY_PORT);
        }

        registry.rebind("Hello", obj);
        System.out.println("HelloServer ready");

    }


    public String sayHello() {
        System.out.println("running sayHello()");
        return "Hello world!";
    }

    @Override
    public void registerForCallback(ClientInterface clientObject) throws RemoteException {
        clients.add(clientObject);
        doCallbacks();
    }

    @Override
    public void unregisterForCallback(ClientInterface clientObject) throws RemoteException{
        clients.remove(clientObject);
    }

    private void doCallbacks() throws RemoteException {
        // Create the message
        String msg;
        if (clients.size() <= 1) {
            msg = "There is " + clients.size() + " active client";
        } else {
            msg = "There are " + clients.size() + " active clients";
        }

        for (ClientInterface client : clients) {
            client.notify(msg);
        }
    }

    public static void main(String args[]) {
        String host = "localhost";
        if (args.length > 0) {
            // arges[0]: hostname
            host = args[0];
        }

        try {
            HelloServer server = new HelloServer(host);
        }catch(Exception e) {
            System.err.println("HelloServer exception: " + e.toString());
            e.printStackTrace();
        }

    }
}