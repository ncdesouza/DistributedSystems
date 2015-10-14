package lab2;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * ElectionServer:
 * <brief description of class>
 */
public class ElectionService implements Election {

    public ElectionService() {
        super();
    }

    @Override
    public void vote(String candidate, int voter_number) throws RemoteException {

    }

    @Override
    public void result() throws RemoteException {

    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            String name = "Election";
            // Create the service
            Election service = new ElectionService();
            // Create the stub for the client
            Election stub = (Election) UnicastRemoteObject.exportObject(service);

            // Bind the stub to the services registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("Election service bound");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
