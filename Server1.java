package Lab;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server1 {
	
	public static void main(String[] args) throws IOException {
		int number,temp;
		
		try{
		ServerSocket ss=new ServerSocket(8081);
		Socket s = ss.accept();
		
		DataInputStream dis = new DataInputStream(s.getInputStream());
		
		number=dis.readInt();
		temp=number/2;
		
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		dout.writeInt(temp);
		dout.flush();
		dout.close();
		dis.close();
		s.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
	}
}