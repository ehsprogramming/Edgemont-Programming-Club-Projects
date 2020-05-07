import java.util.Arrays;

public class MergeSort {

	public static int[] array, tempArray;
	
	public static void main(String[] args) {
		array = new int[10];
		
		for(int i = 0; i < 10; i++) {
			array[i] = (int)(Math.random() * 100) + 1;
		}
		
		tempArray = new int[10];
		
		System.out.println(Arrays.toString(array));
		sort(array);
		System.out.println(Arrays.toString(array));
	}
	
	public static void sort(int[] array) {
		sortHalf(0, array.length - 1);
	}
	
	public static void sortHalf(int l, int r) {
		if(r - l + 1 == 1) {
			return;
		}
		
		if(r - l + 1 == 2) {
			if(array[l] > array[r]) {
				int tmp = array[l];
				array[l] = array[r];
				array[r] = tmp;
				return;
			}else{
				return;
			}
		}
		
		int mid = (l + r) / 2;
		sortHalf(l, mid);
		sortHalf(mid + 1, r);
		
		for(int i = l; i <= r; i++) {
			tempArray[i] = array[i];
		}
		
		int pointer1 = l;
		int pointer2 = mid + 1;
		
		for(int i = l; i <= r; i++) {
			if(pointer1 > mid) {
				array[i] = tempArray[pointer2];
				pointer2++;
			}else if(pointer2 > r) {
				array[i] = tempArray[pointer1];
				pointer1++;
			}else{
				if(tempArray[pointer1] < tempArray[pointer2]) {
					array[i] = tempArray[pointer1];
					pointer1++;
				}else{
					array[i] = tempArray[pointer2];
					pointer2++;
				}
			}
		}
	}
	
}
