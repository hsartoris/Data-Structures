/**
 * This class represents a single address in the U.S.
 * @author Hayden Sartoris
 * @version Last Rewritten: 05.Feb.2016
 */

public class Address {
    public static final String UNKNOWN = "unknown";
    public static final int MINZIP = 0;
    public static final int MAXZIP = Integer.MAX_VALUE;
    public static final int UNKNOWN_ZIP = MINZIP;
    
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private boolean intZip = true;
    
    public Address() {
        street = UNKNOWN;
        city = UNKNOWN;
        state = UNKNOWN;
        zipcode = Integer.toString(UNKNOWN_ZIP);
    }
    
    public Address(String str, String c, String sta, int zip) {
        //only to be used with zips not starting with 0
        street = str;
        city = c;
        state = sta;
        if (zip >= MINZIP && zip <= MAXZIP) {
            zipcode = Integer.toString(zip);
        } else {
            throw new IllegalArgumentException("Bad zipcode");
        }
    }
    
    public Address(String str, String c, String sta, String zip) {
        intZip = false; //mostly unnecessary
        street = str;
        city = c;
        state = sta;
        if (Integer.valueOf(zip) >= MINZIP && Integer.valueOf(zip) <= MAXZIP) {
            zipcode = zip;
        } else {
            throw new IllegalArgumentException("Bad zipcode");
        }
    }
    
    public String toString() {
        return "Street:\t" + street + "\nCity:\t" + city + "\nState:\t" + state + "\nZip:\t" + zipcode + "\n";
    }
    
    public String getStreet() {
        return street;
    }
    
    public String getState() {
        return state;
    }
    
    public String getCity() {
        return city;
    }
    
    public int getZipcode() {
        return Integer.parseInt(zipcode);
        //because most of the time we apparently want an Int but I don't feel like having multiple variables
    }
    
    public String getStringZipcode() {
        //for when we want a string I guess
        //again this could be done better if I could remember how to write a method returning multiple object types
        return zipcode;
    }
    
    public boolean intZip() {
        //for the copy method on person, tell if it's an int or not
        return intZip;
    }
    
    public static void main(String[] args) {
        System.out.println("yayyyy");
    }
}