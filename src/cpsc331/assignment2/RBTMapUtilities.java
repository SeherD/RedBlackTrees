package cpsc331.assignment2;

import cpsc331.assignment2.Colour;
import java.util.NoSuchElementException;
import cpsc331.collections.ElementFoundException;
import cpsc331.assignment2.RBTNode;
import cpsc331.assignment2.RBT;
import cpsc331.assignment2.RBTMap;
import cpsc331.assignment2.RBTUtilities;

public class RBTMapUtilities<K extends Comparable<K>, V> {

 /*
 *
 * Tests a search for a key.
 *
 */
 
 public void testSearch(RBTMap<K, V> M, K key, V value, boolean success) {
 
   if (key != null) {
 
     try {
   
       System.out.print("Searching for \"");
       System.out.println(key.toString() + "\".");
       V foundValue = M.get(key);
       if (success) {
         if (value != null) {
           if (foundValue != null) {
             if (value.equals(foundValue)) {
               System.out.print("Search was successful, returning \"");
               System.out.println(value.toString() + "\", as expected.");
             } else {
               System.out.print("Expected value was \"" + value.toString());
               System.out.print("\", but \"" + foundValue.toString());
               System.out.println("\" was returned, instead.");
             }
           } else {
             System.out.print("Expected value was \"" + value.toString());
             System.out.println("\", but null was returned, instead.");
           }
         } else {
           if (foundValue != null) {
             System.out.print("Expected value was null, but \"");
             System.out.println(foundValue.toString() + "\" was returned, instead.");
           }
         };
       } else {
         System.out.print("Search incorrectly failed to throw a ");
         System.out.println("NoSuchElementException.");
       };
   
     } catch (NoSuchElementException ex) {
   
       if (success) {
         System.out.println("Search incorrectly threw a NoSuchElementException.");
       } else {
         System.out.println("Search threw a NoSuchElementException, as expected.");
       };
   
     };
  
     RBT<K, V> T = M.thisTree();
     RBTUtilities<K, V> utilities = new RBTUtilities<K, V>();
     
     if (!utilities.isRBT(T, false)) {
       System.out.println("Resulting tree is not a valid red-black tree.");
       utilities.isRBT(T, true);
     };
     System.out.println("");
     
   } else {
   
     System.out.println("Attempting search for null key.");
     
     try {
     
       M.get(null);
       System.out.println("A NullPointerException was expected, but not thrown.");
     
     } catch (NullPointerException ex1) {
     
       System.out.println("A NullPointerException was thrown, as expected");
     
     }
   
   }

 }
 
 /*
 * 
 * Test to set the value for a non-null key
 *
 */
 
 public void testSet(RBTMap<K, V> M,
                     K key, V value, int size) {
   
   System.out.print("Trying to set the value of \"" + key.toString());
   System.out.print("\" to be ");
   if (value != null) {
     System.out.print("\"" + value.toString() + "\"...");
   } else {
     System.out.print("null...");
   };
   
   M.set(key, value);
   try {
     V foundValue = M.get(key);
     if (value != null) {
       if (foundValue != null) {
         if (!value.equals(foundValue)) {
           System.out.println(" Not OK.");
           System.out.print("When checked, the value ");
           System.out.print("\"" + foundValue.toString() + "\" was found, ");
           System.out.println("instead.");
         } else {
           System.out.println("OK.");
         }
       } else {
         System.out.println(" Not OK.");
         System.out.println("When checked, the value null was found instead.");
       }
     } else {
       if (foundValue != null) {
         System.out.println(" Not OK.");
         System.out.print("When checked, the value \"");
         System.out.print(foundValue.toString());
         System.out.println("\" was found instead.");
       }
     }
   } catch (NoSuchElementException ex) {
     System.out.println(" Not OK.");
     System.out.println("When checked, no value was found.");
   };
   
   RBT<K, V> T = M.thisTree();
   RBTUtilities<K, V> utilities = new RBTUtilities<K, V>();
   
   int foundSize = M.size();
   if (size != foundSize) {
     System.out.print("Oops! The reported size is now " + foundSize + " instead of ");
     System.out.println(size + ".");
   };
   
   if (!utilities.isRBT(T, false)) {
    System.out.println("Oops! The result is not a red-black tree.");
    utilities.isRBT(T, false);
   }
                     
 }
 
 /*
 *
 * Tests the deletion of a value for a non-null key
 *
 */
 
 public void testDelete(RBTMap<K, V> M, K key, boolean success, int size) {
 
   System.out.print("Trying to delete the value assigned to ");
   System.out.println("\"" + key.toString() + "\".");
   try {
     M.remove(key);
     if (success) {
        System.out.println("Deletion was apparently successful.");
     } else {
       System.out.print("Deletion failed to throw the expected ");
       System.out.println("NoSuchElementException.");
     };
   } catch (NoSuchElementException ex) {
     if (success) {
       System.out.print("Deletion unexpectedly threw a NoSuchElementException.");
     } else {
       System.out.println("Deletion threw a NoSuchElementException, as expected.");
     };
   };
 
   RBT<K, V> T = M.thisTree();
   RBTUtilities<K, V> utilities = new RBTUtilities<K, V>();
   
   int foundSize = T.size();
   if (size != foundSize) {
     System.out.print("The reported size is now " + foundSize + " instead of ");
     System.out.println(size + ".");
   };
   
   if (!utilities.isRBT(T, false)) {
    System.out.println("The result is not a red-black tree.");
    utilities.isRBT(T, false);
   }
 }

}
