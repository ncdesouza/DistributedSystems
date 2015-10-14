package lab1.task3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

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
	PrintWriter out;
	Socket clientSocket;
    Random rand;


    public Connection (Socket aClientSocket) {
	  rand  = new Random();
        try {
          clientSocket = aClientSocket;
          in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
          this.start();
	    } catch(IOException e)  {
				System.out.println("lab1.Connection:"+e.getMessage());}
	}

	public void run(){

		try {
			for(String line = in.readLine(); line != null; line = in.readLine()) {// an echo server
				System.out.println(line);
                if (rand.nextBoolean()) {
                    out.println(line);
                    out.flush();
                }
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
