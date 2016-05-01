/**
 *  
 */
public class RunTrafficSim2 {
  public static final int NUM_ARRIVALS = 5;
  
  public static void main(String[] args) {
    TrafficSim theSim = new TrafficSim(2);
    
    Arrival[] inputData = new Arrival[NUM_ARRIVALS];  // arrivals at bank and service times
    inputData[0] = new Arrival(3, 4);
    inputData[1] = new Arrival(4, 8);
    inputData[2] = new Arrival(7, 5);
    inputData[3] = new Arrival(12, 4);
    inputData[4] = new Arrival(14, 5);
    
    theSim.simulate(inputData, NUM_ARRIVALS);
  }
}
