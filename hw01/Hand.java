/*
  Represent one hand of cards in BlackJack.
  @author 
*/

public class Hand {
    private Card[] cards;
    private int count;
    public static final int DEFAULT_SIZE = 10;

    public Hand() {
	this.cards = new Card[DEFAULT_SIZE];
	this.count = 0;
    }

    /**
       Add card to deck.
       @param c card to add
    */
    public void add(Card c) {
	if (isFull()) {
	    resize(2 * count);
	}
	cards[count] = c;
	count++;
    }

    /** True iff hand is full. */
    public boolean isFull() {
	return count == cards.length;
    }

    public void resize(int newsize) {
	if (newsize <= count) return;
	Card[] newhand = new Card[newsize];
	for (int i = 0; i < count; i++) {
	    newhand[i] = this.cards[i];
	}
	this.cards = newhand;
    }
    /**
       Determine the best way to evaluate this hand.
       @return  maximum value of this hand that is <= 21, assuming aces are 1 or 10.
    */
    public int handValue() {
    	int aceMaxVal = 11;
    	int aceMinVal = 1;
    	boolean haveAces = false;
    	int total = 0;
    	for(int i = 0; i < count; i++) {
    		if (cards[i].value() > Card.MINVAL && cards[i].value() < Card.MAXVAL - 2) {
    			total += cards[i].value();
    		} else if (cards[i].value() > Card.MAXVAL - 2) {
    			total += 10;
    		} else {
    			if (haveAces) {
    				total += aceMinVal;
    			} else {
    				haveAces = true;
    			}
    		}
    	}
    	if (haveAces) {
    		if (total + aceMaxVal <= Blackjack.BUSTVAL) {
    			total += aceMaxVal;
    		} else {
    			total += aceMinVal;
    		}
    	}
    	
    	return total;
    }



}