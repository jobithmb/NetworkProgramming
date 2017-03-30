package Lab;

import java.io.*;

import java.net.*;
import java.util.Scanner;

class FTP_client{

	public static void main(String svi[])throws IOException{
		
		
			Socket sock=new Socket("localhost",8080);
			byte[] bytearray=new byte[1024];
			InputStream is=sock.getInputStream();
			Scanner dis=new Scanner(System.in);
			
			System.out.println("enter the file name");
			String fil=dis.nextLine();
			
			System.out.println("Waiting for file contents...");
			
			FileOutputStream fos=new FileOutputStream(fil);
			BufferedOutputStream bos=new  BufferedOutputStream(fos);
			
			int bytesread=is.read(bytearray,0,bytearray.length);
			bos.write(bytearray,0,bytesread);
			System.out.println(fil+" file is received");
			bos.close();
			sock.close();
		
	}
}