package Lab;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
	
	public static void main(String[] args) throws IOException {
		
		String msgS, msgR;
		
		try{
		ServerSocket ss = new ServerSocket(8081);
		Socket s = ss.accept();
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		DataInputStream dis = new DataInputStream(s.getInputStream());
		
		do{
		msgR = dis.readUTF();
		
		
		
		if(msgR.contains("hello") || msgR.contains("hi") || msgR.contains("hey"))
			msgS = "Hi there! How Are You ?";
		else if (msgR.contains("bye"))
			msgS = "bye";
		else
			msgS = "Default Reply";
		
		dout.writeUTF(msgS);
		dout.flush();
		}while(!msgR.equals("bye"));
		
		dout.close();
		dis.close();
		s.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
	}
}