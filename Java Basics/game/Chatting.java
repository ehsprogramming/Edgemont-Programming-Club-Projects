import java.util.Scanner;

public class Chatting {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		Networker n = new Networker(sc.nextLine());
		
		n.findUsers();
		
		new Thread(() -> {
			while(true) {
				System.out.println(n.otherName + ": " + n.earliestMessage());
			}
		}).start();
		
		while(true) {
			String message = sc.nextLine();
			
			n.send(message);
		}
	}
	
}
