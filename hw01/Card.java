/**
   This class represents a single card.  It has 4 suits and 13 values.
   @author S. Anderson
   @version written Aug 2011, modified Feb 2014.
*/

public class Card {

    /** suits */
    public static final int SPADES = 0;
    public static final int HEARTS = 1;
    public static final int CLUBS = 2;
    public static final int DIAMONDS = 3;     

    /** number of different suits */
    public static final int NSUITS = 4; 

    public static final int MINVAL = 1;
    public static final int MAXVAL = 13; 
    /** number of different values */
    public static final int NVALUES = MAXVAL - MINVAL + 1;
    public static final int ACE = MINVAL;

    // instance variables
    private int suit ; // the suit of the card (0 to 3)
    private int value ; // the value of the card (MINVAL to MAXVAL)
    
    /**
     * Creates a default instance of a Card, an Ace of Spaces.
     */
    public Card()
    {
	this(ACE,SPADES);
    }

    /**
       Creates a card with specified suit and value.
       @param thesuit suit of the card (use the constants provided!!)
       @param thevalue raw value of the card (1 to 13 for ace to king)
       @throws exception if values are invalid.
    */

    public Card(int thesuit, int thevalue) throws RuntimeException {
	if (thesuit < 0 || thesuit > 4 ||
	    thevalue < MINVAL || thevalue > MAXVAL) {
	    throw new RuntimeException("Bad card value: " + thesuit + " " + thevalue);
	}
	this.suit = thesuit;
	this.value = thevalue;
    }


    /**
       Get the suit.
       @return the suit 
   */
    public int suit() { return suit; }    
    /**
       Get the value.
       @return the value
    */
    public int value() { return value; }

    /**
     * returns the suit attribute of the card as a string
     * @return the suit attribute of the card as a string
     */
    public String getSuitString()
    {
	switch (suit)
	    {
	    case SPADES :
		return "spades" ;
	    case HEARTS :
		return "hearts" ;
	    case CLUBS :
		return "clubs" ;
	    case DIAMONDS :
		return "diamonds" ;
	    default :
		return "UNKNOWN" ;
	    }
    }

    /**
     * returns the value attribute of the card as a string
     * @return the value attribute of the card as a string
     */
    public String getValueString()
    {
	final String[] names = 
	    {"ace","two","three","four","five","six","seven",
	     "eight","nine","ten","jack","queen","king"};
	return names[value - MINVAL];
    }

    public String toString() {
	return "(" + getValueString() + ", " + getSuitString() + ")";
    }

}
