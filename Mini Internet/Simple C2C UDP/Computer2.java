package packets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Computer2 {

	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket(5001);
		
		DatagramPacket packet = new DatagramPacket(new byte[1000], 1000);
		socket.receive(packet);
		
		String text = new String(packet.getData(), 0, packet.getLength());
		
		System.out.println(text);
		
		socket.close();
	}
	
}
