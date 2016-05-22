/*
 * get tweets using twitter4j API.  Store in Binary Search Tree.
 * Adapted from SearchTweets example program.
 * @author Hayden Sartoris
 */

/*
 * Access time for the given supply of tweets is... really poor. Given that they're already sorted
 * and no optimization is done to the data put into the tree, the average depth is about half the
 * total number of nodes, meaning that any operations are, for the given data, O(n).
 */

import twitter4j.*;
import java.util.*;
import java.io.*;

public class Main {

    /**
       Return list of twitter4j objects resulting from query.
    */
    public static List<Status> getTweets(Query query) {

    	Twitter twitter = new TwitterFactory().getInstance();
    	QueryResult result = null;
        try {
        	result = twitter.search(query);
        	List<Status> tweets = result.getTweets();
        	return tweets;
        }
        catch (TwitterException te) {
        	te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            return null;
        }
    }

    // Just print
    public static void p(String s) {
	System.out.println(s);
    }
    
    public static void saveBST(String file, BinarySearchTree<Tweet> bst) {
    	try {
    		FileOutputStream fout = new FileOutputStream(file);
    		ObjectOutputStream o = new ObjectOutputStream(fout);
    		o.writeInt(bst.size);
    		saveBSTr(o, bst.root);
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    }
    
    private static void saveBSTr(ObjectOutputStream o, BTNode<Tweet> n) throws IOException {
    	if (n == null) return;
    	o.writeObject(n.element);
    	saveBSTr(o, n.left());
    	saveBSTr(o, n.right());
    }
    
    @SuppressWarnings("unchecked")
	public static BinarySearchTree<Tweet> loadBST(String file) {
    	Tweet[] tweets;
    	BinarySearchTree<Tweet> tree = new BinarySearchTree<Tweet>();
    	try {
		    FileInputStream fin = new FileInputStream(file);
		    ObjectInputStream ois = new ObjectInputStream(fin);
		    tweets = new Tweet[ois.readInt()];
		    for (int i = 0; i < tweets.length; i++) {
		    	tweets[i] = (Tweet) ois.readObject();
		    }
		    ois.close();
		    if (tweets.length > 0) {
		    	tree.root = loadSubTree(tweets, 0, tweets.length - 1);
		    	tree.size = tweets.length;
		    	
		    } else {
		    	return null;
		    }
		    
		} catch (FileNotFoundException ex) {
		    ex.printStackTrace();
		} catch (IOException ioex) {
		    ioex.printStackTrace();
		} catch (ClassNotFoundException classex) {
		    classex.printStackTrace();
		}
    	return tree;
    }
    
    private static BTNode<Tweet> loadSubTree(Tweet[] ts, int low, int high) {
    	// 4. nothing at all
    	if (low == high) return new BTNode<Tweet>(ts[low]);
    	int mid = low + 1;
    	try { while (ts[mid].compareTo(ts[low]) < 0) mid++; }
    	catch (ArrayIndexOutOfBoundsException e) {
    		//what, I'm lazy
    		//the index going out of bounds is part of the functionality
    	}
    	// first node (of some block) is always the root (of that same subtree); this handles different cases
    	// 1. nothing below the root
    	if (mid == low + 1) return new BTNode<Tweet>(ts[low], null, loadSubTree(ts, mid, high));
    	// 2. nothing above the root
    	if (mid > high) return new BTNode<Tweet>(ts[low], loadSubTree(ts, low + 1, high), null);
    	// 3. things above and below
    	return new BTNode<Tweet>(ts[low], loadSubTree(ts, low + 1, mid - 1), loadSubTree(ts, mid, high));
    	
    }

    /*
      Save serialized ArrayList to a file.
    */
    public static void save(String fname, ArrayList<Tweet> tw) {
	try {
	    FileOutputStream fout = new FileOutputStream(fname);
	    ObjectOutputStream oos = new ObjectOutputStream(fout);
	    oos.writeObject(tw);
	    oos.close();
	} catch (FileNotFoundException ex) {
	    ex.printStackTrace();
	} catch (IOException ioex) {
	    ioex.printStackTrace();
	}
    }

    
    /*
      Load a serialized ArrayList of Tweets from a file.
      Fails if file does not exist.
    */
    @SuppressWarnings("unchecked")
	public static ArrayList<Tweet>  load(String fname) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	
		try {
		    FileInputStream fin = new FileInputStream(fname);
		    ObjectInputStream ois = new ObjectInputStream(fin);
		    tweets = (ArrayList<Tweet>) (ois.readObject());
		    ois.close();
		} catch (FileNotFoundException ex) {
		    ex.printStackTrace();
		} catch (IOException ioex) {
		    ioex.printStackTrace();
		}
		catch (ClassNotFoundException classex) {
		    classex.printStackTrace();
		}
		return tweets;
    }


    /**
     * Usage: java Main
     *        OR java Main [query]
     *
     * @param args
     */
    public static void main(String[] args) {

    	ArrayList<Tweet>  tweets;
        if (args.length == 1) {
		    // You must check twitter4j documentation to
		    // find ways to modify the basic query.
		    String searchString = new String(args[0]);
		    Query query = new Query(args[0]);
		    query.setCount(100); // upper bound on number of queries returned
		    query.setResultType(Query.ResultType.recent);
		    //System.out.println("num " + query.getCount());
		
		    GeoLocation gl = new GeoLocation(42.01955,-73.9080); // near Bard
		    query.setGeoCode(gl,100.0,Query.MILES); // limit distance
	
		    List<Status> tweet_status  = Main.getTweets(query);
		    if (tweet_status == null) System.exit(-1);
	
		    tweets = new ArrayList<Tweet>();
		    // Convert tweets into ArrayList of Tweet objects.
		    for (Status stat : tweet_status) {
			Tweet t = new Tweet(stat);
			t.setSearchString(searchString);
			tweets.add(t);
		    }
		    Main.save("tweets.serialized",tweets);	    
        } else { // just get tweets from file
		    // Save and reload tweets just to test serialization
		    tweets = Main.load("tweets100.serialized");
        }
		// Print out the reloaded tweets just to see them
		Iterator<Tweet> iter = tweets.iterator();
		while (iter.hasNext()) {
		    p("===");
		    iter.next().print();
		}
	
		p("Number of tweets: " + tweets.size());
		
		BinarySearchTree<Tweet> tweetTree = new BinarySearchTree<Tweet>();
		BinarySearchTree<Tweet> tweetTree2 = new BinarySearchTree<Tweet>();
		for (Tweet t : tweets) { tweetTree.addElement(t); tweetTree2.addElement(t); }
		tweetTree.computeNodeHeights();
		p("Average depth: " + tweetTree.avgDepth());
		p("Trees the same: " + BinarySearchTree.test(tweetTree, tweetTree2));
		saveBST("bst.save", tweetTree);
		tweetTree2 = loadBST("bst.save");
		p("Trees the same: " + BinarySearchTree.test(tweetTree, tweetTree2));
    }

}
