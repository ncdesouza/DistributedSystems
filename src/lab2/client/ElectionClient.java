package lab2.client;

import lab2.AlreadyVotedException;
import lab2.Election;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * ElectionClient:
 * <brief description of class>
 */
public class ElectionClient {
    protected final String NAME = "Election";

    private String host;
    private int port;
    private Election election;

    public ElectionClient() throws RemoteException, NotBoundException {
        this.host = null;
        this.port = 1099;
        this.election = getRemoteObject();
    }

    public ElectionClient(String host, int port) throws RemoteException, NotBoundException {
        this.host = host;
        this.port = port;
        this.election = getRemoteObject();
    }

    private Election getRemoteObject() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(this.host, this.port);
        return (Election) registry.lookup(NAME);
    }

    public void vote(String candidate, int voterNumber) {
        Boolean voteSent = false;
        while (!voteSent) {
            try {
                election.vote(candidate, voterNumber);
                voteSent = true;
                System.out.println("Your Vote has been sent for " + candidate + " " + Integer.toString(voterNumber));
            } catch (RemoteException e) {
                System.out.println("Your Vote could not be sent. Trying again...");
                voteSent = false;
            } catch (AlreadyVotedException e) {
                System.out.println(e.getMessage());
                voteSent = true;
            } catch (IOException e) {
                System.out.println("Your Vote could not be sent. Trying again...");
                voteSent = false;
            }
        }
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
