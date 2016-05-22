
/**
   Implements a hashtable with buckets that can hold any number of
   elements.
 */
import java.util.*;

public class HashTable {

	private static final int DEF_TABLESIZE = 100;
	private int numItems; // number of items in the table
	private LinkedList[] hashTable; // hashtable is array of lists
	private ArrayList<Integer> hashlist = new ArrayList<Integer>(); // to
																	// provide
																	// histogram
	private ArrayList<Integer> hashes = new ArrayList<Integer>();

	/**
	 * Builds empty hash table of size DEF_TABLESIZE
	 */
	public HashTable() {
		this(DEF_TABLESIZE);
	}

	/**
	 * Constructs an empty hash table of size tableSize.
	 * 
	 * @param size
	 *            must be greater than zero.
	 */
	public HashTable(int size) {
		hashTable = new LinkedList[size];
		numItems = 0;
		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i] = new LinkedList();
		}
	}

	/**
	 * Return the number of items in the table.
	 */
	public int numEntries() {
		return numItems;
	}

	/**
	 * Return the number of buckets in the table.
	 */
	public int numBuckets() {
		return hashTable.length;
	}

	/**
	 * Make histogram of all hashed values.
	 */
	public void makeHist() {
		Histogram hist = new Histogram();
		double[] hdat = new double[hashlist.size()];

		for (int k = 0; k < hashlist.size(); k++) {
			hdat[k] = hashlist.get(k);
		}
		// histogram of all bucket sizes
		hist.draw(hdat, hashTable.length);
	}

	public void stats() {
		int i;
		int nfilled = 0;
		int n = 0;
		int max = 0;
		int[] nwords = new int[10]; // counter for buckets containing num words

		for (i = 0; i < nwords.length; i++)
			nwords[i] = 0;

		for (i = 0; i < hashTable.length; i++) {
			n = countBucket(i); // number in bucket i
			if (n > 0)
				nfilled++; // non-empty bucket
			if (n > max)
				max = n; // largest bucket
			if (n >= nwords.length)
				n = nwords.length - 1; //
			nwords[n] += 1;

		}
		System.out.println("Number of items " + numItems);
		System.out.println("Number of buckets " + hashTable.length);
		System.out.println("Num. Items per bucket " + numItems / ((double) hashTable.length));

		System.out.println("Number of non-empty buckets " + nfilled);
		System.out.println("Average number of items per filled bucket " + numItems / ((double) nfilled));
		System.out.println("Largest bucket " + max);
		for (int j = 0; j < nwords.length - 1; j++) {
			System.out.println("Number of buckets with " + j + " elements " + nwords[j] + "\t"
					+ 100.0 * nwords[j] / (float) hashTable.length + " %");
		}

		System.out.println("Number of buckets with >=" + (nwords.length - 1) + " elements " + nwords[nwords.length - 1]
				+ "\t" + 100.0 * nwords[nwords.length - 1] / (float) hashTable.length + " %");
		System.out.println("Chi^2: " + ChiSquaredStatistic());

		makeHist();
	}

	/**
	 * Insert the key-value pair (newKey, newInfo) into the hash table. PRE: Key
	 * must be valid.
	 */
	public void insert(Hashable key, Object item) {
		int i = hash(key);
		hashlist.add(i);

		TableEntry entry = new TableEntry(key, item);
		hashTable[i].prepend(entry);
		numItems++;
	}

	/**
	 * Call this function to hash the key.
	 */

	private int hash(Hashable key) {
		int hval = key.hashFunction();
		hashes.add(hval);
		hval = hval % hashTable.length;
		if (hval < 0)
			hval += hashTable.length;
		return hval;
	}

	/**
	 * PRE: Key must be valid If key can be found in the table, returns the
	 * corresponding key, value) pair. Otherwise, returns null.
	 */
	public Object find(Hashable key) {

		// determine which bucket contains key
		int i = hash(key);

		// search for the key in this bucket
		ListIterator bucketItems = hashTable[i].getElements();
		while (bucketItems.hasNext()) {

			TableEntry entry = (TableEntry) bucketItems.next();
			if (entry.key.equals(key))
				return entry.item;
		}

		// not found
		return null;
	}

	/**
	 * @return the number of items in bucket i of the table.
	 */
	private int countBucket(int i) {
		List l = hashTable[i];
		ListIterator li = l.getElements();
		int k = 0;

		while (li.hasNext()) {
			li.next();
			k++;
		}
		return k;
	}

	public double ChiSquaredStatistic() {
		double output = 0;
		LinkedHashSet<Integer> t = new LinkedHashSet<Integer>(hashes);
		int nfilled = t.size();
		System.out.println(nfilled);
		double n_m = (double) nfilled / hashTable.length;
		double temp;
		for (int i = 0; i < hashTable.length; i++) {
			temp = countBucket(i) - n_m;
			output += temp * temp;
		}

		return output / n_m;
	}
}
