package Lab;

import java.io.*;
import java.net.*;
import java.util.*;

public class SW_client {

	public static void main(String[] args) throws Exception
	{

		Scanner scan =new Scanner(System.in);
		Socket sock=new Socket(InetAddress.getLocalHost(),3343);
		DataOutputStream dos=new DataOutputStream(sock.getOutputStream());
		DataInputStream dis=new DataInputStream(sock.getInputStream());
		System.out.println("How many frames you want to send...");
		int framesize=scan.nextInt();
		scan.nextLine();
		String[] framecontent=new String[framesize];
		for(int i=0;i<framesize;i++)
		{
			System.out.println("Enter the "+(i+1)+"Frame");
			framecontent[i]=scan.nextLine();
			
		}
		int counter=0;
		int totalf=framesize;
		dos.writeUTF(String.valueOf(framesize));
		for(int i=0; i<framesize;i++)
		{
				
				System.out.println("sending frames...."+(i+1)+":"+framecontent[i]);
				dos.writeUTF(framecontent[i]);
				counter++;
				
				if(counter ==7)
				{
					String acknowledgement=dis.readUTF();
					if(acknowledgement.equals("1"))
					{
						Thread.sleep(5000);
						System.out.println("ACK receives from server for 7 frames");
						
					}
					totalf=totalf-7;
					counter=0;
					
				}
	
			if(counter!=7 &&(i)==framesize-1)
				{
					System.out.println("ACK receives from server for "+totalf+" frames");
				}
		
		}
		dos.close();
		sock.close();
		dis.close();

	
}

}
