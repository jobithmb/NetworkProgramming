package Lab;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
	
	public static void main(String a[]) throws UnknownHostException, IOException
	{
		String msgS, msgR;
		
		try{
		Scanner sc=new Scanner(System.in);
		Socket s =new Socket("localhost",8081);
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		DataInputStream dis = new DataInputStream(s.getInputStream());
		
		do{
		System.out.print("Enter a message: ");
		msgS = sc.next();
		
		dout.writeUTF(msgS);
		dout.flush();
		
		msgR = dis.readUTF();
		System.out.println("Message Recieved: "+msgR);
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