package asmt2.Hello;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {

    String sayHello() throws RemoteException;

    /**
     * registerForCallback:
     *      This method is responsible for adding the clients callback to the
     *      server.
     * @param clientObject An object that represents the client interface.
     * @throws RemoteException
     */
    void registerForCallback(ClientInterface clientObject) throws RemoteException;

    /**
     * unregisterForCallback:
     *      This method is responsible for removing the clients callback from
     *      the server.
     * @param clientObject An object that represents
     * @throws RemoteException
     */
    void unregisterForCallback(ClientInterface clientObject) throws RemoteException;

}