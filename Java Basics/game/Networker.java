import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/** A class for easy networking between devices on the same network. 
 * 
 * Limitations:
 * 	- The performance will be good as long as there are less than 10,000 other
 * 	- people running this program on the same network. Ports 5000 and 5001 must be free
 * 	for this to work. 
 * 	- 100 character limit for names, and 1000 character limit for messages
 * 	- unread messages will stay in the memory until processed */
public class Networker {

	String name;
	boolean sending = true, receiving = true;
	
	DatagramSocket broadcastSocket, messageSocket;
	List<String> names = new ArrayList<>();
	List<InetAddress> addresses = new ArrayList<>();
	
	public String otherName;
	public InetAddress otherIP;
	
	BlockingDeque<String> messages;
	
	/** The name associated with this user. It's the name you would like others to know you by. Note that
	 * if two users have the same name, they will not be able to find each other. */
	public Networker(String name) throws SocketException {
		this.name = name;
		
		messageSocket = new DatagramSocket(5000);
		broadcastSocket = new DatagramSocket(5001);
		
		messages = new LinkedBlockingDeque<>();
	}
	
	/** Call this function when you're ready to find other users.
	 * 	Make sure you're not reading from System.in before you call this function */
	public void findUsers() {
		new Thread(this::receiveBroadcast).start();
		new Thread(this::sendBroadcast).start();
		
		Scanner sc = new Scanner(System.in);
		
		int choice = sc.nextInt() - 1;
		
		otherName = names.get(choice);
		otherIP = addresses.get(choice);
		
		new Thread(this::receiveMessages).start();
	}
	
	/** Call this function to send data to your peer. Note that the user has to select a peer in findUsers
	 * 	before this function can do anything */
	public void send(String s) {
		if(otherName == null)
			throw new RuntimeException("No peer selected");
		
		byte[] data = s.getBytes();
		
		try {
			DatagramPacket packet = new DatagramPacket(data, data.length, otherIP, 5000);
			messageSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * Retrieves and removes the earliest unprocessed message this program has received.  */
	public String earliestMessage() {
		try {
			return messages.takeFirst();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** Retrieves and removes the latest unprocessed message this program has received */
	public String latestMessage() {
		try {
			return messages.takeLast();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	void sendBroadcast() {
		while(sending) {
			byte[] data = name.getBytes();
			
			try {
				DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"), 5001);
			
				broadcastSocket.send(packet);
			
				Thread.sleep(1000);
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	void receiveBroadcast() {
		DatagramPacket packet = new DatagramPacket(new byte[100], 100);
		
		while(sending) {
			try {
				broadcastSocket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String name = new String(packet.getData(), 0, packet.getLength());
			
			if(name.equals(this.name))
				continue;
			
			if(names.contains(name))
				continue;
			
			names.add(name);
			addresses.add(packet.getAddress());
			
			System.out.println(names.size() + ". " + name);
		}
	}
	
	public void receiveMessages() {
		while(receiving) {
			DatagramPacket packet = new DatagramPacket(new byte[1000], 1000);
			
			try {
				messageSocket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String text = new String(packet.getData(), 0, packet.getLength());
			
			messages.add(text);
		}
	}
	
}
