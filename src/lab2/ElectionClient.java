package lab2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

/**
 * ElectionClient:
 * <brief description of class>
 */
public class ElectionClient {
    final String NAME = "Election";

    String host;
    int port;
    Election election;
    Registry registry;

    public ElectionClient() throws RemoteException, NotBoundException {
        this.host = null;
        this.port = 1099;
        this.registry = connectToRegistry();
        this.election = getRemoteObject();
    }

    public ElectionClient(String host, int port) throws RemoteException, NotBoundException {
        this.host = host;
        this.port = port;
        this.registry = connectToRegistry();
        this.election = getRemoteObject();
    }

    private Registry connectToRegistry() throws RemoteException {
        Registry registry = LocateRegistry.getRegistry(this.host, this.port);
        System.out.println(Arrays.toString(registry.list()));
        return registry;
    }

    private Election getRemoteObject() throws RemoteException, NotBoundException {
        return (Election) registry.lookup(NAME);
    }

    public void vote(String candidate, int voterNumber) throws RemoteException {
        election.vote(candidate, voterNumber);
    }

    public void result() throws RemoteException {
        System.out.println(election.result());
    }

    public static void main(String[] args) {
        // get the registry on the remote system
        // find the remote class from the remote registry
        ElectionClient client;
        try {
            if (args.length <= 3)
                client = new ElectionClient();
            else
                client = new ElectionClient(args[3], Integer.parseInt(args[4]));

            if (args[0].equals("vote"))
                client.vote(args[1], Integer.parseInt(args[2]));
            else if (args[0].equals("result"))
                client.result();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
