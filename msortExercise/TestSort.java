/**
   Test a sorting algorithm.
*/

import java.util.*;

public class TestSort {

    public static void main(String[] args) {
	TestSort tl = new TestSort();
	tl.testCorrectness();
    }
    

    /*
      Test sorting algorithm.
    */
    public void testCorrectness() {
	int i;
	Random rand = new Random();
	int rlimit = 100;

	// Randomly insert into sorted list
	p("\nTesting MergeSort");
	SortedLinkedList<Integer> slist = new SortedLinkedList<Integer>();
	for (i = 0; i < 10; i++ ) {
	    //slist.append(new Integer((int) (20 * Math.random())));
	    slist.append(rand.nextInt(rlimit));
	}
	p("Unsorted random list:  " + slist);
	slist.sort();
	p("list should be sorted:  " + slist);

	

	p("Empty List");
	slist = new SortedLinkedList<Integer>();
	p("Unsorted random list:  " + slist);
	slist.sort();
	p("list should be sorted:  " + slist);

	p("One item list");
	slist.append(rand.nextInt(rlimit));

	p("Unsorted random list:  " + slist);
	slist.sort();
	p("list should be sorted:  " + slist);

	/**/
	for (int bignum = 2; bignum < Math.pow(2,20); bignum *= 2) {
	    slist = new SortedLinkedList<Integer>();

	    for (i = 0; i < bignum; i++) slist.append(rand.nextInt(bignum));
	    p("Testing a random list of size:  " + slist.size());
	    slist.sort();
	    p("sorted!");
	    Integer num1 = slist.head();
	    Integer num2 = num1;
	    for (i = 1; i < bignum; i++) {
		num1 = num2;
		num2 = slist.head(); // should be true that num1 <= num2
		if (num1.compareTo(num2) > 0) {
		    System.out.println("Sort failed with numbers: " + num1 + " " + num2);
		    System.exit(-1);
		}
	    }
	}
	System.out.println("Sort test passed.");
	
    }


    // for debugging
    public static void p(Object o) {System.out.println(o);}

}
