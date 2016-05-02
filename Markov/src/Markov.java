import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Markov {
	private static final String term = "/end"; // string used to represent end of sentence or simple fragment
	private ArrayList<String> words;
	private ArrayList<Word> processedWords;
	
	public Markov(String file) {
		gatherWords(file);
		processWords();
		
	}
	
	public String generateSentence(String init, int length) {
		//Word w = new Word(init);
		//if (processedWords.contains(w)) w = processedWords.get(processedWords.indexOf(w));
		//else return null;
		// why isn't this working
		String output = "";
		for (Word w2 : processedWords) {
			if (w2.equals(init)) {
				output += w2.word();
				for (int i = 1; i < length; i++) {
					if (w2.hasWord()) {
						w2 = w2.getWord(3, false);
						output += " " + w2.word();
					} else {
						return output;
					}
				}
				return output;
			}
		}
		return "Word not found";
		/* 	FUCK IT
		 *
		String output = w.word();
		for (int i = 1; i < length; i++) {
			if (w.hasWord()) {
				w = w.getWord();
				output += " " + w.word();
			} else {
				return output;
			}
		}
		return output;
		*/
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
			prev = curr;
		}
		for (Word w : processedWords) w.sort();
		System.out.println("Finished processing.");
	}
	
	private void gatherWords(String file) {
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
		} catch (FileNotFoundException e) {
			System.out.println("Source file not found! Please try again.");
			return;
		} catch (IOException e) {
			System.out.println("You fucked up but I don't know how.");
			e.printStackTrace();
		}
		System.out.println("Finished gathering");
	}
	
	private static boolean isEndChar(char c) {
		char[] enders = ";.:!?".toCharArray();
		for (char c2 : enders) if (c2 == c) return true;
		return false;
	}
	
	private static boolean isLetter(char c) {
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz'".toCharArray();
		for (char c2: alphabet) if (c2 == c) return true;
		return false;
	}
}
