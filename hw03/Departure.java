public class Departure extends Event {
    private int wait;     // time waiting for service
	private int server; // num of toll serving this car
    
    /**
     * Create a departure event for this arriving entity.
     * 
     * @param currentTime the current system time
     * @param arr the arrival event for this departure
     */
    public Departure(int currentTime, Arrival arr,int serverid) {
        time = currentTime + arr.getDuration(); // which will happen "duration" time units from now
        wait = currentTime - arr.getArrTime();     // and they've waited until now to start being served
        server = serverid;
    }
    
    /**
     * 
     * @return the departure time
     */
    public int getDepTime() {
        return (int) time;
    }
    
    /**
     * @return the departure time
     */
    public double getTime() {
        return time;
    }
    /**
     * 
     * @return the time spent waiting for service for this entity
     */
    public int getWait() {
        return wait;
    }

    /**
     * 
     * @return server id (position in ArrayList)
     */
    public int getServer() {
  	  return server;
    }

    /**
   * 
   * @param theserver id of server
   */
    public void setServer(int theserver) {
  	  server = theserver;
    }

    public String toString() {
        return("\t\t\t\t\tDeparture event at time " + time + " with wait " + wait);
    }
}
