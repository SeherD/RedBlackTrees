package cpsc331.assignment2;

import org.junit.*;
import static org.junit.Assert.*;
import cpsc331.assignment2.Colour;
import java.util.NoSuchElementException;
import cpsc331.collections.ElementFoundException;
import cpsc331.assignment2.RBTNode;
import cpsc331.assignment2.RBT;
import cpsc331.assignment2.RBTUtilities;

/*
 *
 * Test suite for searching in a red-black tree.
 *
 *  Requires JUnit 4.x
 *
 */
 
 public class testInsert {
 
   // Utility methods for tests of successful insertions
   
   public void testSuccess1(RBT<Integer, String> T,
                       Integer argument, String value) {
     try {
       T.insert(argument, value);
       String returnValue = T.get(argument);
       assertTrue("Search for " + argument + " returned \""
         + returnValue + "\" instead of expected value \""
         + value + "\".", value == returnValue);    
     } catch (ElementFoundException ex) {
       assertTrue("Attempt to insert " + argument
         + " with value \"" + value + "\" failed.", false);
     } catch (NoSuchElementException ex) {
       assertTrue("Attempt to retrieve value for " + argument
         + " after its insertion failed.", false);
     }
   
   }
   
   public void testSuccess2(RBT<Integer, String> T,
                            Integer argument, String value) {
                            
     RBTUtilities<Integer, String> utilities = new RBTUtilities<Integer, String>();
     try {
      T.insert(argument, value);
     } catch (ElementFoundException ex) {
     };
     assertTrue("Insertion of " + argument
                  + " produced an invalid red-black tree.",
                  utilities.isRBT(T, true));
   }
   
   public void testSuccess3(RBT<Integer, String> T,
                            Integer argument, String value, Integer size) {
                            
     try {
       T.insert(argument, value);
     } catch (ElementFoundException ex) {
     };
     assertTrue("Insertion of " + argument
                + " produced a red-black tree with the wrong size.",
                T.size() == size + 1);
   }
   
   // Utility methods for tests of unsuccessful insertions
   
   public void testFailure1(RBT<Integer, String> T,
                            Integer argument, String value) {
                            
     try {
       T.insert(argument, value);
       assertTrue("Attempt to insert " + argument
                   + " did not produce an ElementFoundException.", false);
     } catch (ElementFoundException ex) {
     }
                            
   }
   
   public void testFailure2(RBT<Integer, String> T,
                            Integer argument, String oldValue, String newValue) {
     
     try {
       T.insert(argument, newValue);
       assertTrue("Attempt to insert " + argument
                  + " did not produce an ElementFoundException", false);
     } catch (ElementFoundException ex) {
       try {
         String reportedValue = T.get(argument);
         assertTrue("Attempt to insert " + argument
                  + " again changed its value to \""
                  + reportedValue + "\".",
                  reportedValue == oldValue);
       } catch (NoSuchElementException ex1) {
         assertTrue("Attempt to insert " + argument
                  + " again has removed it from the tree.", false);
       }
     }
                    
   }
   
   public void testFailure3(RBT<Integer, String> T,
                            Integer argument, String value) {
   
     RBTUtilities<Integer, String> utilities = new RBTUtilities<Integer, String>();
     try {
       T.insert(argument, value);
     } catch (ElementFoundException ex) {
     };
     assertTrue("Attempt to insert " + argument
                  + " produced an invalid red-black tree.",
                  utilities.isRBT(T, true));
                            
   }
   
   public void testFailure4(RBT<Integer, String> T,
                            Integer argument, String value, Integer size) {
   
     try {
       T.insert(argument, value);
     } catch (ElementFoundException ex) {
     };
     assertTrue("Attempt to insert " + argument
                + " again changed its size from " + size
                + " to " + T.size() + ".",
                size == T.size());
   }
   
   @Test
   public void TestEmpty1() {
     RBT<Integer, String> T = new RBT<Integer, String>();
     testSuccess1(T, 3, "three");
   }
   
   @Test
   public void TestEmpty2() {
     RBT<Integer, String> T = new RBT<Integer, String>();
     testSuccess2(T, 3, "three");
   }
   
   @Test
   public void TestEmpty3() {
     RBT<Integer, String> T = new RBT<Integer, String>();
     testSuccess3(T, 3, "three", 0);
   }
   
   RBT<Integer, String> sampleTree1() {
   
     RBTNode<Integer, String> node3 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node2 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node1
       = new RBTNode<Integer, String>(3, "three", Colour.BLACK,
                                          node2, node3, 1, 1);
     RBT<Integer, String> T = new RBT<Integer, String>(node1);
     return T;
     
   }
 
   @Test
   public void TestSimpleFail1 () {
     RBT<Integer, String> T = sampleTree1();
     testFailure1(T, 3, "three");
   }
   
   @Test
   public void TestSimpleFail2() {
     RBT<Integer, String> T = sampleTree1();
     testFailure2(T, 3, "three", "THREE");
   }
   
   @Test
   public void TestSimpleFail3() {
     RBT<Integer, String> T = sampleTree1();
     testFailure3(T, 3, "three");
   }
   
   @Test
   public void TestSimpleFail4() {
     RBT<Integer, String> T = sampleTree1();
     testFailure4(T, 3, "three", 1);
   }
   
   @Test
   public void TestSimpleLeft1() {
     RBT<Integer, String> T = sampleTree1();
     testSuccess1(T, 1, "one");
   }
   
   @Test
   public void TestSimpleLeft2() {
     RBT<Integer, String> T = sampleTree1();
     testSuccess2(T, 1, "one");
   }
   
   @Test
   public void TestSimpleLeft3() {
     RBT<Integer, String> T = sampleTree1();
     testSuccess3(T, 1, "one", 1);
   }
   
   @Test
   public void TestSimpleRight1() {
     RBT<Integer, String> T = sampleTree1();
     testSuccess1(T, 5, "five");
   }
   
   @Test
   public void TestSimpleRight2() {
     RBT<Integer, String> T = sampleTree1();
     testSuccess2(T, 5, "five");
   }
   
   @Test
   public void TestSimpleRight() {
     RBT<Integer, String> T = sampleTree1();
     testSuccess3(T, 5, "five", 1);
   }
 
 // Insertion into larger tree; no red-red situation
 
   RBT<Integer, String> sampleTree2 () {
     RBTNode<Integer, String> n1 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n2 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n3 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n4 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n5
       = new RBTNode<Integer, String>(2, "two", Colour.BLACK, n1, n2, 1, 1);
     RBTNode<Integer, String> n6
       = new RBTNode<Integer, String>(6, "six", Colour.BLACK, n3, n4, 1, 1);
     RBTNode<Integer, String> n7
       = new RBTNode<Integer, String>(4, "four", Colour.BLACK, n5, n6, 3, 2);
     RBT<Integer, String> T = new RBT<Integer, String>(n7);
     return T;
   }
   
   @Test
   public void TestEasy1_1 () {
     RBT<Integer, String> T = sampleTree2();
     testSuccess1(T, 3, "three");
   }
   
   @Test
   public void TestEasy1_2 () {
     RBT<Integer, String> T = sampleTree2();
     testSuccess2(T, 3, "three");
   }
   
   @Test
   public void TestEasy1_3 () {
     RBT<Integer, String> T = sampleTree2();
     testSuccess3(T, 3, "three", 3);
   }
   
   @Test
   public void TestEasy2_1 () {
     RBT<Integer, String> T = sampleTree2();
     testSuccess1(T, 5, "five");
   }
   
   @Test
   public void TestEasy2_2 () {
     RBT<Integer, String> T = sampleTree2();
     testSuccess2(T, 5, "five");
   }
   
   @Test
   public void TestEasy2_3 () {
     RBT<Integer, String> T = sampleTree2();
     testSuccess3(T, 5, "five", 3);
   }
   
   // Insertion into larger tree: Subcase 1(a)
   
   RBT<Integer, String> sampleTree3 () {
     RBTNode<Integer, String> n1 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n2 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n3 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n4 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n5
       = new RBTNode<Integer, String>(2, "two", Colour.RED, n1, n2, 1, 1);
     RBTNode<Integer, String> n6
       = new RBTNode<Integer, String>(6, "six", Colour.RED, n3, n4, 1, 1);
     RBTNode<Integer, String> n7
       = new RBTNode<Integer, String>(4, "four", Colour.BLACK, n5, n6, 3, 1);
     RBT<Integer, String> T = new RBT<Integer, String>(n7);
     return T;
   }
   
   @Test
   public void TestSimple1a_1 () {
     RBT<Integer, String> T = sampleTree3();
     testSuccess1(T, 1, "one");
   }
   
   @Test
   public void TestSimple1a_2 () {
     RBT<Integer, String> T = sampleTree3();
     testSuccess2(T, 1, "one");
   }
   
   @Test
   public void TestSimple1a_3 () {
     RBT<Integer, String> T = sampleTree3();
     testSuccess3(T, 1, "one", 3);
   }
   
   // Insertion into larger tree: Subcase 1(b)
   
   @Test
   public void TestSimple1b_1 () {
     RBT<Integer, String> T = sampleTree3();
     testSuccess1(T, 3, "three");
   }
   
   @Test
   public void TestSimple1b_2 () {
     RBT<Integer, String> T = sampleTree3();
     testSuccess2(T, 3, "three");
   }
   
   @Test
   public void TestSimple1b_3 () {
     RBT<Integer, String> T = sampleTree3();
     testSuccess3(T, 3, "three", 3);
   }
   
   // Insert into large subtree: Subcase 2
   
   RBT<Integer, String> sampleTree4 () {
     RBTNode<Integer, String> n1 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n2 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n3
       = new RBTNode<Integer, String>(2, "two", Colour.RED, n1, n2, 1, 1);
     RBTNode<Integer, String> n4 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n5
       = new RBTNode<Integer, String>(4, "four", Colour.BLACK, n3, n4, 2, 1);
     RBT<Integer, String> T = new RBT<Integer, String>(n5);
     return T;
   }
   
   @Test
   public void TestSimple2_1() {
     RBT<Integer, String> T = sampleTree4();
     testSuccess1(T, 1, "one");
   }
   
   @Test
   public void TestSimple2_2() {
     RBT<Integer, String> T = sampleTree4();
     testSuccess2(T, 1, "one");
   }
   
   @Test
   public void TestSimple2_3() { 
     RBT<Integer, String> T = sampleTree4();
     testSuccess3(T, 1, "one", 2);
   }
   
   // Insert into large subtree: Subcase 3
   
   @Test
   public void TestSimple3_1() {
     RBT<Integer, String> T = sampleTree4();
     testSuccess1(T, 3, "three");
   }
   
   @Test
   public void TestSimple3_2() {
     RBT<Integer, String> T = sampleTree4();
     testSuccess2(T, 3, "three");
   }
   
   @Test
   public void TestSimple3_3() {
     RBT<Integer, String> T = sampleTree4();
     testSuccess3(T, 3, "three", 2);
   }
   
   // Insert into large subtree: Subcase 4(a)
   
   @Test
   public void TestSimple4a_1 () {
     RBT<Integer, String> T = sampleTree3();
     testSuccess1(T, 7, "seven");
   }
   
   @Test
   public void TestSimple4a_2 () {
     RBT<Integer, String> T = sampleTree3();
     testSuccess2(T, 7, "seven");
   }
   
   @Test
   public void TestSimple4a_3 () {
     RBT<Integer, String> T = sampleTree3();
     testSuccess3(T, 7, "seven", 3);
   }
   
   // Insertion into larger tree: Subcase 4(b)
   
   @Test
   public void TestSimple4b_1 () {
     RBT<Integer, String> T = sampleTree3();
     testSuccess1(T, 5, "five");
   }
   
   @Test
   public void TestSimple4b_2 () {
     RBT<Integer, String> T = sampleTree3();
     testSuccess2(T, 5, "five");
   }
   
   @Test
   public void TestSimple4b_3 () {
     RBT<Integer, String> T = sampleTree3();
     testSuccess3(T, 5, "five", 3);
   }
   
   // Insert into larger tree: Subcase 5
   
   RBT<Integer, String> sampleTree5() {
     RBTNode<Integer, String> n1 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n2 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n3 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n4
       = new RBTNode<Integer, String>(4, "four", Colour.RED, n1, n2, 1, 1);
     RBTNode<Integer, String> n5
       = new RBTNode<Integer, String>(2, "two", Colour.BLACK, n3, n4, 2, 1);
     RBT<Integer, String> T = new RBT<Integer, String>(n5);
     return T;
   }
   
   @Test
   public void TestSimple5_1() {
     RBT<Integer, String> T = sampleTree5();
     testSuccess1(T, 5, "five");
   }
   
   @Test
   public void TestSimple5_2() {
     RBT<Integer, String> T = sampleTree5();
     testSuccess2(T, 5, "five");
   }
   
   @Test
   public void TestSimple_5_3() {
     RBT<Integer, String> T = sampleTree5();
     testSuccess3(T, 5, "five", 2);
   }
   
   // Insert into larger tree: Subcase 6
   
   @Test
   public void TestSimple6_1() {
     RBT<Integer, String> T = sampleTree5();
     testSuccess1(T, 3, "three");
   }
   
   @Test
   public void TestSimple6_2() {
     RBT<Integer, String> T = sampleTree5();
     testSuccess2(T, 3, "three");
   }
   
   @Test
   public void TestSimple6_3() {
     RBT<Integer, String> T = sampleTree5();
     testSuccess3(T, 3, "three", 2);
   }
   
}