import java.util.Scanner;

public class TwentyQuestions {
	
	public static Scanner sc;
	public static Node root;
	
	public static void main(String[] args) throws Exception {
		sc = new Scanner(System.in);
		
		root = new Node();
		root.animal = "lion";
		root.question = "Is this animal a lion?";
		
		while(true) {
			recurse(root);
			
			System.out.println("Would you like to play again?");
			String answer = sc.nextLine();
			
			if(answer.toLowerCase().startsWith("n")) {
				break;
			}
		}
		
		sc.close();
	}

	public static void recurse(Node n) {
		System.out.println(n.question);
		
		String s = sc.nextLine();
		s = s.toLowerCase();
		
		if(s.startsWith("y")) {
			if(n.isLeaf()) {
				System.out.println("Thanks for playing! :D");
				return;
			}
			
			recurse(n.y);
		}else if(s.startsWith("n")) {
			if(n.isLeaf()) {
				System.out.println("What would be a good question to ask here?");
				String question = sc.nextLine();
				
				System.out.println("What is your animal?");
				String animal = sc.nextLine();
				
				System.out.println("Would you answer yes to your question?");
				String answer = sc.nextLine();
				
				Node yes = new Node();
				Node no = new Node();
				
				if(answer.toLowerCase().startsWith("y")) {
					yes.animal = animal;
					yes.question = "Is your animal a " + animal + "?";
					
					no.animal = n.animal;
					no.question = n.question;
					
					n.question = question;
				}else{
					no.animal = animal;
					no.question = "Is your animal a " + animal + "?";
					
					yes.animal = n.animal;
					yes.question = n.question;
					
					n.question = question;
				}
				
				n.y = yes;
				n.n = no;
				
				return;
			}
			
			recurse(n.n);
		}else{
			System.out.println("Sorry, this didn't work, please try again");
			recurse(n);
			
			return;
		}
	}

	public static class Node {
		
		public String question;
		public String animal;
		public Node y = null, n = null;
		
		public boolean isLeaf() {
			return y == null && n == null;
		}
		
	}
	
}
