import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * Return random variates that obey an empirical 
 * probability distribution.
 */

public class CustomDistribution {
	private Random r;
	private double[] freq;

	/**
	 * Generate variates from an empirical distribution.
	 * @param seed  SEED for random number generator
	 * @param filename file containing integer counts, one per line.
	 * 
	 */
	
	public CustomDistribution(long seed,String filename) {
		r = new Random(seed);
		
		Scanner scanner = null;
		try {
			File file = new File(filename);
			scanner = new Scanner(file);
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + filename);
			System.exit(-1);
		}

		ArrayList<Integer> data = new ArrayList<Integer>();
		while (scanner.hasNext()) data.add(scanner.nextInt());
		int i;

		// xfer data to int[] array
		int counts[] = new int[data.size()];
		for (i = 0; i < counts.length; i++) {
			counts[i] = data.get(i);
		}
		
		freq = normalize(counts);
	}

	/**
	 * @param count array of frequencies.  Assumes element count[i] is count
	 * at ith second.
	 */
	public CustomDistribution(String filename) {
		this(System.currentTimeMillis(),filename);
	}

	/**
	 * Return normalized version of counts in x.
	 * Sum of return elements is 1.0.
	 * @param x
	 * @return frequency (normalized) of counts in x.
	 */
	private double[] normalize(int[] x) {
		double sum = 0;

		for (int item: x) {
			sum += item;
		}
		if (sum < 1.0) sum = 1.0;
		
		double[] freq = new double[x.length]; 
		for (int i = 0; i < freq.length; i++) {
			freq[i] = x[i] / sum;
		}
		return freq;
	}

	/**
	 * Get next random variate that obeys distribution.
	 * @return
	 */
	public int next() {
		double rnum = r.nextDouble();
		double sum = 0.0;
		int i = 0;
		for (i = 0; i < freq.length; i++) {
			sum += freq[i];
			if (rnum < sum) return i;
		}
		return i;
	}

	/** Test the number generator. */
	public static void main(String args[]) {
		if (args.length != 1) {
			System.out.println("Usage: java CustomDistribution COUNTSFILE");
			System.exit(-1);
		}

		CustomDistribution dist = new CustomDistribution(args[0]);
		for (int i = 0; i < 100; i++) {
			System.out.println(dist.next());
		}
		
		}


}
