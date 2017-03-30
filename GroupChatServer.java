package Lab;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class GroupChatServer {
	
	public static void main(String[] args) throws IOException {
		
		String msg1="", msg2="";
		
		try{
		ServerSocket ss = new ServerSocket(8081);
		Socket s1 = ss.accept();
		Socket s2 = ss.accept();
		
		DataOutputStream dout1 = new DataOutputStream(s1.getOutputStream());
		DataInputStream dis1 = new DataInputStream(s1.getInputStream());
		
		DataOutputStream dout2 = new DataOutputStream(s2.getOutputStream());
		DataInputStream dis2 = new DataInputStream(s2.getInputStream());
		
		do{
		
			if(dis1.available()>0){
				msg1 = dis1.readUTF();
				dout2.writeUTF(msg1);
			}
			
			if(dis2.available()>0){
				msg2 = dis2.readUTF();
				dout1.writeUTF(msg2);
			}
			
		}while((!msg1.equals(".bye"))||(!msg2.equals(".bye")));
		
		
		System.out.println("Session Ended!");
		
		s1.close();
		s2.close();
		ss.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
	}
}