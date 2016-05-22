
/**
   TableEntry implements an entry for the HashTable class.
   This class is package access as are its data members.
*/

class TableEntry {
    Hashable  key;
    Object item;


  TableEntry( Hashable newKey, Object newItem ) {
    key = newKey;
    item = newItem;
  }

}
