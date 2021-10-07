import java.util.*;

public class P1567A {

     public static void main(String []args) throws Exception {
     	Scanner sc = new Scanner(System.in);
	
	int t = Integer.parseInt(sc.nextLine());
	
	for(int i = 0; i < t; i++){
		int len = Integer.parseInt(sc.nextLine());
		String str = sc.nextLine();

		for(int j = 0; j < len; j++){
			char half = str.charAt(j);

			if(half == 'U'){
				System.out.print("D");
			}else if(half == 'D'){
				System.out.print("U");
			}else if(half == 'L'){
				System.out.print("L");
			}else if(half == 'R'){
				System.out.print("R");
			}
		}
		System.out.println();
	}
	
     }
     
     
}