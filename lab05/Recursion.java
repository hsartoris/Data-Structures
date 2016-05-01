/**
   Template for lab on recursion.
*/

import java.util.*;

public class  Recursion {
	public static final int NOTFOUND = -1;
    static Random rand = new Random();

    public static boolean isEven(int n) {
    	if (n == 0) return true;
    	if (n < 0) return isOdd(n + 1);
    	return isOdd(n - 1);
    }
    
    public static boolean isOdd(int n) {
    	if (n == 0) return false;
    	if (n < 0) return isEven(n + 1);
    	return isEven(n - 1);
    }

    /*
      Find target in arr, returning true if it's found.
      @param target Item to look for.
      @param arr array to search
      @returns true iff target found in array.
    */
    public static boolean binarySearch(int target, int[ ] arr) {
    	int result = binarySearch(target, arr, 0, arr.length - 1);
    	if (result != -1 && arr[result] == target) return true;
    	return false;
    }

    /**
       @param target Item to look for.
       @param arr array to search from arr[low...hi], inclusive
       @returns index in arr[low..hi] where target is found.
       returns -1 if not found.
    */
    private static int binarySearch(int target,  int[] arr, int low, int hi) {
    	if (low > hi /*|| arr.length == 0*/) return NOTFOUND;
    	int mid = (low + hi) / 2;
    	if (arr[mid] == target) return mid;
    	else if (arr[mid] < target) return binarySearch(target, arr, mid + 1, hi);
    	else return binarySearch(target, arr, low, mid - 1);
    }

    /**
       Sequential search for target in arr.
    */
    public static boolean search(int target, int [ ] arr) {
    	for (int i = 0; i < arr.length ; i++) {
    		if (target == arr[i]) return true;
    	}
    	return false;
    }

    /* Print for those with sore fingers. */
    public static void p(String s) {	System.out.println(s); }

    private static void testSearch() {
    	Random rand = new Random();
    	int counter = 0;
    	int arr[];
    	int errorCount = 0;
    	while (counter < 50) {
    		arr = new int[rand.nextInt(50) + 1]; //rand.nextInt(MIN, MAX) isn't a valid call
    		for (int i = 0; i < arr.length; i++) {
    			arr[i] = rand.nextInt(201) - 100;
    		}
    		Arrays.sort(arr);
    		for (int i = -101; i < 102; i++) {
    			if (binarySearch(i, arr) == search(i, arr)) continue;
    			if (binarySearch(i, arr)){ 
    				p("ERROR. Number " + i + " was erroneously found."); 
    				errorCount++;
    			} else if (!binarySearch(i, arr)) {
    				p("ERROR. Number " + i + " was not found.");
    				errorCount++;
    			}
    		}
    		counter++;
    	}
    	System.out.println(errorCount + " errors in search test.");
    }
    
    public static int[] randArray(int len) {
    	int limit = 50;
    	Random rand = new Random();
    	int[] ret = new int[len];
    	for (int i = 0; i < len; i++) {
    		ret[i] = rand.nextInt(limit);
    	}
    	return ret;
    }
    
    public static int sumArray(int[] arr, int low, int high) {
    	if (low == high) return arr[low];
    	return sumArray(arr, low, (low + high)/2) + sumArray(arr, (low + high)/2 + 1, high);
    }

    public static int hanoi(int n, char src, char dst, char tmp) {
    	if (n == 1) { // I wanted a move count
    		p("Move disk from " + src + " to " + dst);
    		return 1;
    	} else {
    		int temp = 0;
    		temp += hanoi(n - 1, src, tmp, dst);
    		temp += hanoi(1, src, dst, tmp);
    		temp += hanoi(n - 1, tmp, dst, src);
    		return temp;
    	}
    }
    
    public static void main(String[] args) {
    	/*for (int i = -100; i < 100; i++) {
    	*	if (isEven(i) == isOdd(i)) throw new NullPointerException();
    	} */
    	testSearch();
    	for (int counter = 0; counter < 25; counter++) { // test this a lot
	    	int[] testSumArray = randArray(50);
	    	int sum = 0;
	    	for (int i = 0; i < testSumArray.length; i++) sum += testSumArray[i];
	    	if (sumArray(testSumArray, 0, testSumArray.length - 1) != sum) { 
	    		System.out.println("sumArray test failed!");
	    		break;
	    	}
	    }
    	p(hanoi(3, 'A', 'C', 'B') + " moves to complete.");
    	
    }



}
