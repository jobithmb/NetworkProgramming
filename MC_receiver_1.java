package Lab;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MC_receiver_1 {
  public static void main(String[] args) throws Exception {
    int mcPort = 12345;
    String mcIPStr = "230.1.1.1";
    MulticastSocket mcSocket = null;
    InetAddress mcIPAddress = null;
    mcIPAddress = InetAddress.getByName(mcIPStr);
    mcSocket = new MulticastSocket(mcPort);
    System.out.println("Multicast Receiver running at:"
        + mcSocket.getLocalSocketAddress());
    mcSocket.joinGroup(mcIPAddress);
    String msg;
    DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
    do{
    //System.out.println("Waiting for a  multicast message...");
    mcSocket.receive(packet);
    msg = new String(packet.getData(), packet.getOffset(),
        packet.getLength());
    System.out.println("[Multicast  Receiver] Received:" + msg);
    }while(!msg.equals(".bye"));

    mcSocket.leaveGroup(mcIPAddress);
    mcSocket.close();
  }
}