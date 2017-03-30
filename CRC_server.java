package Lab;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CRC_server {

	public static void main(String args[]){
		
		try{
			ServerSocket ss = new ServerSocket(8080); 
			Socket s = ss.accept();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			Scanner sc = new Scanner(System.in);
			
			
			System.out.println("Waiting to receive data...");
			
			String res = (String) dis.readUTF();
			String[] resArray = res.split("9");
			
			System.out.println("Data Received SuccessFully!");
			System.out.println("Checking Validity! ");
			
			System.out.print("Enter CRC code: ");
			String datastream = sc.nextLine();
			
			//System.out.print("Enter generator: ");
			String generator = resArray[1];
			
			int [] data = new int[datastream.length() + generator.length() - 1];
			int [] divisor = new int[generator.length()];
			
			for(int i=0;i<datastream.length();i++)
				data[i] = Integer.parseInt(datastream.charAt(i)+"");
			
			for(int i=0;i<generator.length();i++)
				divisor[i] = Integer.parseInt(generator.charAt(i)+"");

			//Calculation of remainder
			for(int i=0;i<datastream.length();i++){
				if(data[i]==1)
					for(int j=0;j<divisor.length;j++)
						data[i+j] ^= divisor[j];
			}

			//Display validity of data
			boolean valid = true;
			for(int i=0;i<data.length;i++)
				if(data[i]==1){
					valid = false;
					break;
				}

			if(valid==true) 
				System.out.println("Data stream is valid");
			else 
				System.out.println("Data stream is invalid. CRC error occured.");
			
	           dis.close();
	           s.close();
	           ss.close();
			}
			
		catch(Exception e){
			System.out.print(e.getMessage());
		}     
	}
}
