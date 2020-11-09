package cpsc331.assignment2;

import cpsc331.assignment2.Colour;
import java.util.NoSuchElementException;
import cpsc331.collections.ElementFoundException;
import cpsc331.assignment2.RBTNode;
import cpsc331.assignment2.RBT;
import cpsc331.assignment2.RBTUtilities;

public class RBT_Test2 {

  public static void main(String[] args) {
  
    System.out.println("");
    System.out.print("Initializing a new red-black tree ");
    System.out.println("using integers as keys and values.");
    System.out.println("");
    
    RBT<Integer, Integer> T = new RBT<Integer, Integer>();
    RBTUtilities<Integer, Integer> utilities = new RBTUtilities<Integer, Integer>();

    System.out.println("Insertions in descending order.");
    System.out.println("");
    
    int i = 0;
    while (i <= 50) {
      utilities.testInsert(T, 50 - i, i, true);
      i = i + 1;
    };

    utilities.testSearch(T, -1, null, false);
    i = 0;
    while (i <= 50) {
      utilities.testSearch(T, i, 50-i, true);
      i = i + 1;
    };
    utilities.testSearch(T, 51, null, false);
    
    utilities.testChange(T, -1, null, false);
    i=0;
    while (i <= 50) {
      utilities.testChange(T, i, i, true);
      i = i + 1;
    };
    utilities.testChange(T, 51, null, false);
    
    i = 26;
    while (i <= 50) {
      utilities.testDelete(T, i, true);
      i = i + 1;
    };
    
    i = 0;
    while (i <= 25) {
      utilities.testSearch(T, i, i, true);
      i = i + 1;
    };
    while (i <= 50) {
      utilities.testSearch(T, i, null, false);
      i = i + 1;
    };
    
  }
  
}