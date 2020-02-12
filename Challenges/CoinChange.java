public class CoinChange {

     public static void main(String []args){
     
     int x = 3;
     int y = 17;

     for(int l = 0; l<101; l+=1){
     	if(l%x == 0){
     		System.out.println(l);
     		continue;
     	}
     	if(l%y == 0){
     		System.out.println(l);
     		continue;
     	}
     	for(int a = 0; a < 100/x && 3 * a <= l; a++){
     		if((l-(3*a))%17 == 0){
     			int b = (l-(3*a))/17;
     			System.out.println(l + ":" + a + " " + b);
     	
     		}
     	}
     	
     	}

     }
     
     
}