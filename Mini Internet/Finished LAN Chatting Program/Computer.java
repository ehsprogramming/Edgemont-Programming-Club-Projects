package packets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Computer {

	static boolean sending = true;
	
	public static void main(String[] args) throws Exception {
		DatagramSocket messageSocket = new DatagramSocket(5000);
		DatagramSocket broadcastSocket = new DatagramSocket(5001);
		
		List<String> names = new ArrayList<>();
		List<InetAddress> addresses = new ArrayList<>();
		
		Thread receiveBroadcastThread = new Thread(() -> {
			DatagramPacket packet = new DatagramPacket(new byte[100], 100);
			
			while(sending) {
				try {
					broadcastSocket.receive(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				String name = new String(packet.getData(), 0, packet.getLength());
				
				if(name.equals("Kenny"))
					continue;
				
				if(names.contains(name))
					continue;
				
				names.add(name);
				addresses.add(packet.getAddress());
				
				System.out.println(name);
			}
		});
		receiveBroadcastThread.start();
		
		Thread sendBroadcastThread = new Thread(() -> {
			while(sending) {
				String name = "Kenny";
				
				byte[] data = name.getBytes();
				
				try {
					DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"), 5001);
				
					broadcastSocket.send(packet);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		sendBroadcastThread.start();
		
		//allow the user to choose someone to chat with
		Scanner sc = new Scanner(System.in);
		
		int choice = sc.nextInt() - 1;
		
		String name = names.get(choice);
		InetAddress ip = addresses.get(choice);
		
		Thread t = new Thread(() -> {
			while(true) {
				String text = sc.nextLine();
				
				//TODO: send the text to the other computer
				send(text, messageSocket, ip);
			}
		});
		t.start();
		
		while(true) {
			DatagramPacket packet = new DatagramPacket(new byte[1000], 1000);
			
			messageSocket.receive(packet);
			
			String text = new String(packet.getData(), 0, packet.getLength());
			
			for(int i = 0; i < addresses.size(); i++) {
				if(addresses.get(i).equals(packet.getAddress())) {
					System.out.print(names.get(i) + ": ");
				}
			}
			
			System.out.println(text);
		}
	}
	
	static void send(String message, DatagramSocket socket, InetAddress address) {
		byte[] data = message.getBytes();
				
		try {
			DatagramPacket packet = new DatagramPacket(data, data.length, address, 5000);
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
