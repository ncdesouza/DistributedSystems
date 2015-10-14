package asmt1.q4;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * client:
 * <brief description of class>
 */
public class Client {
    public static void main(String args[]) {

        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 8020);
            OutputStream out = socket.getOutputStream();
            ObjectOutput objOut = new ObjectOutputStream(out);
            InputStream in = socket.getInputStream();
            ObjectInput objIn = new ObjectInputStream(in);

            // create a person bob
            Person bob = new Person("Bob", "Toronto", 1987);
            // create a couple and put bob as person one
            Couple couple = new Couple(bob);
            // print the couple before sending
            System.out.println(couple);
            // send couple to server
            objOut.writeObject(couple);
            objOut.flush();

            // get couple response from server
            couple = (Couple) objIn.readObject();
            // print couple response from server
            System.out.println(couple);



        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
