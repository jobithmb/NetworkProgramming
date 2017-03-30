package Lab;

import java.util.*;
import java.io.*;
import java.net.*;

public class BS_server {

	public static void main( String args[]){
		try{
			ServerSocket ss = new ServerSocket(8080); 
			Socket s = ss.accept();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			
			String res = (String) dis.readUTF();
            String out=new String();
            int counter = 0;

            for(int i=0;i<res.length();i++)
            { 
                counter = (int) ((res.charAt(i) == '1')? counter+1 : 0);
                out = out + res.charAt(i);
                
               if(counter == 5)
                {
                  if((i+2)!=res.length())
                	  out = out + res.charAt(i+2);
                  else
                	  out=out + '1';
                  i=i+2;
                  counter = 1;
                }
           }

           System.out.println("The Destuffed Message is: "+out);
			
           dis.close();
           s.close();
           ss.close();
		}
		
		catch(Exception e){
			System.out.print(e.getMessage());
		}
	}
	
}
