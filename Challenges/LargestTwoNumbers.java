package robotics;

import java.util.Arrays;
import java.util.Random;

public class LargestTwoNumbers {

	/** Program to find the top 2 numbers in an array */
	
	public static final int NEGATIVE_INFINITY = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		Random random = new Random();
		
		int n = random.nextInt(15) + 5;
		
		int[] array = new int[n];
		
		for(int i = 0; i < n; i++) {
			array[i] = (int) (Math.random() * 100);
			System.out.print(array[i] + " ");
		}
		
		System.out.println();
		System.out.println("--------");
		
		solution1(Arrays.copyOf(array, n));
		System.out.println("--------");
		
		solution2(Arrays.copyOf(array, n));
		System.out.println("--------");
		
		solution3(Arrays.copyOf(array, n), 2);
		System.out.println("--------");
	}
	
	/** 
	 * Rachel Vardi's solution - find the largest number, remove it from the array, and find the largest
	 * again
	 * */
	public static void solution1(int[] arr) {
		if(arr.length < 2) {
			throw new RuntimeException("Ruh-roh!");
		}
		
		//go through the array twice
		for(int j = 0; j < 2; j++) {
			int index = 0;
			
			//loop through the array
			for(int i = 1; i < arr.length; i++) {
				//if the number is greater, update the current maximum
				if(arr[i] > arr[index]) {
					index = i;
				}
			}
			
			System.out.println(arr[index]);
			//minimum value for an int, works as long as the numbers are all greater than this value
			arr[index] = NEGATIVE_INFINITY;
		}
	}
	
	/** Alternative solution - keep track of the highest and second-highest numbers, 
	 *  also keep track of the frequency of each*/
	public static void solution2(int[] arr) {
		int highest = -1;
		int frequency1 = -1;
		
		int second_highest = -1;
		int frequency2 = -1;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > highest) {
				//the number that used to be the largest is now the second largest
				second_highest = highest;
				frequency2 = 1;
				
				highest = arr[i];
				frequency1 = 1;
			}else if(arr[i] == highest) {
				frequency1++;
			}//really important else below
			else if(arr[i] > second_highest) {
				second_highest = arr[i];
				frequency2 = 1;
			}else if(arr[i] == second_highest) {
				frequency2++;
			}
		}
		
		if(frequency1 > 1) {
			System.out.println(highest);
			System.out.println(highest);
		}else{
			System.out.println(highest);
			System.out.println(second_highest);
		}
	}
	
	/** My solution - bubble sort the top k numbers
	 * k = 2 in this case */
	public static void solution3(int[] arr, int k) {
		int[] bubble = new int[k + 1];
		Arrays.fill(bubble, -1);
		
		for(int i = 0; i < arr.length; i++) {
			//put the number in a temporary storage bin at the beginning of the array
			bubble[0] = arr[i];
			for(int j = 0; j < k; j++) {
				//if the current number is greater than the next, swap them
				if(bubble[j] > bubble[j + 1]) {
					//swap
					int tmp = bubble[j];
					bubble[j] = bubble[j + 1];
					bubble[j + 1] = tmp;
				}else{
					//otherwise we can stop -- the number won't bubble any higher
					break;
				}
			}
		}
		
		for(int i = k; i >= 1; i--) {
			System.out.println(bubble[i]);
		}
	}
}

