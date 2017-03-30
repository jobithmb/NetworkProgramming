package Lab;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class HTTP_client {
  
 

  public static void main(String[] args) throws ClientProtocolException, IOException {
    
	  final String USER_AGENT = "Mozilla/5.0";
	  String url = "http://www.google.com";

	  HttpClient client = HttpClientBuilder.create().build();
	  HttpGet request = new HttpGet(url);

	  request.addHeader("User-Agent", USER_AGENT);
	  HttpResponse response = client.execute(request);

	  
      System.out.println(response.getStatusLine());
      Header[] headers = response.getAllHeaders();
      for (int i = 0; i < headers.length; i++) {
        System.out.println(headers[i]);
      }
  }
}


