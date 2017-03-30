package Lab;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GroupChatClient2 {
	
	static String msgS, msgR="";
	
	public static void main(String a[]) throws UnknownHostException, IOException
	{
		
		
		Scanner sc=new Scanner(System.in);
		Socket s =new Socket("localhost",8081);
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		DataInputStream dis = new DataInputStream(s.getInputStream());
		
		try{
		
		Thread t = new Thread() {
		    public void run() {
		    	do{
		    		try {
						if(dis.available()>0){
							msgR = dis.readUTF();
							System.out.println("                    "+msgR);
						}
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
		    	}while(!msgR.equals(".bye"));
		    }
		};
		t.start();
		
		do{
			
			System.out.println("");
			msgS = sc.next();
			
			dout.writeUTF(msgS);
			dout.flush();
		
		}while(!msgR.equals(".bye"));
		
		t.stop();
		System.out.println("Session Ended!");
		
		dout.close();
		dis.close();
		s.close();
	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
		
	}
}