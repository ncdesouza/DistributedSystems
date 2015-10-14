package lab1.task1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
  public static void main (String args[]) {
		try{
			int serverPort = 7896; 
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch(IOException e){
				System.out.println("Listen :"+e.getMessage());}
  }
}

class Connection extends Thread {
	BufferedReader in;
	DataOutputStream out;
	Socket clientSocket;

	public Connection (Socket aClientSocket) {
	  try {
			clientSocket = aClientSocket;
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			this.start();
	    } catch(IOException e)  {
				System.out.println("lab1.Connection:"+e.getMessage());}
	}

	public void run(){

		try {
			for(String line = in.readLine(); line != null; line = in.readLine()) {// an echo server
//				data = in.readLine();
				System.out.println(line);
			}
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				/*close failed*/
			}
		}
	}
}
