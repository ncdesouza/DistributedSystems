package asmt2.Hello;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Program:
 *      This program will create a server instance and two client instances. It
 *      will then register the clients then unregister the clients. Finally it
 *      will re-register one client to show that the clients have been
 *      successfully registered and removed.
 */
public class Program {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 1099;

        try {

            HelloServer server = new HelloServer(host);

            HelloClient client1 = new HelloClient(host, port);
            HelloClient client2 = new HelloClient(host, port);

            client1.register();
            client2.register();

            client1.unregister();
            client2.unregister();

            client1.register();
            client2.unregister();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}
