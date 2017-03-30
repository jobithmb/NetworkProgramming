package Lab;

import java.util.*;
import java.io.*;
import java.net.*;

public class BS_client {

	public static void main( String args[]){
		try{
			Socket s = new Socket("localhost",8080);
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			
			
			System.out.print("Enter the Binary message: ");
	        Scanner sn=new Scanner(System.in);
	        String data = sn.nextLine();
	        String res = new String();
	        
	        int counter = 0;
            
          for(int i=0;i<data.length();i++)
          {   
             if(data.charAt(i) == '1')
              counter++;
             else
	          counter = 0;
             
             res = res + data.charAt(i);
 
             if(counter == 5)
             {
              res = res + '0';
              counter = 0;
             }
          }
          
          
          System.out.println("The Message to be transfered: " +res);
          
			dout.writeUTF(res);
			dout.flush();
			dout.close();
			
			s.close();
		}
		catch(Exception e){
			System.out.print(e.getMessage());
		}
	}
	
}
