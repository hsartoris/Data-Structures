/**
   A hashable key is one that can be hashed.
 */

public interface Hashable {
    
	static final int HASH_RANGE = 1000;
    /**
       the hashing function
    */
    int hashFunction();
    
    /**
       @return  true iff this matches key
    */
    boolean equals(Object key);
    
}
