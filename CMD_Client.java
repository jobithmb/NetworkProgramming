
package Lab;

import java.util.*;
import java.io.*;
import java.net.*;

/**
 * @author Jobith M Basheer
 *
 */
public class CMD_Client {

	public static void main(String[] args) {
		
		String ip;
		int port;
		Scanner scan = new Scanner(System.in);
		
		if(args.length<1){
			System.out.println("Please provide ip and port number as CommandLine arguments are not provided!");
			System.out.println("Enter ip: ");
			ip = scan.nextLine();
			System.out.println("Enter port: ");
			port = scan.nextInt();
		}
		else{
			ip = args[0];
			port = Integer.parseInt(args[1]);
		}
		
		
		
		try{
			Socket s = new Socket(ip,port);
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			
			System.out.println("enter a message: ");
			dout.writeUTF(scan.nextLine());
			dout.flush();
			dout.close();
			
			s.close();
		}
		catch(Exception e){
			System.out.print(e.getMessage());
		}

	}

}
