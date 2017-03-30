package Lab;

import java.io.*;
import java.net.*;
public class SW_server {

	public static void main(String[] args)throws Exception 
	{

		ServerSocket soc=new ServerSocket(3343);
		Socket s=soc.accept();
		DataOutputStream dos=new DataOutputStream(s.getOutputStream());
		DataInputStream dis=new DataInputStream(s.getInputStream());
		int framesize=Integer.valueOf(dis.readUTF());
		int counter=0;
		int totalf=framesize;
		for(int i=0;i<framesize;i++)
		{
				String received =dis.readUTF();
				Thread.sleep(2000);
				System.out.println("receieved frame...."+(i+1)+":"+received);
				counter++;
				
				if(counter==7)
				{
					System.out.println("Sendind ACK for 7 frames");
					dos.writeUTF("1");
					counter=0;
					totalf=totalf-7;
				}
				if(counter!=7&&(i)==framesize-1)
				{
					
					System.out.println("ACK Recieved sending for last "+totalf+" frames");
					
				}
		}
	}
		
}

