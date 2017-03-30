
package Lab;

import java.net.*;
import java.util.*;
import java.io.*;

/**
 * @author Jobith M Basheer
 *
 */
public class CMD_Server {


	public static void main(String[] args) {
		
		try{
			ServerSocket ss = new ServerSocket(8080); 
			Socket s = ss.accept();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			
			String str = (String) dis.readUTF();
			
			System.out.print("Message from Client = "+str);
			dis.close();
			s.close();
			ss.close();
		}
		catch(Exception e){
			System.out.print(e.getMessage());
		}
		

	}

}
