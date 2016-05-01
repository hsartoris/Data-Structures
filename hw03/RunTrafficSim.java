import java.util.Random;
/**
 * Simulate a specified number of arrivals using
 * arrival and service distributions found in an external file.
 *
 * USAGE: java RunTrafficSim INTERARRIVAL_FILE INTERSERVICE_FILE NUM_TOLL_BOOTHS NUM_ARRIVALS
 * @author S. Anderson
 */

public class RunTrafficSim {

    public static void main(String[] args) {
	String errmsg = "USAGE: java RunTrafficSim INTERARRIVAL_FILE INTERSERVICE_FILE NUM_TOLL_BOOTHS NUM_ARRIVALS";
	if (args.length < 4) {
	    System.err.println(errmsg);
	    System.exit(-1);
	}
	int ntolls = Integer.parseInt(args[2]);
	int num_arrivals = Integer.parseInt(args[3]);

	// TODO remove the following to enable more tollbooths.
	//if (ntolls != 1) {
	//    System.out.println("Only one toll currently supported.");
	//    System.exit(-1);
	//}

	
	TrafficSim theSim = new TrafficSim(ntolls);
	CustomDistribution arrivaldist = new CustomDistribution(args[0]); // arrival interarrival times
	CustomDistribution servicedist = new CustomDistribution(args[1]); // service times
    
	Arrival[] inputData = new Arrival[num_arrivals];  // arrivals at bank and transation times
	int arrivaltime = 0;
	int servicetime;
	for (int i = 0; i < num_arrivals; i++) { // generate all arrival events
	    arrivaltime += arrivaldist.next();
	    servicetime = servicedist.next();
	    inputData[i] = new Arrival(arrivaltime,servicetime); // new car arrival and service time
	}
    
	theSim.simulate(inputData, num_arrivals);
    }
}
