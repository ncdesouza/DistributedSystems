package lab2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

/**
 * ElectionServer:
 * <brief description of class>
 */
public class ElectionService extends UnicastRemoteObject implements Election {

    public ElectionService() throws RemoteException{
        super();
    }

    @Override
    public void vote(String candidate, int voter_number) throws RemoteException {
        System.out.println(candidate + Integer.toString(voter_number));
    }

    @Override
    public void result() throws RemoteException {

    }

    public static void main(String[] args) {

//        System.setProperty("java.security.policy","file:///./server.policy");
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }


        int port = 1099;
        String host = "localhost";
        if (args.length != 0) {
            if (args[0].equals("deploy")) {
                port = 1099;
                host = "";
            }
        }

        String registryName = "Election";
        String url = "rmi://"
                + host + ":"
                + Integer.toString(port) + "/"
                + registryName;

        try {
            // Create the service
            Election service = new ElectionService();
            // Bind the stub to the services registry
            Registry registry = LocateRegistry.createRegistry(port);
//            registry.list();
            Naming.rebind(url, service);
            System.out.println("Election service bound");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
