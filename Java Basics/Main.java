import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello!");
		System.out.print("Hi!");
		System.out.print("Asdf!");
		
		//Type name = _______
		String text = "afdlkjasdflkjsadflkjdsaflkjsadflk";
		//byte, short, int, long
		//float, double
		//boolean
		//char - stores unicode character
		
		int x = 54; //no ** for power, java primitives have a size limit
		x = x / 2;
		x = x * 3;
		
		//make a random decimal number and give the variable whatever name you want
		//do some operations on it
		//print the result
		
		float f = 2.5f;
		
		char c = '\n';
		
		int i = (int) (Math.random() * 60);
		
		if(i == 0) {
			//do stuff here
		}else if(i == 1){ //else if instead of elif
			
		}else{
			
		}
		
		char a = 'a';
		while(a == 'a') {
			System.out.println(a);
			a = 'b';
		}
		
		//print anything 10 times using while loops
		int numTimes = 0;

		while(numTimes != 10){
			System.out.println("Anything");
			numTimes = numTimes + 1;
		}
		
		//numTimes = 0, so "Anything"
		//numTimes = 1, then run the while loop again
		//numTimes != 10, so print "Anything"
		//numTimes = 2, so ....
		
		//for i in range(10):
		for(int times = 0; times != 10; times++) {
			System.out.println(times);
		}
		
		//print 0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9
		int sum = 0;
		for(int num = 0; num <= 9; num = num + 2) {
			sum = sum + num;
			System.out.println("New running sum: " + sum);
		}
		System.out.println(sum);
		
		//add 2 + 4 + 6 + 8 + 10
		//notation: x++ means the same as x = x + 1
		for(int num = 0; /* starting number */ num <= 10; num = num + 2) {
			
		}
		System.out.println(x);
		
		int[] array = new int[5];
		array[0] = 4;
		array[1] = 5;
		array[2] = 12;
		
		System.out.println(array[0] + " " + array[1] + " " + array[2]);
		
		//try making an array with 3 values
		//print each of these using []
		array = new int[]{0, 123, 4};
		
		//0, 1, 2, 3, 4, 5
		//do this with a loop
		array = new int[6];
		
		for(i = 0; i <= 5; i = i + 1) {
			//add the arrays together
			array[i] = i;
		}
		
		System.out.println(Arrays.toString(array));
		
		function();
	}
	
	public static void function() {
		int a = 0, b = 1;
		
		for(int i = 0; i < 50; i++) {
			int c = a + b;
			a = b;
			b = c;
		}
		
		System.out.println(a);
	}
	
}
