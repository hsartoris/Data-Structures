/**
 * Represents a standard deck of 52 Card objects.
 * @author 
 * @version 1.0 written Feb 2014
 * @version 1.1 written Feb 2016
 */

import java.util.* ;


public class Deck
{
    private Card[  ] deck; // the cards
    private int numDealt ; // number of cards dealt so far, also indicates current card
    private static Random rand = new Random() ; // used to generate random numbers

    /**
       Create a new 52-card deck that is shuffled.
    */
    public Deck() {
    	reset();
    }

    /**
       Return deck to a new fully shuffled state with 52 cards.
    */
    public void reset() {
		numDealt = 0;
		
		deck = new Card[Card.MAXVAL * Card.NSUITS];
		for (int i = 0; i < deck.length; i++) {
			deck[i] = new Card(i/Card.NVALUES, (i - ((i/Card.NVALUES) * Card.NVALUES) + 1));
		}
	
		shuffle();
    }

    /**
       Returns true if and only if the deck is empty.
    */
    public boolean empty() {
    	if (numDealt == deck.length) {
    		return true;
    	}
    	return false;
    }

    /**
       Deals one card from top of the deck.  Card is removed.
       Will raise the EmptyDeck exception if called on an empty deck!!

       @return next card from top of deck
       @throws Java's NoSuchElementException if the deck is empty
    */
    public Card dealCard() throws java.util.NoSuchElementException {
		if (numDealt < deck.length) {
			numDealt++;
			return deck[numDealt - 1];
		}
		throw new java.util.NoSuchElementException();
    }

    /**
       Use Knuth's shuffling algorithm to shuffle a deck.
       Shuffles all cards in the current deck.
    */
    public void shuffle() {
	/* 
	   From http://en.wikipedia.org/wiki/Fisher-Yates_shuffle

	   To shuffle an array a of n elements (indices 0 to n-1):

	   for i from n-1 down to 1 do:
	        j = a random integer with 0 <= j <= i
		exchange a[j] and a[i]

	*/
    	int j;
    	Card tmp;
    	for (int i = deck.length - 1; i > 0; i--) {
    		j = rand.nextInt(i+1);
    		tmp = deck[i];
    		deck[i] = deck[j];
    		deck[j] = tmp;
    	}
    }

    /**
       @return string equivalent of entire deck.
    */
    public String toString() {
		String retval = "";
		for (int i = numDealt; i < deck.length; i++) {
		    retval += deck[i].toString() + "\n";
		}
		return retval;
    }

    /** check card dealing for empty condition */
    public static void test1() {
		Deck d1 = new Deck();
	
		for (int i = 0; i < 52; i++) {
		    d1.dealCard();
		}
		if (d1.empty()) 
		    System.out.println("OK, deck is empty.");
		else
		    System.out.println("ERROR, deck should be empty.");	    
		    
		try {
		    d1.dealCard();  //raise Exception
		    System.out.println("ERROR exception was not raised.");
		} catch (Exception e) {
		    System.out.println("OK Exception raised: " + e);
		}
    }

    public static void test2() {
		Deck d1 = new Deck();	
		d1.reset();
	
		for (int i = 0; i < 51; i++) {
		    d1.dealCard();
		}
		if (d1.empty())
		    System.out.println("ERROR. Deck shold not be empty.");
		else
		    System.out.println("OK. Deck is not empty.");
		d1.shuffle();
		d1.dealCard();  //not a problem
		if (d1.empty()) 
		    System.out.println("OK, deck is empty.");
		else
		    System.out.println("ERROR, deck should be empty.");	    
		d1.reset();
		d1.shuffle();
		d1.dealCard();
		System.out.println("Dealt last card, reset, shuffled, dealt from new deck.");
    }
    
    /** Run a few tests on Deck type. */
    public static void main(String[] args) {
		Deck.test1();
		Deck.test2();
    }
} // end of class Deck
