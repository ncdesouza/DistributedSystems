package lab1.task1;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {


	public static void main (String args[]) {
		// arguments supply message and hostname of destination
		Socket s = null;
		try{

			int serverPort = 7896;
			s = new Socket(args[0], serverPort);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

			String line = "";
			while (!line.equals(".quit")) {

				if (s.isConnected()) {
					System.out.print("Send>>");
					Scanner console = new Scanner(System.in);
					line = console.nextLine();
//					if (line.length() == 0){
//						continue;
//					}
					try {
						out.println(line);
						out.flush();// UTF is a string encoding see Sn 4.3
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("Socket connection closed unexpectedly");
				}
			}

		}catch (UnknownHostException e){
			System.out.println("Sock:"+e.getMessage());
		}catch (EOFException e){
			System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){
			System.out.println("IO:"+e.getMessage());
		}finally {
			if(s != null)
				try {
					s.close();
				}
				catch (IOException e) {
					System.out.println("close:"+e.getMessage());
				}
		}
	}
}

