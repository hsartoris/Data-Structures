/*
  Local class used to store Tweets.
  @author Hayden Sartoris
*/
import twitter4j.*;

import java.util.Date;
import java.io.*;

public class Tweet implements Serializable, Comparable<Tweet> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6571558880126032463L;
	// Tweet info
    protected long id;
    protected java.util.Date creationDate;
    protected String text;
    // User info
    protected long userId;    
    protected String userName;
    protected String screenName;
    protected String location;
    protected String placeName;
    protected String placeCountry;
    // Reply info
    protected String replyToScreenName;
    protected long replyToStatusId;
    protected boolean isRetweeted;
    protected int retweetCount;
    protected String source;
    protected String searchString;

    /**
       Convert twitter4j tweet information into more limited local content.
    */
    public Tweet (Status tweet) {
		id = tweet.getId();
		text = tweet.getText();
		creationDate = tweet.getCreatedAt();
		// user
		User user = tweet.getUser();
		userId = user.getId();
		userName = user.getName();
		screenName = user.getScreenName();
		// location
		location = user.getLocation();
		Place place = tweet.getPlace();
		if (place != null) {
		    placeName =  place.getName();
		    placeCountry = place.getCountry();
		}
		// reply
		replyToScreenName =  tweet.getInReplyToScreenName();
		replyToStatusId =  tweet.getInReplyToStatusId();
		// retweet
		isRetweeted = tweet.isRetweeted();
		retweetCount = tweet.getRetweetCount();
		source = tweet.getSource();
    }

    /*
      String used to find this tweet.
    */
    public void setSearchString(String s) {
    	searchString = s;
    }

    /**
       Basic output of tweet.
    */
    public String toString() {
    	return "User: " + userName + "\nText: " + text;
    }

    public String date() {
    	return "" + creationDate;
    }
    
    public Date getDate() {
    	return creationDate;
    }

    /** just print */
    public static void p(String s) {
    	System.out.println(s);
    }

    public void print() {
		p("TweetId: " + id);
		p("Text: " + text);
		p("SearchSting: " + searchString);
		p("UserID: " + userId);
		p("Date: " + creationDate);
		p("Name: " + userName);
		p("ScreenName: " + screenName);
		p("Location: " + location);
		p("PlaceName: " + placeName);
		p("PlaceCountry: " + placeCountry);
		p("IsRetweeted: " + isRetweeted);
		p("RetweetCount: " + retweetCount);
		p("Source: " + source);
    }

	@Override
	public int compareTo(Tweet t) {
		return creationDate.compareTo(t.getDate());
	}










}