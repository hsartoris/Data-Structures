/**

   This is a test driver for the Address and Person classes.
   It determines if values are as expected.
   
   You should enhance it to test the methods/constructors that you
   write.

   @author Your Name

 */


public class TestCases {
  
    // Constructor
    public TestCases() {
	System.out.println("New TestCases object created.  Time to test!");
    }

    /**
       @param o1 integer to test
       @param o2 integer to test
       Print OK iff two integers are equal.
    */
    public void assertEquals(int o1, int o2) {
	if (o1 == o2) 
	    System.out.println("OK " + o1 + " equals " + o2);
	else 
	    System.out.println("ERROR " + o1 + " and " + o2 + " differ.");
    }
  
    /**
       @param o1 obj to test
       @param o2 obj to test
       Print OK iff two objects are equal.
    */
    public void assertEquals(Object o1,Object o2) {
	if (o1.equals(o2)) 
	    System.out.println("OK " + o1 + " equals " + o2);
	else 
	    System.out.println("ERROR " + o1 + " and " + o2 + " differ.");
    }


    /**
       Print message dependon on value of test which should
       be true.
    */
    public void assertTrue(String message, boolean test) {
	if (test) 
	    System.out.println("OK " + message);
	else 
	    System.out.println("ERROR  " + message);
    }



  public void testDefaultConstructor() {
    Address addr = new Address();
    //System.out.println("" + addr.getCity());
    assertEquals( Address.UNKNOWN,addr.getCity());
    assertEquals(Address.MINZIP,addr.getZipcode());
  }
  
  public void testConstructor() {
    Address addr = new Address("4848 Chinook","Boise","Idaho",83705);
    assertEquals("4848 Chinook",addr.getStreet());
    assertEquals("Boise" , addr.getCity());
    assertEquals( "Idaho" , addr.getState());
    assertEquals(83705,addr.getZipcode());
    
    // Verify correct exception is raised for bad zipcode.
    try {
      
      addr = new Address("xxx","city","state",-1);
    } catch (IllegalArgumentException ex) {
      // It should get here, so just return gracefully.
      return;
    } catch (Exception exother) {
	System.out.println("Incorrect exception raised: " + exother);
    }
    System.out.println("Should have raised the Illegal Arg exception.");
      
  }
  
  public void testPerson() {
      Person p1 = new Person("Hayden", "Sartoris", 20, Person.MALE);
      p1.setAddress("14 Bowdoin", "Brunswick", "Maine", "04011");
      Person p2 = p1.copy();
      assertEquals(p1.getAddress().getStreet(), p2.getAddress().getStreet());
      assertEquals(p1.getAddress(), p2.getAddress());
  }

    public static void main(String[] args) {
	TestCases test = new TestCases();

	test.testDefaultConstructor();
	test.testConstructor();
	test.testPerson();
    
    }	
}

