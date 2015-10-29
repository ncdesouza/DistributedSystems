package lab2.server;

import lab2.Election;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * ElectionServer:
 * <brief description of class>
 */
public class ElectionServer {

    protected final String REGISTRY_NAME = "Election";

    private String hostname;
    private int port;
    private Election service;
    private Registry registry;

    public ElectionServer() throws RemoteException {
        this.hostname = "localhost";
        this.port = 1099;
        this.service = new ElectionService();
        this.registry = LocateRegistry.createRegistry(port);
    }

    public ElectionServer(String hostname, int port) throws RemoteException {
        this.hostname = hostname;
        this.port = port;
        this.service = new ElectionService();
        this.registry = LocateRegistry.createRegistry(port);
    }

    public void run() throws MalformedURLException, RemoteException {
        setSystemProperties();
        setRegistry();
        System.out.println("Election server ready @ " + hostname + ":" + Integer.toString(port));
    }

    public void stop() throws NoSuchObjectException {
        UnicastRemoteObject.unexportObject(registry, true);
    }

    private void setSystemProperties() {
        System.setProperty("java.rmi.server.hostname", hostname);
    }

    private void setRegistry() throws RemoteException, MalformedURLException {
        // Create a new registry
        System.out.println("New registry created on port:" + Integer.toString(port));

        // Bind the remote election class to registry
        Naming.rebind("rmi://" + hostname + ":" + Integer.toString(port) + "/" + REGISTRY_NAME, service);
        System.out.println("Election bound to registry");
    }

    public static void main(String[] args) {

        try {

            ElectionServer server;

            if (args.length == 0)   // if no args supplied
                server = new ElectionServer();  // create local server
            else // create remote server
                server = new ElectionServer(args[0], Integer.parseInt(args[1]));

            server.run();   // run server

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
