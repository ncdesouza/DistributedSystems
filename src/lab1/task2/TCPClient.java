package lab1.task2;

import java.net.*;
import java.io.*;
public class TCPClient {
	public static void main (String args[]) {
		int numMsg = Integer.parseInt(args[2]);
		// arguments supply message and hostname of destination
		Socket s = null;
		try{
			int serverPort = 7896;
			s = new Socket(args[1], serverPort);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			for (int i = 0; i<numMsg; i++) {
				out.println(Integer.toString(i) + " " + args[0]);
				out.flush();
			}
		}catch (UnknownHostException e){
			System.out.println("Sock:"+e.getMessage()); 
		}catch (EOFException e){
			System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){
			System.out.println("IO:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}
	catch (IOException e){System.out.println("close:"+e.getMessage());}}
	}
}

