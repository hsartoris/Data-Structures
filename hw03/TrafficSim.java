import java.util.ArrayList;

/**
 *  Simulate a system in which single entities arrive and are served
 *  by a single server.
 *  
 *  adapted from Carrano & Prichard, and Weiss
 */

public class TrafficSim {
    private ArrayList<QueueVector<Arrival>> tollqueue; // A bunch of queues. Customers in line, rep'd by Arrivals
    private EventList eventList; // events to be processed sorted by time
    private EventList arrivalList; // arrivals to the system sorted by time
    private double time; // system time
    private int maxwait = 0;

    /**
     * @param numtolls must be >= 1
     */
    public TrafficSim(int numtolls) {
	// TODO: remove next line when ready for more than one queue
	//numtolls = 1;

	if (numtolls <= 0) numtolls = 1;
	time = 0;
	// create one toll (one server) which is really just a single queue
	tollqueue = new ArrayList<QueueVector<Arrival>>(numtolls);
	for (int i = 0; i < numtolls; i++) {
		tollqueue.add(new QueueVector<Arrival>());
	}

	arrivalList = new EventList(); // sorted by time
	eventList = new EventList(); // sorted by time
    }
    /**
     * @param inputData an array of Arrivals, each with arrival time and duration
     * @param numDataPoints specifies how many Events are in the array 
     * @throws RuntimeException
     */
    public void simulate(Arrival[] inputData, int numDataPoints) throws RuntimeException {
        Event newEvent;
        int numDepartures = 0;
        double totalWait = 0;
        
        
        for (Arrival arr: inputData) arrivalList.insert(arr); // add all arrival events 
        eventList.insert(arrivalList.head()); // start simulation by adding arrival of first entity        
        //EventList.checkOrder(arrivalList);
        
        while(!eventList.isEmpty()) {
            newEvent = eventList.head();
            time = newEvent.getTime();
            //System.out.println(newEvent);    // tell user about event
            
            if (newEvent instanceof Arrival) {  
            	processArrival((Arrival)newEvent);
            }
            else if (newEvent instanceof Departure) {
                Departure newDep = (Departure)newEvent;
                processDeparture(newDep);
                numDepartures++;
                if (newDep.getWait() > maxwait) maxwait = newDep.getWait();  // update maximum waiting time
                totalWait += newDep.getWait();
            }
            else
                throw new RuntimeException("Have a generic Event but need Arrival or Departure: " + newEvent);  // shouldn't happen!
        } // end while
        
        System.out.println("Finish time (seconds/minutes/hours) " + time + "/" + time/60 + "/" + time/3600.0);
        if (numDepartures != 0) {   // at end, report average wait time 
            System.out.println("Average wait to be served is " + 
                               (totalWait/numDepartures));
            System.out.println("Maximum wait to be served is " + maxwait); 
        }
    } // end simulate
    
    /**
     * Handle a new arrival to the system.
     * @param arrEvent arrival event
     */
    public void processArrival(Arrival arrEvent) { //, int inputDataIndex) {

        int serverid = getShortestQueue();  // choose correct tollqueue for this arrival
        Queue<Arrival> server = tollqueue.get(serverid);
        boolean atFront = server.isEmpty();  // am I the only one here? (MUST be before enqueue!)
        server.enqueue(arrEvent);  // put new customer into bank line to wait
        
        if (atFront) {    // if no other customers, then immediately get served
            Departure newDep = new Departure(arrEvent.getArrTime(), arrEvent,serverid);
            // because this customer's next Event will be a departure
            eventList.insert(newDep); // put the departure into eventList
       
        } // end if
        
        // and them, regardless of whether this customer was served immediately or not,
        // get the next customer (next Arrival) from the inputData array
        if (!arrivalList.isEmpty()) eventList.insert(arrivalList.head());
    
    } // end processArrival
    
    /**
     * Handle a departure from the system.
     * @param depEvent
     */
    public void processDeparture(Departure depEvent) {

	// TODO
	/// 1. determine the exitqueue, the queue from which
	// depEvent is happening.
    	
    	int exitID = depEvent.getServer();
    	Queue<Arrival> tollQueue = tollqueue.get(exitID);
    	
	// 2. dequeue the car in the exitqueue
    	tollQueue.dequeue();
	// 3. if the exitqueue is not empty, generate a departure for
	// the car at the front of the exitqueue. and put the new
	// departure event into the eventList.
    	if (!tollQueue.isEmpty()) eventList.insert(new Departure((int) time, tollQueue.getFront(), exitID));
    		


    } // end processDeparture
    
    /**
     * Add arrival event to shortest tollqueue or first if all have 
     * same length. 
     * @param arrival
     */
    private int getShortestQueue() {
    	
    	int shortest = 0;
    	for (QueueVector<Arrival> q : tollqueue) {
    		if (q.size() < tollqueue.get(shortest).size()) {
    			shortest = tollqueue.indexOf(q);
    		}
    	}
    	return shortest;
    }
    
    
    public void printList(EventList theList) {  // for debugging only
        System.out.println(theList);
    }
} // end class



