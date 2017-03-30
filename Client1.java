package Lab;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client1 {
	
	public static void main(String a[]) throws UnknownHostException, IOException
	{
		int number,temp;
		
		try{
		Scanner sc=new Scanner(System.in);
		Socket s =new Socket("localhost",8081);
		
		DataInputStream dis = new DataInputStream(s.getInputStream());
		
		System.out.print("Enter any no:");
		number =sc.nextInt();
		
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		dout.writeInt(number);
		dout.flush();
		
		
		temp = dis.readInt();
		System.out.println("Half From Server: "+temp);
		
		dout.close();
		dis.close();
		s.close();
	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
		
	}
}