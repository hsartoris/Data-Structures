/*
  This program finds subsequence of largest value in an array of N
  random values.

EXERCISE: 

1. Open a UNIX terminal.  Compile the program.

2. Run it for various input values to obtain run times (e.g., "time
java MaxSumTest 100") from about 10 to about 10000 (or more), each
time increasing the input size by a factor of 10.  Run it 5 times for
each input size that you choose and find the average time for that
input value.  

Your output from the time command will look like this:
0.105u 0.025s 0:00.12 100.0%	0+0k 0+3io 0pf+0w

The first number is the elapsed real-world time ("user time").  Use
this value.


3. On paper plot the average running time versus the size of input ("n"), with
the size of input on the x-axis.  

*/

import java.util.Random;

public final class MaxSumTest3
{
    static private int seqStart = 0;
    static private int seqEnd = -1;


    /**
     * Maximum contiguous subsequence sum algorithm #3.
     * seqStart and seqEnd represent the actual best sequence.
     */
    public static int maxSubSum3( int [ ] a )
    {
        int maxSum = 0;
        int thisSum = 0;

        for( int i = 0, j = 0; j < a.length; j++ )
        {
            thisSum += a[ j ];

            if( thisSum > maxSum )
            {
                maxSum = thisSum;
                seqStart = i;
                seqEnd   = j;
            }
            else if( thisSum < 0 )
            {
                i = j + 1;
                thisSum = 0;
            }
        }

        return maxSum;
    }


    /**
     * Return maximum of three integers.
     */
    private static int max3( int a, int b, int c )
    {
        return a > b ? a > c ? a : c : b > c ? b : c;
    }


    

    
    /**
     * Simple test program.
     */
    public static void main( String [ ] args )
    {
	Random rand = new Random( );
        int a[ ];
        int maxSum;
	int n = 0;
	int maxnum = 100000;
	try {
	    n = Math.abs(Integer.parseInt(args[0]));
	} catch (Exception e) {
	    System.err.println("USAGE: java MaxSumTest problemsize");
	}
	a = new int[n];
	// max random array
	for (int i = 0; i < n; i++) {
	    a[i] = rand.nextInt(maxnum) - maxnum/2;
	}
	// find max subsum
	maxSubSum3(a);
    }
}

