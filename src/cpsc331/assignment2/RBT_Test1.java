package cpsc331.assignment2;

import cpsc331.assignment2.Colour;
import java.util.NoSuchElementException;
import cpsc331.collections.ElementFoundException;
import cpsc331.assignment2.RBTNode;
import cpsc331.assignment2.RBT;
import cpsc331.assignment2.RBTUtilities;

public class RBT_Test1 {

  public static void main(String[] args) {
  
    System.out.println("");
    System.out.print("Initializing a new red-black tree ");
    System.out.println("using integers as keys and values.");
    System.out.println("");
    
    RBT<Integer, Integer> T = new RBT<Integer, Integer>();
    RBTUtilities<Integer, Integer> utilities = new RBTUtilities<Integer, Integer>();
    
    System.out.println("Checking use of methods with null keys.");
    System.out.println("");
    
    System.out.print("Trying a search...");
    try {
      Integer v = T.get(null);
      System.out.println("Not OK: NullPointerException not thrown.");
    } catch (NullPointerException ex) {
      System.out.println("OK: NullPointerException was thrown.");
    };
    
    System.out.print("Trying an insertion...");
    try {
      T.insert(null, 0);
      System.out.println("Not OK: NullPointerException not thrown.");
    } catch (NullPointerException ex) {
      System.out.println("OK: NullPointerException was thrown.");
    } catch (ElementFoundException ex1) {
      System.out.println("Not OK: ElementFoundException was thrown.");
    };
    
    System.out.print("Trying an update...");
    try {
      T.change(null, 0);
      System.out.println("Not OK: NullPointerException not thrown.");
    } catch (NullPointerException ex) {
      System.out.println("OK: NullPointerException was thrown.");
    };
    
    System.out.print("Trying a deletion...");
    try {
      T.delete(null);
      System.out.println("Not OK: NullPointerException not thrown.");
    } catch (NullPointerException ex) {
      System.out.println("OK: NullPointerException was thrown.");
    };
    System.out.println("");
    
    System.out.print("Operations populate the tree, empty it, repopulate it,");
    System.out.println("and then empty it again.");
    System.out.println("");
   
    utilities.testInsert(T, 0, 0, true);
    utilities.testSearch(T, 0, 0, true);
    utilities.testDelete(T, 0, true);
    utilities.testSearch(T, 0, 0, false);
    utilities.testDelete(T, 0, false);
    utilities.testInsert(T, 0, 0, true);
    utilities.testDelete(T, 0, true);
    
    System.out.println("");
    
    System.out.println("Insertions in ascending order.");
    System.out.println("");
    
    int i = 0;
    while (i <= 25) {
      utilities.testInsert(T, i, 25-i, true);
      i = i + 1;
    };
    
    utilities.testSearch(T, -1, null, false);
    i = 0;
    while (i <= 25) {
      utilities.testSearch(T, i, 25-i, true);
      i = i + 1;
    };
    utilities.testSearch(T, 26, null, false);
    
    utilities.testChange(T, -1, null, false);
    i=0;
    while (i <= 25) {
      utilities.testChange(T, i, i, true);
      i = i + 1;
    };
    utilities.testChange(T, 26, null, false);
    
    utilities.testDelete(T, -1, false);
    i = 0;
    while (i <= 12) {
      utilities.testDelete(T, 2*i, true);
      i = i + 1;
    };
    utilities.testDelete(T, 26, false);
    
    utilities.testSearch(T, -1, null, false);
    i = 0;
    while (i <= 12) {
      utilities.testSearch(T, 2*i, null, false);
      utilities.testSearch(T, 2*i+1, 2*i+1, true);
      i = i + 1;
    };
    utilities.testSearch(T, 26, null, false);
    
    i = 0;
    while (i <= 12) {
      utilities.testSearch(T, 2*i, null, false);
      utilities.testSearch(T, 2*i+1, 2*i+1, true);
      i = i + 1;
    };
  
  }

}