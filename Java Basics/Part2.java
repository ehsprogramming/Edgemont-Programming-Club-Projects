import java.util.Arrays;
import java.util.Scanner;

public class Part2 {

	public static void main(String[] args) {
		int[] array = new int[5];
		
		for(int i = 0; i < 5; i++) { //iterate over every "box" in the array
			//set the value to be a random number
			array[i] = (int) (Math.random() * 10);
		}
		
		System.out.println(Arrays.toString(array));
		
		//make another array where each "box" in the new array contains the sum of all
		//the previous boxes in the array you made for part 1
		//array = 
		//{3, 0, 2, 0, 5}
		//{3, 3, 5, 5, 10}
		
		int[] s = new int[5];
		
		s[0] = array[0];
		for(int i = 0; i < 5 - 1; i++) {
			s[i + 1] = s[i] + array[i + 1];
		}
		
		System.out.println(Arrays.toString(s));
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What is your name? ");
		String name = sc.nextLine();
		
		System.out.println("Hi " + name);
		
		int i = sc.nextInt();
		System.out.println(i);
		
		//blackjack, battleship, war, pokemon, poker, presidents, tic tac toe
	}
	
}
