/**
 * Words are Hashable Strings.
 */

public class Word implements Hashable {
	private String word;

	/**
	 * Initialize the word (kept in lower case only).
	 */
	public Word(String s) {
		// word = s.toLowerCase();
		word = new String(s);
	}

	/**
	 * @return the String value of this word
	 */
	public String toString() {
		return word;
	}

	/**
	 * @return the length of the word
	 */
	public int length() {
		return word.length();
	}

	public int hashFunction() {
		return hashFunction8();
	}

	/**
	 * @return the hashed value of this String as an integer. Hashed value in
	 *         the range [0 .. Integer.MAX_VALUE]
	 */
	public int hashFunction1() {
		if (word.length() == 0)
			return 0;
		int ichar = word.charAt(0) - 'a';
		if (ichar < 0)
			ichar = -ichar;
		return ichar;
	}

	/**
	 * Simple function that returns sum of characters in the word.
	 */

	public int hashFunction2() {
		int hashVal = 0;
		for (int i = 0; i < word.length(); i++) {
			hashVal += word.charAt(i);
		}
		return hashVal;
	}
	
	// returns hashed value based on first d chars in range 0 - Integer.MAX_VALUE
	// doesn't actually work
	
	public int hashFunction3(int depth) {
		if (word.length() == 0) return 0;
		if (depth >= word.length()) depth = word.length() - 1;
		int hashVal = 0;
		for (int i = 0; i < depth; i++) {
			double charVal = (Integer.MAX_VALUE / Math.pow(26, depth)) * (word.charAt(depth) - 'a') / 26;
			hashVal += (int) charVal;
		}
		return hashVal;
	}
	
	//returns hashed value based on first 2 chars; range: 0 - a large number
	public int hashFunction4() {
		if (word.length() == 0) return 0;
		int largeNum = Hashable.HASH_RANGE;
		int hashVal = 0;
		hashVal += (int) largeNum * (((double)word.charAt(0) -  'a') / 26);
		//if (word.length() == 1) return hashVal;
		//hashVal += (int) (largeNum / 26) * (((double)word.charAt(1) - 'a') / 26);
		//if (word.length() == 2) return hashVal;
		//hashVal += (int) (largeNum / (26 * 26)) * (((double)word.charAt(2) - 'a') / 26);
		return hashVal;
		
		
	}
	
	public int hashFunction5() {
		return word.hashCode();
	}
	
	public int hashFunction6() {
		int hash = 0;
		for (int i = 0; i < word.length(); i++) {
			hash += word.charAt(i) * (i + 1);
		}
		return hash;
	}
	
	public int hashFunction7() {
		int prime = 50;
		int hash = 0;
		for (int i = 0; i < word.length(); i++) {
			hash += word.charAt(i) + ((i + prime) * prime);
		}
		return hash;
	}
	
	public int hashFunction8() {
		if (word.length() == 0) return 0;
		int hash = 1;
		for (int i = 0; i < word.length(); i++) {
			hash *= word.charAt(i);
			hash /= i + 1;
		}
		return hash;
	}

	/**
	 * @param points
	 *            to a valid Word
	 * @return true iff string values are equal
	 */
	public boolean equals(Object o) {
		Word wo = (Word) o;

		return word.equals(wo.word);
	}

}
