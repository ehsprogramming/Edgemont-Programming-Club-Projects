package packets;

import java.io.IOException;
import java.util.Scanner;

import packets.networking.NetworkHandler;
import packets.networking.Person;

public class ChattingApp {

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What's your name?");
		String name = sc.nextLine();
		
		NetworkHandler handler = new NetworkHandler(name, (m, p) -> System.out.println(p.name + ": " + m));
		
		System.out.println("Looking for other people...");
		
		//find peers for 5 seconds
		for(int i = 0; i < 5; i++) {
			handler.findPeers();
			
			Thread.sleep(1000);
		}
		
		System.out.println("The following people are available to chat: ");
		int counter = 1;
		for(Person p: handler.people) {
			System.out.println(counter + ". " + p.name);
			counter++;
		}
		
		int idx = Integer.parseInt(sc.nextLine()) - 1;
		Person person = handler.people.get(idx);
		
		while(true) {
			String message = sc.nextLine();
			
			handler.sendMessage(message, person);
		}
	}

}
