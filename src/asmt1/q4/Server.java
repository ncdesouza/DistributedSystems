package asmt1.q4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server:
 * <brief description of class>
 */
public class Server {

    public static void main(String args[]) {
        ServerSocket servSoc = null;
        Socket soc = null;
        String str = null;
        Couple couple = null;

        try {
            // create server connections
            servSoc = new ServerSocket(8020);
            soc = servSoc.accept();
            InputStream in = soc.getInputStream();
            ObjectInput objIn = new ObjectInputStream(in);
            OutputStream out = soc.getOutputStream();
            ObjectOutput objOut = new ObjectOutputStream(out);

            // get couple from client
            couple = (Couple) objIn.readObject();
            // create second person
            Person sue = new Person("Sue", "Toronto", 1988);
            // set second person in couple
            couple.setTwo(sue);

            // send couple back to client
            objOut.writeObject(couple);
            objOut.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
