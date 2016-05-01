
/**
   Implements a simulation of many games of 21.  Objective is to find 
   best holding point for a player, assuming that the dealer always 
   holds at 17.

   @author Hayden Sartoris
   
   	Hold	Dealer	Player	Player
	Value	Won		Won		Winnings
	-----------------------------------------
	10	5866	3576	-1470.0
	11	5733	3636	-859.0
	12	5518	3799	552.0
	13	5410	3854	1143.0
	14	5120	4032	2869.0
	15	4971	4104	3601.0
	16	4841	4098	4514.0
	17	4621	4271	5890.0
	18	4848	4166	5751.0
	19	5330	3808	4721.0
	20	6094	3233	2768.0
	21	7601	2064	-754.0
	
	The optimal hold value is around 17; this allows the player to achieve reasonably high winrates
	as well as getting 21 frequently, the bonus from which is the primary reason for the player
	making any profit whatsoever.
	
	Notably, my code produces slightly higher values overall than other students' that I've seen,
	despite (seemingly) functionally identical logic. I can't explain this, and I've checked
	extremely thoroughly, probably not thoroughly enough though.
*/

public class Blackjack {
    public static final int BUSTVAL = 21;
    public static final int PLAYER = 1;
    public static final int DEALER = 2;
    public static final int TIE = 0;
    public static int PLAYER_HOLD = 10; 
    public static final int DEALER_HOLD = 17;
    
    static int maxes = 0;


    public static void main(String[] args) {
	final int MAX_GAMES = 10000;  // number of games to play
	Deck deck = new Deck();

	double winnings = 0; // player's winnings
	int pwin = 0; // number of time player wins
	int dwin = 0; // number of times dealer wins
	double bet = 2.0; // size of player's bet

	System.out.println("Hold\tDealer\tPlayer\tPlayer");
	System.out.println("Value\tWon\tWon\tWinnings");
	System.out.println("-----------------------------------------");
	// Loop over different player hold values.
	for (int phold = PLAYER_HOLD; phold <= BUSTVAL; phold++) {
	    pwin = dwin = 0;
	    winnings = 0;

	    // play many games
	    for (int i = 0; i < MAX_GAMES; i++) {

			double value = playGame(deck,bet,phold);
			winnings += value; // accumulate winnings of player
			if (value > 0) {
			    pwin++;
			} else if (value < 0) {
			    dwin++;
			}

	    } // many games
	    
	    System.out.println(phold + "\t" + dwin + "\t" + pwin + "\t" + winnings);
	    //System.out.println(maxes);
	    maxes = 0;
	}
    }

    /**
       Play one game of blackjack with the given deck.
       The bet is taken from the player prior to play.
       @param deck the deck of cards
       @param bet the bet to place
       @param card value player holds at
       @return the winnings/loss of the player.
    */
    public static double playGame(Deck deck, double bet,int playerhold) {
	// TODO
    	Hand player = new Hand();
    	Hand dealer = new Hand();
    	
    	player.add(getCard(deck));
    	dealer.add(getCard(deck));
    	player.add(getCard(deck));
    	dealer.add(getCard(deck));
    	
    	/* Disregard this; misread directions
    	while (player.handValue() < playerhold || dealer.handValue() < dealerHold) {
    		// this should add cards to each hand up until the point at which they hold or bust
    		if (player.handValue() < playerhold) {
    			player.add(getCard(deck));
    			if (player.handValue() > BUSTVAL) {
    				//System.out.println("player busted");
    				return bet * -1;
    			}
    		}
    		if (dealer.handValue() < dealerHold) {
    			dealer.add(getCard(deck));
    			if (dealer.handValue() > BUSTVAL) {
    				if (player.handValue() != BUSTVAL) {
    					//System.out.println("dealer busted");
    					return bet;
    				} else {
    					return bet + 5;
    				}
    			}
    		}
    	}
    	*/
    	while (player.handValue() < playerhold) {
    		player.add(getCard(deck));
    		if (player.handValue() > BUSTVAL) {
    			return -bet;
    		}
    	}
    	while (dealer.handValue() < DEALER_HOLD) {
    		dealer.add(getCard(deck));
    		if (dealer.handValue() > BUSTVAL) {
    			if (player.handValue() == BUSTVAL) {
    				maxes++;
    				return bet + 5; //removed + 5
    			} else {
    				return bet;
    			}
    		}
    	}
    	//both hands should now be either holding or over (and returned)
    	//System.out.println("got here");
    	if (player.handValue() == dealer.handValue()) {
    		return 0;
    	} else if (player.handValue() > dealer.handValue()) {
    		if (player.handValue() != BUSTVAL) {
				return bet;
			} else {
				maxes++;
				return bet + 5; // removed + 5
			}
    	} else {
    		return -bet;
    	}
    }


    /**
       Get next card in the deck.  Get new shuffled deck if necessary.

       @param deck the deck of cards
       @returns the next card.
    */
    public static Card getCard(Deck deck) {
	if (deck.empty())  {
	    deck.reset();
	}
	Card c = deck.dealCard();
	return c;
    }



}
