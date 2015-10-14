package lab2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * ElectionClient:
 * <brief description of class>
 */
public class ElectionClient {

    public static void main(String[] args) {
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }

        try {
            String name = "Election";
            Registry registry = LocateRegistry.getRegistry(1099);
            Election election = (Election) registry.lookup(name);
            election.vote("Nicholas", 1);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}
