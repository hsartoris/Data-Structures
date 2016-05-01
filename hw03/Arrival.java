/**
 * Implement the abstract Event class.
 * Models arrival of an entity at a server.
 * 
 */
public class Arrival extends Event {
		private int duration; // duration of service
  
  public Arrival() {
  }
  
  /*
   * Create arrival at atime and duration 0.
   * @param atime time of arrival
   */
  public Arrival(int atime) {
    time = atime;
    duration = 0;
  }
  
  /*
   * Create arrival at atime and duration theDuration.
   * @param atime time of arrival
   * @paraam theDuration duration of service
   */
  public Arrival(int atime, int theDuration) {
    time = atime;
    duration = theDuration;
  }
  
  /**
   * @return arrival time
   */
  public int getArrTime() {
      return (int) time;
  }
  
  /**
   * @return arrival time
   */
  public double getTime() {
    return time;
  }

  /**
   * 
   * @return the duration of service for this event
   */
  public int getDuration() {
    return duration;
  }
  
  public String toString() {
    return("Arrival event at time " + time + " with duration " + duration);
  }
}
