import java.util.ArrayList;
import java.util.Collections;

public class Word {
	private static final boolean debug = true;
	private static final double decayFactor = 1.5;
	protected String data;
	protected ArrayList<ArrayList<Connection>> sentenceConnections;
	protected ArrayList<Integer> tieredReferenceTotals;
	protected ArrayList<Connection> connections;
	private boolean sorted;
	private int totalReferences; // keeps track of total connections and connection ranks, for weighted getting
	

	public Word(String s) {
		sentenceConnections = new ArrayList<ArrayList<Connection>>();
		tieredReferenceTotals = new ArrayList<Integer>();
		connections = new ArrayList<Connection>();
		data = s;
		sorted = false;
		totalReferences = 0;
	}
	
	public void addSentenceConnection(int posIdx, Word w) {
		if (sentenceConnections.size() > posIdx) {
			addConnection(sentenceConnections.get(posIdx), w);
			tieredReferenceTotals.add(posIdx, tieredReferenceTotals.remove(posIdx) + 1);
			
		}
		else {
			sentenceConnections.add(new ArrayList<Connection>());
			tieredReferenceTotals.add(1);
			if (sentenceConnections.size() <= posIdx) throw new ArrayIndexOutOfBoundsException("Somehow skipped a tier");
			addConnection(sentenceConnections.get(posIdx), w);
			
		}
	}
	
	private void addConnection(ArrayList<Connection> cs, Word w) {
		// more general addConnection for handling tiered connections
		for (Connection c : cs) {
			if (c.word.equals(w)) {
				c.rankUp();
				sorted = false;
				return;
			}
		}
		cs.add(new Connection(w));
		sorted = false;
	}
	
	public void addConnection(Word w) {
		for (Connection c : connections) {
			if (c.word.equals(w)) {
				c.rankUp();
				totalReferences++;
				sorted = false;
				return;
			}
		}
		connections.add(new Connection(w));
		totalReferences++;
		sorted = false;
	}
	
	public void addConnection(String s) {
		addConnection(new Word(s));
	}
	
	// there are two options for making sure the list of connections is maintained in order of frequency
	// 1. use some EventList-esque insert method and remove the connection each time
	// 2. trigger a resort at the end of the word import phase (going with this for now);
	
	public void sort() {
		// to be called after all words have been added
		if (!sorted) {
			Collections.sort(connections);
			connections.trimToSize();
			for (ArrayList<Connection> a : sentenceConnections) {
				Collections.sort(a);
				a.trimToSize();
			}
			sorted = true;
		}
	}
	
	public Word getWord() {
		// just returns the most common word
		if (sorted) return connections.get(connections.size() - 1).word;
		return null;
	}
	
	public Word getWord(Word[] prevs, boolean weighted) throws NoNextWordException {
		// returns a word based on the weighted arrays of the previous words' tier lists, as well as the current one
		ArrayList<Connection> temp = new ArrayList<Connection>();
		int totalRank = 0;
		for (Connection c : sentenceConnections.get(0)) {
			temp.add(new Connection(c.rank, c.word));
			totalRank += c.rank;
		}
		int primaryRank = totalRank; // only used in debug mode
		// this algorithm works equally well when applied to the whole prevs array, as the most recent element will always be this
		// however I like this better and I think it's more clear in some ways
		for (int i = 1; i < prevs.length; i++) {
			if (prevs[Math.abs(i - (prevs.length - 1))].hasWord(i)){
				for (Connection c : prevs[Math.abs(i - (prevs.length - 1))].sentenceConnections.get(i)) {
					boolean found = false;
					for (Connection t : temp) {
						if (t.word == c.word) {
							t.rank += (t.rank / Math.pow(decayFactor, i));
							totalRank += (t.rank / Math.pow(decayFactor, i));
							found = true;
							break;
						}
					}
					if (!found && c.rank / (int) Math.pow(decayFactor, i) > 0) {
						temp.add(new Connection(c.rank / (int) Math.pow(decayFactor, i), c.word));
						totalRank += c.rank / (int) Math.pow(decayFactor, i);
					}
				}
			}
		}
		Collections.sort(temp);
		if (!weighted) return temp.get(0).word;
		int rankValue = (int) (totalRank * Math.random());
		for (Connection t : temp) {
			if (t.rank >= rankValue) {
				if (debug) {
					System.out.println("Rank entries from simple chain (only considering '" + this.data + "'): " + primaryRank);
					System.out.println("Rank entries from all others :" + (totalRank - primaryRank));
					System.out.println("Rank entries corresponding to generated word: " + t.rank);
					System.out.println("Probability of this word being selected: " + ((double) t.rank / totalRank));
					System.out.println("Word: " + t.word);
					System.out.print("\n");
				}
				return t.word;
			}
			rankValue -= t.rank;
		}
		throw new NoNextWordException();
	}
	
	public Word getWord(boolean weighted) {
		// if !weighted, just returns the most common following word
		// if weighted, returns based on a random number
		if (sorted) {
			if (weighted) {
				int refIndex = (int) Math.random() * totalReferences;
				for (Connection c : connections) {
					if (refIndex <= c.rank) return c.word;
					refIndex -= c.rank;
				}
				// should not reach this point
				return new Word("Something is wrong with reference tracking.");
			}
			return connections.get(0).word;
		}
		return new Word("You didn't sort the connections!");
	}
	
	public boolean hasWord() {
		//checks if the default connection list has available words
		return connections.size() > 0;
	}
	
	public boolean hasWord(int tier) {
		//checks if the specified connection tier list has available words
		return (sentenceConnections.size() > 0 && sentenceConnections.get(tier).size() > 0);
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
