import java.util.ArrayList;
import java.util.Collections;

public class Word {
	protected String data;
	protected ArrayList<Connection> connections;
	private boolean sorted;
	

	public Word(String s) {
		connections = new ArrayList<Connection>();
		data = s;
		sorted = false;
	}
	
	
	
	public void addConnection(Word w) {
		for (Connection c : connections) {
			if (c.word.equals(w)) {
				c.rankUp();
				sorted = false;
				return;
			}
		}
		connections.add(new Connection(w));
		sorted = false;
	}
	
	public void addConnection(String s) {
		for (Connection c : connections) {
			if (c.word.equals(s)) {
				c.rankUp();
				return;
			}
		}
		connections.add(new Connection(new Word(s)));
		sorted = false;
	}
	
	// there are two options for making sure the list of connections is maintained in order of frequency
	// 1. use some EventList-esque insert method and remove the connection each time
	// 2. trigger a resort at the end of the word import phase (going with this for now);
	
	public void sort() {
		// to be called after all words have been added
		if (!sorted) {
			Collections.sort(connections);
			sorted = true;
		}
	}
	
	public Word getWord() {
		// just returns the most common word
		if (sorted) return connections.get(connections.size() - 1).word;
		return null;
	}
	
	public boolean hasWord() {
		return connections.size() > 0;
	}
	
	public Word getWord(int range, boolean weighted) {
		weighted = false; //change this after figuring out weighted probability efficiently
		// once again two ways
		// 1. can return with equal probability from the end of the list to the len-range
		// 2. can return with weighted probability within the range
		if (sorted) {
			if (range > connections.size()) range = connections.size(); //no goin outta that list now
			if (weighted) {
				// lollll do this later
			} else {
				return connections.get((connections.size()-1) - (int)(Math.random()*range)).word;
			}
		}
		return null;
	}
	
	
	
	
	public boolean equals(String s) {
		if (data.equals(s)) return true;
		return false;
	}
	
	public boolean equals(Word w) {
		if (data.equals(w.word())) return true;
		return false;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Word) {
			Word w = (Word) o;
			return w.word().equals(this.data);
		}
		return false;
	}
	
	
	
	public String word() {
		return data;
	}
	
	public String toString() {
		return data;
	}
	
	public class Connection implements Comparable<Connection> {
		int rank;
		Word word;
		
		public Connection(Word w) {
			// creates a new Connection with the given word and a rank of 0
			rank = 0;
			word = w;
		}
		
		public Connection(int r, Word w) {
			// creates a new Connection with a give word and rank
			rank = r;
			word = w;
		}
		
		
		public void rankUp() {
			rank++;
		}

		@Override
		public int compareTo(Connection o) {
			if (o.rank < this.rank) return -1;
			if (o.rank > this.rank) return 1;
			return 0;
		}
		
		public boolean equals(Connection o) {
			if (o.rank == this.rank) return true;
			return false;
		}
	}
}
