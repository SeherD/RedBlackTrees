package cpsc331.assignment2;

import cpsc331.assignment2.Colour;
import java.util.NoSuchElementException;
import cpsc331.collections.ElementFoundException;
import cpsc331.assignment2.RBTNode;
import cpsc331.assignment2.RBT;
import cpsc331.assignment2.RBTUtilities;
import cpsc331.assignment2.RSeq;

public class RBT_Test3{

  public static void main(String[] args) {
  
    System.out.println("");
    System.out.print("Initializing a new red-black tree ");
    System.out.println("using integers as keys and values.");
    System.out.println("");
    
    RBT<Integer, Integer> T = new RBT<Integer, Integer>();
    RBTUtilities<Integer, Integer> utilities = new RBTUtilities<Integer, Integer>();
    
    RSeq vSeq = new RSeq(100);
    boolean[] contents = new boolean[100];
    
    int i = 0;
    while (i < 100) {
      contents[i] = false;
      i = i + 1;
    };
    System.out.println("");
    checkContents(T, contents);
    System.out.println("");
    System.out.println("");
    
    i = 0;
    while (i < 100) {
      int j = vSeq.next();
      if (contents[j]) {
        utilities.testInsert(T, j, j, false);
      } else {
        utilities.testInsert(T, j, j, true);
        contents[j] = true;
      };
      i = i + 1;
    };  
    System.out.println("");
    checkContents(T, contents);
    System.out.println("");
    System.out.println("");
    
    i = 0;
    while (i < 50) {
      int j = vSeq.next();
      if (contents[j]) {
        utilities.testDelete(T, j, true);
        contents[j] = false;
      } else {
        utilities.testDelete(T, j, false);
      };
      i = i + 1;
    };
    System.out.println("");
    checkContents(T, contents);
    System.out.println("");
    System.out.println("");

    i = 0;
    while (i < 50) {
      int j = vSeq.next();
      if (contents[j]) {
        utilities.testInsert(T, j, j, false);
      } else {
        utilities.testInsert(T, j, j, true);
        contents[j] = true;
      };
      i = i + 1;
    };  
    System.out.println("");
    checkContents(T, contents);
    System.out.println("");
    System.out.println("");
    
    i = 0;
    while (i < 50) {
      int j = vSeq.next();
      if (contents[j]) {
        utilities.testDelete(T, j, true);
        contents[j] = false;
      } else {
        utilities.testDelete(T, j, false);
      };
      i = i + 1;
    };
    System.out.println("");
    checkContents(T, contents);
    System.out.println("");
    System.out.println("");
  }
  
  // Checks whether the contents of a given red-black tree are as expected
  
  private static void checkContents(RBT<Integer, Integer> T, boolean[] contents) {
  
    RBTUtilities<Integer, Integer> utilities = new RBTUtilities<Integer, Integer>();
    boolean consistent = true;
    Integer i = 0;
    while (i < 100) {
      if (consistent) {
        if (contents[i]) {
          if (!contains(T, i)) {
            System.out.print("Contents of the red-black tree are not as expected: ");
            System.out.print(i.toString());
            System.out.println(" was expected, but not found.");
            consistent = false;
         }
        } else {
          if (contains(T, i)) {
            System.out.print("Contents of the red-black tree are not as expected: ");
            System.out.print(i.toString());
            System.out.println(" was not expected, but was found.");
            consistent = false;
          }
        }
      } else {
        if (contents[i]) {
          if (!contains(T, i)) {
            System.out.print("Contents of the red-black tree are not as expected: ");
            System.out.print(i.toString());
            System.out.println(" was expected, but not found.");
          }
        } else {
          if (contains(T, i)) {
            System.out.print("Contents of the red-black tree are not as expected: ");
            System.out.print(i.toString());
            System.out.println(" was not expected, but was found.");
          }
        }
      };
      i = i + 1;
    };
    if (consistent) {
      System.out.println("Contents of the red-black tree are as expected.");
    }
  
  }
  
  // Checks whether a given red-black tree represents a mapping for
  // which an input key is defined
  
  private static boolean contains (RBT<Integer, Integer> T, Integer key) {
  
    try {
      Integer value = T.get(key);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  
  }
  
}