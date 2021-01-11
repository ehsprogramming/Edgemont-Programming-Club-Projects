package packets.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkHandler {

	public static final int BROADCAST_PORT = 5000, SEND_PORT = 5001;
	public List<Person> people;
	public String name;
	
	DatagramSocket broadcastSocket, messagingSocket;
	MessageHandler handler;
	Map<InetAddress, Person> map;
	
	public NetworkHandler(String name, MessageHandler handler) throws SocketException {
		this.name = name;
		this.handler = handler;
		
		people = new ArrayList<>();
		map = new HashMap<>();
		broadcastSocket = new DatagramSocket(BROADCAST_PORT);
		messagingSocket = new DatagramSocket(SEND_PORT);
		
		new Thread(this::receiveBroadcast).start();
		new Thread(this::receiveMessage).start();
	}
	
	public void findPeers() throws IOException {
		//send broadcast
		byte[] message = name.getBytes();
		DatagramPacket packet = new DatagramPacket(message, message.length, InetAddress.getByName("255.255.255.255"), BROADCAST_PORT);
		broadcastSocket.send(packet);
	}
	
	public void receiveBroadcast() {
		DatagramPacket packet = new DatagramPacket(new byte[100], 100);
		while(true) {
			try {
				broadcastSocket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String name = new String(packet.getData(), 0, packet.getLength());
			
			if(name.equals(this.name)) {
				continue;
			}
			
			Person p = new Person(name, packet.getAddress());
			people.add(p);
			map.put(packet.getAddress(), p);
		}
	}
	
	public void sendMessage(String message, Person p) throws IOException {
		byte[] data = message.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, p.address, SEND_PORT);
		
		messagingSocket.send(packet);
	}
	
	public void receiveMessage() {
		while(true) {
			DatagramPacket packet = new DatagramPacket(new byte[1000], 1000);
			while(true) {
				try {
					messagingSocket.receive(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				String data = new String(packet.getData(), 0, packet.getLength());
				handler.receiveMessage(data, map.get(packet.getAddress()));
			}
		}
	}
	
}
