package packets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Computer1 {

	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket(5000);
		
		byte[] data = "This is some text".getBytes();
		
		DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5001);
		
		socket.send(packet);
		
		socket.close();
	}
	
}
