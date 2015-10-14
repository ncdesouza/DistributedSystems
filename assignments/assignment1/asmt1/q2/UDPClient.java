package asmt1.q2;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;

public class UDPClient{

    public static byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }

    public static void main(String args[]) {
        int numMsg = 100000;
        int size = 25000;
        String addr = "192.168.2.214";

        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket();
            for (int i  = 0; i <= numMsg; i+=1) {
                byte[] id = ByteBuffer.allocate(4).putInt(i).array();
                System.out.println(ByteBuffer.wrap(id).getInt());

                byte [] data = new byte[size];
                System.arraycopy(id, 0, data, 0, id.length);

                InetAddress aHost = InetAddress.getByName(addr);
                int serverPort = 6789;
                DatagramPacket request = new DatagramPacket(data, data.length, aHost, serverPort);
                aSocket.send(request);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(aSocket != null) aSocket.close();
        }
    }
}
	
