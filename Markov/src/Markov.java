import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Markov {
	private static final String term = "/end"; // string used to represent end of sentence or simple fragment
	private ArrayList<String> words; // yeah it's confusing whatever
	private ArrayList<Word> processedWords;
	private int totalConnections = 0;
	private boolean dumb; // marks for single-word probability processing or multiple. default to false, multiple
	private int longestSentence = 0;
	private final boolean debug = true;
	private final int maxSentenceLength = 150;
	
	public Markov() {
		processedWords = new ArrayList<Word>();
		dumb = false;
	}
	
	public Markov(boolean singleWordProbability) {
		dumb = singleWordProbability;
	}
	
	public Markov(String file, boolean singleWordProbability) {
		dumb = singleWordProbability;
		processedWords = new ArrayList<Word>();
		if (gatherWords(file) == 0){
			if (!dumb) processSentences();
			else processWords();
		}
		
	}
	
	public void addFile(String filename) {
		if (gatherWords(filename) == 0) {
			if (!dumb) processSentences();
			else processWords();
		}
	}
	
	public String generateSentence(String firstWord, int length) {
		if (!dumb) return generateSentenceMultiple(firstWord, length);
		return generateSentenceSingle(firstWord, length);
	}
	
	private String generateSentenceMultiple(String init, int length) {
		ArrayList<Word> sentence = new ArrayList<Word>();
		for (Word w : processedWords) {
			if (w.equals(init)) {
				sentence.add(w);
				break;
			}
		}
		if (sentence.size() == 0) return "Word not found.";
		while (sentence.size() < length) {
			if (sentence.get(sentence.size()-1).hasWord(0)){
				try {
					sentence.add(sentence.get(sentence.size()-1).getWord(sentence.toArray(new Word[0]), true));
				} catch(NoNextWordException e){
					//ha haaaa do nothing
				}
			} else break;
		}
		String output = "";
		for (Word w : sentence) output += w.data + " ";
		return output;
	}
	
	private String generateSentenceSingle(String init, int length) {
		String output = "";
		for (Word w2 : processedWords) {
			if (w2.equals(init)) {
				output += w2.word();
				for (int i = 1; i < length; i++) {
					if (w2.hasWord()) {
						w2 = w2.getWord(true);
						output += " " + w2.word();
					} else {
						return output;
					}
				}
				return output;
			}
		}
		return "Word not found";
	}
	
	private void processSentences() {
		System.out.println("Processing sentences...");
		ArrayList<Word> sentence = new ArrayList<Word>();
		double count = 0;
		for (String s : words) {
			count++;
			if (count % 10000 == 0 && debug) {
				System.out.println(count + " entries processed out of " + words.size() + ".");
				System.out.println(processedWords.size() + " actual words with " + totalConnections + " connections.");
				System.out.println("Longest sentence so far: " + longestSentence);
				System.out.println("");
			}
			if (count == words.size() - 1) System.out.println("This is the last word!");
			if (s == Markov.term) {
				if (sentence.size() > maxSentenceLength && debug) for (Word w : sentence) System.out.print(w + " ");
				if (sentence.size() > maxSentenceLength) {
					sentence = new ArrayList<Word>();
					continue;
				}
				//actually need to process the sentence that has been found here
				for (int i = 0; i < sentence.size() - 1; i++) {
					for (int j = i + 1; j < sentence.size(); j++) {
						sentence.get(i).addSentenceConnection(j - (i + 1), sentence.get(j));
						totalConnections++;
					}
				}
				if (sentence.size() > longestSentence) longestSentence = sentence.size();
				sentence = new ArrayList<Word>();
				continue;
			}
			Word temp = new Word(s);
			boolean found = false;
			for (Word w : processedWords) {
				if (w.equals(s)) {
					temp = w;
					found = true;
					break;
				}
			}
			sentence.add(temp);
			if (!found) processedWords.add(temp);
		}
		for (Word w : processedWords) w.sort();
		System.out.println("Finished processing");
	}
	
	private void processWords() {
		System.out.println("Processing words...");
		// THIS DOES NOT HANDLE DATA WITH QUOTATIONS WELL OR RATIONALLY
		processedWords = new ArrayList<Word>();
		Word prev = null;
		Word curr;
		for (String s : words) {
			if (s == Markov.term) { // if the last word was the end of a sentence I don't want to connect the next one to it
				prev = null;
				continue;
			}
			curr = new Word(s);
			boolean found = false;
			for (Word w : processedWords) {
				if (w.equals(s)) {
					curr = w;
					found = true;
					break;
				}
			}
			if (!found) processedWords.add(curr);
			if (prev != null) prev.addConnection(curr);
			totalConnections++;
			prev = curr;
		}
		for (Word w : processedWords) w.sort();
		processedWords.trimToSize();
		System.out.println("Finished processing.");
	}
	
	private int gatherWords2(String file) {
		System.out.println("Gathering words...");
		words = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			String[] lineWords;
			while (line != null) {
				lineWords = line.split(" ");
				for (String word : lineWords) {
					if (word.length() == 0) continue;
					if (word.length() == 1 && word.charAt(0) != 'a') continue;
					boolean sQuote = false;
					boolean eQuote = false;
					for (int i = 0; i < word.length(); i++) {
						if (!Markov.isLetter(word.charAt(i))) {
							
						}
					}
					
					
					boolean startsWithQuote = false;
					if (word.charAt(0) == "'".charAt(0) || word.charAt(0) == '"') {
						startsWithQuote = true;
						word = word.substring(1, word.length());
					}
					if (word.charAt(word.length() - 1) == "'".charAt(0) || word.charAt(word.length() - 1) == '"') {
						word = word.substring(0, word.length() - 1);
						if (startsWithQuote) {
							words.add(word);
						} else {
							
						}
						
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Source file not found! Please try again.");
			return -1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private int gatherWords(String file) {
		System.out.println("Gathering words...");
		words = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line = br.readLine();
		    String newWord;
		    char[] chArray;
		    while (line != null) {
		    	line = line.toLowerCase();
		    	chArray = line.toCharArray();
		    	newWord = "";
		    	for (char c : chArray) {
		    		if (!Markov.isLetter(c)) {
		    			if (newWord.length() > 0) {
		    				words.add(newWord);
		    				newWord = "";
		    				if (Markov.isEndChar(c)) words.add(Markov.term);
		    			}
		    		} else {
		    			newWord += c;
		    		}
		    	}
		    	if (newWord.length() > 0) words.add(newWord);
		        line = br.readLine();
		    }
		    words.add(Markov.term);
		} catch (FileNotFoundException e) {
			System.out.println("Source file not found! Please try again.");
			return -1;
		} catch (IOException e) {
			System.out.println("You fucked up but I don't know how.");
			e.printStackTrace();
		}
		words.trimToSize();
		System.out.println("Finished gathering, make sure to process!");
		return 0;
	}
	
	private static boolean isQuote(char c) {
		if (c == "'".charAt(0) || c == '"') return true;
		return false;
	}
	
	private static boolean isEndChar(char c) {
		char[] enders = ";.:!?'".toCharArray();
		for (char c2 : enders) if (c2 == c) return true;
		if (c == '"') return true;
		return false;
	}
	
	private static boolean isLetter(char c) {
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz'-".toCharArray();
		for (char c2: alphabet) if (c2 == c) return true;
		return false;
	}
	
	public int totalWords() {
		return processedWords.size();
	}
	
	public int totalConnections() {
		return totalConnections;
	}
	
	public String toString() {
		String probType = "mulitple-word";
		if (dumb) probType = "single-word";
		String output = "Markov chain generator running in " + probType + " mode. \n" + totalWords() + " total words in use. \n"
			+ totalConnections + " total connections word to word. \n";
		if (!dumb) output += "The longest processed sentence was " + longestSentence + " words deep.";
		return output;
	}
}
