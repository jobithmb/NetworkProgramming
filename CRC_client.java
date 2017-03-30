package Lab;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class CRC_client
{
    public static void main(String args[]) throws IOException
    {
    	
    	try{
			Socket s = new Socket("localhost",8080);
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
						
			Scanner sc = new Scanner(System.in);
	
			System.out.print("Enter data stream: ");
			String datastream = sc.nextLine();
			
			System.out.print("Enter generator: ");
			String generator = sc.nextLine();
			
			int data[] = new int[datastream.length() + generator.length() - 1];
			int divisor[] = new int[generator.length()];
			
			for(int i=0;i<datastream.length();i++)
				data[i] = Integer.parseInt(datastream.charAt(i)+"");
			
			for(int i=0;i<generator.length();i++)
				divisor[i] = Integer.parseInt(generator.charAt(i)+"");

			//Calculation of CRC
			for(int i=0;i<datastream.length();i++){
				if(data[i]==1)
					for(int j=0;j<divisor.length;j++)
						data[i+j] ^= divisor[j];
			}

			String out = new String();
			//Display CRC
			System.out.print("The CRC code is: ");
			for(int i=0;i<datastream.length();i++)
				data[i] = Integer.parseInt(datastream.charAt(i)+"");
			
			for(int i=0;i<data.length;i++) 
				System.out.print(data[i]);
			System.out.println();
	        
			for(int i=0;i<data.length;i++)
				out+=data[i];
	        
	        
			dout.writeUTF(out+"9"+generator);
			dout.flush();
			dout.close();
			
			s.close();
		}
		catch(Exception e){
			System.out.print(e.getMessage());
		}
    }
}
