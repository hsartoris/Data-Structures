import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Markov {
	static ArrayList<String> words;
	
	public static void main(String[] args) {
		if (args.length == 1) {
			System.out.println("Gathering words...");
			gatherWords(args[0]);
		}
		
		
		
	}
	
	private static void gatherWords(String file) {
		words = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line = br.readLine();
		    String newWord;
		    char[] chArray;
		    while (line != null) {
		    	chArray = line.toCharArray();
		    	newWord = "";
		    	for (char c : chArray) {
		    		if (!isLetter(c) && newWord.length() > 0) {
		    			words.add(newWord);
		    			newWord = "";
		    		} else {
		    			newWord += c;
		    		}
		    	}
		        line = br.readLine();
		    }
		} catch (FileNotFoundException e) {
			System.out.println("Source file not found! Please try again.");
		} catch (IOException e) {
			System.out.println("You fucked up but I don't know how.");
			e.printStackTrace();
		}
		System.out.println("Finished gathering words");
	}
	
	private static boolean isLetter(char c) {
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz'".toCharArray();
		for (char c2: alphabet) {
			if (c2 == c) return true;
		}
		return false;
	}
}
