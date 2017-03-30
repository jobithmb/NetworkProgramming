package Lab;

import java.io.*;
import java.util.*;
import java.net.*;

class FTP_server{

	public static void main(String svi[])throws IOException{
		
			ServerSocket servsock=new ServerSocket(8080);
			Scanner dis=new Scanner(System.in);
			System.out.println("enter the file name");
			String fil=dis.nextLine();
			
			System.out.println(fil+" :is file transfer");
			
			File myfile=new File(fil);
			
			Socket sock=servsock.accept();
			byte[] mybytearray=new byte[(int)myfile.length()];
			
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(myfile));
			bis.read(mybytearray,0,mybytearray.length);
			
			OutputStream os=sock.getOutputStream();
			os.write(mybytearray,0,mybytearray.length);
			
			os.flush();
			
			sock.close();
			servsock.close();
			dis.close();
				
	}
}