package packets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Computer2 {

	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket(5001);
		
		Thread t = new Thread(() -> {
			Scanner sc = new Scanner(System.in);
			while(true) {
				String text = sc.nextLine();
				
				//TODO: send the text to the other computer
				send(text, socket);
			}
		});
		t.start();
		
		while(true) {
			DatagramPacket packet = new DatagramPacket(new byte[1000], 1000);
			
			socket.receive(packet);
			
			String text = new String(packet.getData(), 0, packet.getLength());
			
			System.out.println(text);
		}
	}
	
	static void send(String message, DatagramSocket socket) {
		byte[] data = message.getBytes();
				
		try {
			DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5000);
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
