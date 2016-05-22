
/**
   Test hashtable by comparing words in a document with words
   in a dictionary.
 */

import java.io.*; // for PrintWriter

public class SpellCheck {
	// default values for dictionary and document
	static String dictFilename = "/usr/share/dict/words";
	static String docFilename = "ispell.words";

	static WordFile dictionary; // dictionary of Words
	static WordFile document; // document to check

	/**
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		int size = 500; // default dictionary size
		size = 2460;

		if (args.length == 3) {
			dictFilename = args[0];
			docFilename = args[1];
			size = Integer.parseInt(args[2]);
		} else if (args.length != 0) {
			System.out.println("USAGE: java SpellCheck [dictionaryfile documentfile tablesize]");
			System.exit(-1);
		}

		// open files
		dictionary = new WordFile(dictFilename);
		document = new WordFile(docFilename);

		// create dictionary
		HashTable dict = new HashTable(size);
		makeDictionary(dict);

		// read the document into a list
		LinkedList wordlist = new LinkedList();
		int count = 0;
		while (!document.isEOF()) {

			// get next word
			Word checkWord = document.nextWord();

			// insert word into list
			if (checkWord != null) {
				wordlist.append(checkWord);
				count++;
			}
		}
		System.out.println("Read " + count + " words in " + docFilename);

		LinkedList incorrectwords = check(dict, wordlist);

		// printMistakes(incorrectwords);
		dict.stats();
	}

	/**
	 * @param dict
	 *            is a valid HashTable
	 * @param size
	 *            maximum number of words read from file Reads and stored
	 *            (word,wordlength) in hashtable.
	 */

	private static void makeDictionary(HashTable dict) throws IOException {
		int num = 0;
		while (!dictionary.isEOF()) {
			// get next word
			Word newWord = dictionary.nextWord();
			if (newWord != null) {
				dict.insert(newWord, new Integer(newWord.length()));
				num++;
			}
		}
	}

	/**
	 * @param wordlist
	 *            List of Words to check
	 * @param dict
	 *            the dictionary to use when checking
	 * @return List of the misspelled words.
	 */
	private static LinkedList check(HashTable dictionary, LinkedList wordlist) {

		LinkedList incorrectWordList = null;

		incorrectWordList = new LinkedList();
		ListIterator words = wordlist.getElements();

		// Loop over all words
		while (words.hasNext()) {
			Word nextWord = (Word) words.next();
			// System.out.println("Next word " + nextWord);
			Integer foundData = (Integer) dictionary.find(nextWord);
			if (foundData == null)
				incorrectWordList.append(nextWord);
		}

		return incorrectWordList;
	}

	private static void printMistakes(LinkedList incorrectWordList) {
		int count = 0;

		System.out.println("Misspelled Words:");

		ListIterator incorrectWords = incorrectWordList.getElements();

		while (incorrectWords.hasNext()) {
			Word nextWord = (Word) incorrectWords.next();
			System.out.println("\t" + nextWord);
			count++;
		}
		System.out.println("Number of Misspelled Words: " + count);
	}

}
