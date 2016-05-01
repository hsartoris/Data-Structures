/**
   Class for events.  Events are ordered by their timestamp (time of
   occurrence).
   @author S. Anderson
 */

public class Event implements Comparable<Event> {

    public static final double TOLERANCE = 1.0e-3;
    protected double time; // timestamp for this event

    /**
       Create event with timestamp at 0.0.
    */
    public Event() {
	time = 0.0;
    }

    /**
       Create event with timestamp at t units in future.
    */
    public Event(double t) {
	    time = t;
    }


    /**
       @param o the object to be compared.
       @return a negative integer, zero, or a positive integer as this
       object is less than, equal to, or greater than the specified object.

       @throws ClassCastException - if the specified object's type
       prevents it from being compared to this object.
    */

    public int compareTo(Event o) {
	double t = o.getTime();
	if ( Math.abs(t - this.time) < TOLERANCE ) return 0;
	if ( this.time < t ) return -1;
	else return 1;
    }

    public double getTime() {
	return time;
    }

    public String toString() {
	return Double.toString(time);
    }

}
