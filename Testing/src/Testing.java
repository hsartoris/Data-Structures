import java.util.Random;

public class Testing {

	public static void main(String[] args) {
		/*
		String tests[] = {"testing", "good", "recursion", "fabulous"};
		for (int i = 0; i < tests.length; i++) {
			System.out.println(tests[i]);
			System.out.println(reverseString(tests[i]));
		}
		*/
		//System.out.println(fib(20));
		int[] testArray = randArray(10, 20);
		//int[] testArray = {0,7,5,12,11,16,4,14,3,5};
		printArray(testArray);
		quickSort(testArray, 0, testArray.length - 1);
		printArray(testArray);
	}

	public static void printArray(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length; i++) System.out.print(arr[i] + ", ");
		System.out.print("]\n");
	}
	
	public static String reverseString(String s) {
		if (s.length() <= 1) {
			return s;
		} else {
			return reverseString(s.substring(1)) + s.charAt(0);
		}
	}
	
	public static int[] randArray(int length, int max) {
		Random rand = new Random();
		int[] thing = new int[length];
		for (int i = 0; i < length; i++) {
			thing[i] = rand.nextInt(max);
		}
		return thing;
	}
	
	public static long fib(int n) {
		if (n <= 1) return n;
		int curr = 1;
		int prev = 0;
		int temp;
		for (int i = 1; i < n; i++) {
			temp = curr;
			curr = curr + prev;
			prev = temp;
		}
		return curr;
	}
	
	private static long x;
	private static long y;
	
	public static void fullGcd(long a, long b) {
		long x1, y1;
		System.out.println("a " + a + "\tb " + b);
		if (b == 0) {
			x = 1;
			y = 0;
		} else {
			fullGcd(b, a % b);
			
			
		}
	}
	
//	public static void merge(Comparable[] a, int lo, int mid, int hi) {
//		Comparable[] aux = new Comparable[a.length];
//
//		int i = lo, j = mid+1;
//		
//		//copy values to aux array
//		for (int k = lo; k <= hi; k++) aux[k] = a[k];
//		
//		// 0 1 2 3 4 5 6 7 8 9
//		// [----i--] m [--j---]
//		
//		
//		for (int k = lo; k <= hi; k++) {
//			if (i > mid) a[k] = aux[j++];	// if i > mid, it's off its array, and the values from that array are use up
//			else if(j > hi ) a[k] =aux[i++];// if j > mid, it's off its array etc etc
//			else if(less(aux[j], aux[i])) a[k] = aux[j++];
//			else a[k] = aux[i++];
//		}
//	}
	
	public static void quickSort(int[] arr, int low, int high) {
		if (low == high) return;
		int i = low;
		int j = high - 1;
		while (i < j) {
			while(arr[i] < arr[high] && i+1 < high) i++;
			while(arr[j] > arr[high] && j > low) j--;
			if (i < j) swap(arr, i, j);
			//printArray(arr);
			//System.out.println("i: " + i + "; j: " + j);
		}
		//System.out.println("i: " + i + "; j: " + j);
		//System.out.println("Pivot: " + arr[high]);
		swap(arr, i, high);
		quickSort(arr, low, i);
		quickSort(arr, i+1, high);
		
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
