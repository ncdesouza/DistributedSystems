package asmt2.Hello;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ClientInterface:
 * <brief description of class>
 */
public interface ClientInterface extends Remote {

    String notify(String message) throws RemoteException;

}
