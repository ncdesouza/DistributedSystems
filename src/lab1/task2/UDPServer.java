package lab1.task2;

import java.net.*;
import java.io.*;
public class UDPServer{
	public static void main(String args[]){ 
		DatagramSocket aSocket = null;
		try{
			aSocket = new DatagramSocket(6789);
			byte[] buffer = new byte[1000];
			int packetNum;
            Integer lastPacketNum = null;

            while(true){
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);

                byte[] data = request.getData();
                packetNum = data[0];
                if ((lastPacketNum != null) && ((packetNum - 1) != lastPacketNum)) {
                    System.out.println("Packet loss detected - " + Integer.toString(packetNum - 1));
                }
                lastPacketNum = packetNum;

                DatagramPacket reply = new DatagramPacket(request.getData(),
                                                          request.getLength(),
                                                          request.getAddress(),
                                                          request.getPort());
                aSocket.send(reply);
			}
		}catch (SocketException e){
			System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
	}
}
