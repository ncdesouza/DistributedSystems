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

        int port = 1099;
        String host = null;
        String name = "Election";

        if (args.length > 3) {
            host = args[3];
            port = Integer.parseInt(args[4]);
        }

        try {
            // get the registry on the remote system
            Registry registry = LocateRegistry.getRegistry(host, port);

            // find the remote class from the remote registry
            Election election = (Election) registry.lookup(name);

            if (args[0].equals("vote"))
                election.vote(args[1], Integer.parseInt(args[2]));
            else if (args[0].equals("result"))
                System.out.println(election.result());

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}
