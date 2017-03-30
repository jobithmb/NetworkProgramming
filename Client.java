/**
 * Prog 1
 */
package Lab;

import java.util.*;
import java.io.*;
import java.net.*;

/**
 * @author Jobith M Basheer
 *
 */
public class Client {

	public static void main(String[] args) {
		
		try{
			Socket s = new Socket("localhost",8080);
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			
			dout.writeUTF("Hello Server!");
			dout.flush();
			dout.close();
			
			s.close();
		}
		catch(Exception e){
			System.out.print(e.getMessage());
		}

	}

}
