
/**
 * Represents simple facts about a person
 * @author Roger Garside
 * @version Last Rewritten: 17th March 1997 (modified 30.Jan.2014 by SEA)
 */

public class Person
{
    // Person Class Constants

    /*
     * Constant - the upper limit for the age
     */
    private static final int UPPER_AGE = 150;

    /**
     * Constant - unknown gender
     */
    public static final int UNKNOWN = 99 ;

    /**
     * Constant - male gender
     */
    public static final int MALE = 1 ;

    /**
     * Constant - female gender
     */
    public static final int FEMALE = 2 ;


    // Person Instance Variables

    /*
     * the person's (single) forename
     */
    private String forename ;

    /*
     * the person's surname
     */
    private String surname ;

    /*
     * the person's age
     */
    private int age ;

    /*
     * the person's gender
     */
    private int gender ;

    /*
     * the person's address
     */
    private Address address;

    // Person Constructor Methods

    /**
     * Creates an instance of the Person class with default values
     * (forename = "NONE", surname = "NONE", age = 0, gender = UNKNOWN)
     */
    public Person()
    {
        forename = "NONE" ;
        surname = "NONE" ;
        age = 0 ;
        gender = UNKNOWN ;
        address = null;
	
    } // end of constructor method

    /** 
     * Creates an instance of the Person class with specified attribute
     * values
     * @param f forename of the person
     * @param s surname of the person
     * @param a age of the person
     * @param g gender of the person
     */
    public Person(String f, String s, int a, int g)
    {
        if (f.length() < 1)
            {
		System.err.println("bad forename argument in constructor") ;
		System.exit(1) ;
	    }
        forename = f ;
        if (s.length() < 1)
            {
		System.err.println("bad surname argument in constructor") ;
		System.exit(1) ;
	    }
        surname = s ;
        if ((a < 0) || (a > UPPER_AGE))
            {
		System.err.println("bad age argument in constructor") ;
		System.exit(1) ;
            }
        age = a ;
        if ((g != MALE) && (g != FEMALE) && (g != UNKNOWN))
            {
		System.err.println("bad gender argument in constructor") ;
		System.exit(1) ;
            }
        gender = g ;
        
        address = null;

    } // end of constructor method


    // Person Class Methods
 
    /**
     * returns the upper limit for a valid age
     * @return the upper limit for a valid age
     */
    public static int getUpperAgeLimit()
    {
        return UPPER_AGE ;
    } // end of method getUpperAgeLimit


    // Person Instance Methods - Selectors

    /**
     * returns the forename attribute of the person
     * @return the forename attribute of the person
     */
    public String getForename()
    {
        return forename ;
    } // end of method getForename

    /**
     * returns the surname attribute of the person
     * @return the surname attribute of the person
     */
    public String getSurname()
    {
        return surname ;
    } // end of method getSurname

    /**
     * returns the age attribute of the person
     * @return the age attribute of the person
     */
    public int getAge()
    {
        return age ;
    } // end of method getAge

    /**
     * returns the gender attribute of the person
     * @return the gender attribute of the person
     */
    public int getGender()
    {
        return gender ;
    } // end of method getGender

    /**
     * returns the gender attribute of the person as a String
     * @return the gender attribute of the person as a String
     */
    public String getGenderString()
    {
	String x = "unknown" ;
	switch (gender)
	    {
	    case MALE :
		x = "male" ;
		break ;
            case FEMALE :
		x = "female" ;
		break ;
            case UNKNOWN :
		x = "unknown" ;
		break ;
            }
        return x ;
    } // end of method getGenderString


    // Instance Methods - Mutators


    /**
     * set the forename attribute of the person
     * @param f the forename of the person
     */
    public void setForename(String f)
    {
        if (f.length() < 1)
            {
		System.err.println("bad forename argument in 'setForename'") ;
		System.exit(1) ;
            }
        forename = f ;
    } // end of method setForename

    /**
     * set the surname attribute of the person
     * @param s the surname of the person
     */
    public void setSurname(String s)
    {
        if (s.length() < 1)
            {
		System.err.println("bad surname argument in 'setSurname'") ;
		System.exit(1) ;
            }
        surname = s ;
    } // end of method setSurname

    /**
     * set the age attribute of the person
     * @param a the age of the person
     */
    public void setAge(int a)
    {
        if ((a < 0) || (a > UPPER_AGE))
            {
		System.err.println("bad age argument in 'setAge'") ;
		System.exit(1) ;
            }
        age = a ;
    } // end of method setAge

    /**
     * set the gender atribute of the person
     * @param g the gender of the person
     */
    public void setGender(int g)
    {
        if ((g != MALE) && (g != FEMALE) && (g != UNKNOWN))
            {
		System.err.println("bad gender argument in 'setGender'") ;
		System.exit(1) ;
            }
        gender = g ;
    } // end of method setGender
    

    // Other Person Methods

    /**
     * increase the age of the person by the specified amount
     * @param n the number of years by which the age attribute should
     *          be increased
     */
    public void increaseAge(int n)
    {
	if ((n < 1) || (age + n > UPPER_AGE))
	    {
		System.err.println("bad age in 'increaseAge'") ;
		System.exit(1) ;
	    }
        age += n ;
    } // end of method increaseAge

    /**
     * set the full name of the person in one operation
     * @param f the forename of the person
     * @param s the surname of the person
     */
    public void setFullName(String f, String s)
    {
        if ((f.length() < 1) || (s.length() < 1))
            {
		System.err.println("bad name arguments (s) in 'setFullName'") ;
		System.exit(1) ;
            }
        forename = f ;
        surname = s ;
    } // end of method setFullName

    /**
     * return the formal title of the person
     * @return the formal name of the person ('Mr' or 'Ms', initial, surname)
     */
    public String formalTitle()
    {
        String s;
   
        if (gender == MALE)
            s = "Mr " ;
        else if (gender == FEMALE)
            s = "Ms " ;
        else
            s = "" ;
        return s + forename.charAt(0) + ". " + surname ;
    } // end of method formalTitle



    // TODO
    // Add one getter and two setters for this.address.
    public Address getAddress() {
        //return address object
        return this.address;
    }
    
    public void setAddress(Address a) {
        //sets address object
        this.address = a;
    }
    
    public void setAddress(String street, String city, String state, int zip) {
        // for int zips
        this.address = new Address(street, city, state, zip);
    }

    public void setAddress(String street, String city, String state, String zip) {
        //there was a better way to do this, maybe having a single method with an Object class last argument?
        this.address = new Address(street, city, state, zip);
    }

    /**
     * return a string representing the person
     * @return details of the person ('forename surname (age: gender)')
     */
    public String toString()
    {
	String s =  forename + ' ' + surname + " (" + age + ": " +
	    getGenderString() + ')' + "\n";
        
    if (this.address != null) {
        s += this.address.toString();
    }

	return(s);
    } // end of method toString




    /**
     * return a copy of the person
     * @return a copy of the Person instance
     */
    public Person copy()
    {
	Person result = new Person() ;
	result.forename = forename;
	result.surname = surname;
	result.age = age ;
	result.gender = gender ;
    if (this.address.intZip()) { // this is super pointless, all of the zipcodes should just be strings but I didn't want to change the test cases that much
        result.setAddress(this.address.getStreet(), this.address.getCity(), this.address.getState(), this.address.getZipcode());
    } else {
        result.setAddress(this.address.getStreet(), this.address.getCity(), this.address.getState(), this.address.getStringZipcode());
    }
    
	return result ;
      
    } // end of method copy
    
    
} // end of class Person


