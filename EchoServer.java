package Lab;

import java.io.*;
import java.net.*;

public class EchoServer
{
	

	public static void main(String[] args) throws IOException
	{
		ServerSocket server = new ServerSocket(9999);
		Socket client = server.accept();
		BufferedReader r = new BufferedReader(new InputStreamReader(client.getInputStream()));
		PrintWriter w = new PrintWriter(client.getOutputStream(), true);
		w.println("Welcome to the Java EchoServer.  Type 'bye' to close.");
		String line;
		do
		{
			line = r.readLine();
			if ( line != null )
				w.println("Got: "+ line);
		}
		while ( !line.trim().equals("bye") );
		client.close();
	}

	
}