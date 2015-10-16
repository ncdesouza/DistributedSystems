package lab2;

import asmt1.q4.Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.Dictionary;
import java.util.HashMap;

/**
 * ElectionServer:
 *  This class implements the remote Election interface. It will create a registry
 *  and bind the remote implemented class to it.
 */
public class ElectionService extends UnicastRemoteObject implements Election {
    HashMap<Integer, String> hashMap;


    public ElectionService() throws RemoteException{
        super();
        hashMap = new HashMap<Integer, String>();
    }

    @Override
    public void vote(String candidate, int voter_number) throws RemoteException {
        System.out.println(candidate + Integer.toString(voter_number));
        if (hashMap.get(voter_number) == null)
            hashMap.put(voter_number, candidate);
    }

    @Override
    public String result() throws RemoteException {
        return hashMap.toString();
    }

    public static void main(String[] args) {

        // set local host and port
        String host = "localhost";
        int port = 1099;

        if (args.length != 0) {
                // set remote host and port
                host = args[0];
                port = Integer.parseInt(args[1]);
        }

        System.setProperty( "java.rmi.server.hostname", host ) ;

        // create registry name
        String registryName = "Election";
        // create registry url
        String url = "rmi://"
                + host + ":"
                + Integer.toString(port) + "/"
                + registryName;

        try {
            // Create the service
            Election service = new ElectionService();

            // Create a new registry
            Registry registry = LocateRegistry.createRegistry(port);
            System.out.println("New registry created on port:" + Integer.toString(port));

            // Bind the remote election class to registry
            Naming.rebind(url, service);
            System.out.println("Election bound to registry");

            System.out.println("Election Service ready @ " + host + ":" + Integer.toString(port));
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
