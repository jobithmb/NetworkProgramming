package Lab;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class MC_sender {
  public static void main(String[] args) throws Exception {
    int mcPort = 12345;
    String mcIPStr = "230.1.1.1";
    DatagramSocket udpSocket = new DatagramSocket();
    Scanner scan = new Scanner(System.in);
    InetAddress mcIPAddress = InetAddress.getByName(mcIPStr);
    String sent = new String();
    do{
    	sent = scan.nextLine();
    	byte[] msg = sent.getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length);
        packet.setAddress(mcIPAddress);
        packet.setPort(mcPort);
        udpSocket.send(packet);

    	
    }while(!sent.equals(".bye"));
    
    
    System.out.println("Exiting application");
    udpSocket.close();
  }
}
