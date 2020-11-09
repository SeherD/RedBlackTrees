package cpsc331.assignment2;

import cpsc331.assignment2.Colour;
import java.util.NoSuchElementException;
import cpsc331.collections.ElementFoundException;
import cpsc331.assignment2.RBTNode;
import cpsc331.assignment2.RBT;
import cpsc331.assignment2.RBTMap;
import cpsc331.assignment2.RBTUtilities;
import cpsc331.assignment2.RSeq;

public class RBTMap_Test {

  public static void main(String[] args) {
  
    System.out.println("");
    System.out.print("Initializing a new red-black tree ");
    System.out.println("using integers as keys and values.");
    System.out.println("");
    
    RBTMap<Integer, Integer> M = new RBTMap<Integer, Integer>();
    RBTMapUtilities<Integer, Integer> utilities 
                          = new RBTMapUtilities<Integer, Integer>();
  
    RSeq kSeq = new RSeq(100);
    RSeq vSeq = new RSeq(1000);
    boolean[] defined = new boolean[100];
    Integer[] contents = new Integer[100];
    
    int i = 0;
    while (i < 100) {
      defined[i] = false;
      contents[i] = null;
      i = i + 1;
    };
    int size = 0;
    
    System.out.println("");
    checkContents(M, defined, contents, size);
    System.out.println("");
    System.out.println("");
    
    i = 0;
    while (i < 100) {
      int k = kSeq.next();
      int v = vSeq.next();
      if (!defined[k]) {
        size = size + 1;
      };
      defined[k] = true;
      contents[k] = v;
      utilities.testSet(M, k, v, size);
      i = i + 1;
    };  
    System.out.println("");
    checkContents(M, defined, contents, size);
    System.out.println("");
    System.out.println("");
    
    
    i = 0;
    while (i < 50) {
      int k = kSeq.next();
      if (defined[k]) {
        size = size - 1;
        utilities.testDelete(M, k, true, size);
        defined[k] = false;
        contents[k] = null;
      } else {
        utilities.testDelete(M, k, false, size);
      };
      i = i + 1;
    };
    System.out.println("");
    checkContents(M, defined, contents, size);
    System.out.println("");
    System.out.println("");
    
    i = 0;
    while (i < 50) {
      int k = kSeq.next();
      int v = vSeq.next();
      if (!defined[k]) {
        size = size + 1;
        defined[k] = true;
      };
      contents[k] = v;
      utilities.testSet(M, k, v, size);
      i = i + 1;
    };  
    System.out.println("");
    checkContents(M, defined, contents, size);
    System.out.println("");
    System.out.println("");
    
    i = 0;
    while (i < 50) {
      int k = kSeq.next();
      if (defined[k]) {
        size = size - 1;
        utilities.testDelete(M, k, true, size);
        defined[k] = false;
      } else {
        utilities.testDelete(M, k, false, size);
      };
      i = i + 1;
    };
    System.out.println("");
    checkContents(M, defined, contents, size);
    System.out.println("");
    System.out.println("");
    
    if (!defined[10]) {
      defined[10] = true;
      size = size + 1; 
    };
    contents[10] = null;
    utilities.testSet(M, 10, null, size);
    System.out.println("");
    checkContents(M, defined, contents, size);
    System.out.println("");
    System.out.println("");
    
  }
  
  // Checks whether the contents of a given RBTMap are as expected
  
  private static void checkContents(RBTMap<Integer, Integer> M, 
                                    boolean[] defined, Integer[] contents,
                                    int size) {
                                    
    RBTMapUtilities<Integer, Integer> utilities
                              = new RBTMapUtilities<Integer, Integer>();
    boolean consistent = true;
    int i = 0;
    while (i < 100) {
     if (consistent) {
      if (M.defined(i)) {
        Integer value = M.get(i);
        if (defined[i]) {
          if (value != null) {
            if (!value.equals(contents[i])) {
              System.out.print("Value for " + i + "is " + value.toString());
              System.out.print(" instead of ");
              if (contents[i] != null) {
                System.out.println(contents[i].toString() + ".");
              } else {
                System.out.println("null.");
              };
              consistent = false;
            }
          } else {
            if (contents[i] != null) {
              System.out.print("Value for " + i + "is null instead of ");
              System.out.println(contents[i].toString() + ".");
              consistent = false;
            };
          }
        } else {
          if (value != null) {
            System.out.print("Value for " + i + " is " + value.toString());
            System.out.println(" when it should be undefined.");
            consistent = false;
          } else {
            System.out.print("Value for " + i + " is null ");
            System.out.println("when it should be undefined.");
            consistent = false;
          }
        }
      } else {
        if (defined[i]) {
          System.out.print("Value for " + i + " is undefined when it should be ");
          if (contents[i] != null) {
            System.out.println(contents[i].toString() + ".");
          } else {
            System.out.println("null.");
          };
          consistent = false;
        }
      }
     } else {
       if (M.defined(i)) {
         Integer value = M.get(i);
         if (defined[i]) {
           if (value != null) {
             if (value.equals(contents[i])) {
               System.out.print("Value for " + i + " is " + value.toString());
               System.out.print(" instead of ");
               if (contents[i] != null) {
                 System.out.println(contents[i].toString() + ".");
               } else {
                 System.out.println("null.");
               }
             }
           } else {
             if (contents[i] != null) {
               System.out.print("Value for " + i + " is null instead of ");
               System.out.println(contents[i].toString() + ".");
             }
           }
         } else {
           if (value != null) {
             System.out.print("Value for " + i + " is " + value.toString());
             System.out.println(" when it should be undefined.");
           } else {
             System.out.print("Value for " + i + " is null ");
             System.out.println("when it should be undefined.");
           }
         }
       } else {
         if (defined[i]) {
           System.out.print("Value for " + i + " is undefined when it should be ");
           if (contents[i] != null) {
             System.out.println(contents[i].toString() + ".");
           } else {
             System.out.println("null.");
           }
         }
       }
     };
     i = i + 1;
    };
    
    RBT<Integer, Integer> T = M.thisTree();
    int foundSize = T.size();
    if (size != foundSize) {
      System.out.print("Size of mapping is " + foundSize + " instead of ");
      System.out.println(size + ".");
      consistent = false;
    };
    
    RBTUtilities<Integer, Integer> treeUtilities
                           = new RBTUtilities<Integer, Integer>();
    if (!treeUtilities.isRBT(T, false)) {
      System.out.println("Red-black tree representing mapping is invalid.");
      treeUtilities.isRBT(T, true);
      consistent = false;
    };
    
    if (consistent) {
      System.out.println("Mapping is as expected.");
    }
  
  }
  
}