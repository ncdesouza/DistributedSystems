package lab1.task2;

import java.net.*;
	import java.io.*;
import java.util.Random;

public class UDPClient{

    static class UDPReceiver extends Thread {
        DatagramSocket aSocket = null;
        int packetSize;
        public UDPReceiver(DatagramSocket aSocket, int packetSize) {
            this.aSocket = aSocket;
            this.packetSize = packetSize;
        }

        public void run() {
            try {
                    this.aSocket.setSoTimeout(1);
                    byte[] buffer = new byte[this.packetSize];
                    DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                    this.aSocket.receive(reply);
                    System.out.println("Reply:" + new String(reply.getData()));
            } catch (SocketException e) {
                System.out.println("Receive" + e.getMessage());
            } catch (IOException e) {
                System.out.println("Receive" + e.getMessage());
            }
        }
    }

    static class UDPSender extends Thread {
        DatagramSocket aSocket = null;
        String hostName;
        String msg;
        Integer id;

        public UDPSender(DatagramSocket aSocket, int id, String msg, String hostName) {
            this.aSocket = aSocket;
            this.hostName = hostName;
            this.id = id;
            this.msg = msg;
        }

        public void run() {
            try {
                byte id = this.id.byteValue();
                byte [] msg = this.msg.getBytes();
                byte [] data = new byte[msg.length + 1];
                data[0] = id;
                System.arraycopy(msg, 0, data, 1, msg.length);

                InetAddress aHost = InetAddress.getByName(hostName);
                int serverPort = 6789;
                DatagramPacket request = new DatagramPacket(data, msg.length, aHost, serverPort);
                aSocket.send(request);

            } catch (SocketException e) {
                System.out.println("Send: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Send: " + e.getMessage());
            }
        }
    }

    public static void main(String args[]){
        int numMsg = Integer.parseInt(args[2]);
        // args give message contents and server hostname
        DatagramSocket aSocket = null;
        UDPSender sender = null;
        UDPReceiver receiver = null;
        try {
            aSocket = new DatagramSocket();
            Random random = new Random();
            for (int i  = 0; i <= numMsg; i+=1) {

                byte id = new Integer(i).byteValue();
                System.out.println(id);
                byte [] msg = args[0].getBytes();
                byte [] data = new byte[msg.length + 1];
                data[0] = id;
                System.arraycopy(msg, 0, data, 1, msg.length);

                InetAddress aHost = InetAddress.getByName(args[1]);
                int serverPort = 6789;
                DatagramPacket request = new DatagramPacket(data, msg.length, aHost, serverPort);
                aSocket.send(request);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            try {
//                if (receiver != null) {
//                    receiver.join();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            if(aSocket != null) aSocket.close();
        }
    }
}
	
