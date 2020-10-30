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
 
 public class testSearch {
 
   // Creates a nontrivial red-black tree to be used to test searches
   
   RBT<Integer, String> sampleTree() {
   
     RBTNode<Integer, String> node27 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node26 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node25 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node24 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node23
       = new RBTNode<Integer, String>(32, "thirty-two", Colour.BLACK,
                                                node26, node27, 1, 1);
     RBTNode<Integer, String> node22
       = new RBTNode<Integer, String>(29, "twenty-nine", Colour.BLACK,
                                                node24, node25, 1, 1);
     RBTNode<Integer, String> node21 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node20 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node19 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node18 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node17 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node16 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node15
       = new RBTNode<Integer, String>(30, "thirty", Colour.RED,
                                                node22, node23, 3, 2);
     RBTNode<Integer, String> node14
       = new RBTNode<Integer, String>(23, "twenty-three", Colour.BLACK,
                                                node20, node21, 1, 1);
     RBTNode<Integer, String> node13
       = new RBTNode<Integer, String>(16, "sixteen", Colour.BLACK,
                                                node18, node19, 1, 1);
     RBTNode<Integer, String> node12
       = new RBTNode<Integer, String>(10, "ten", Colour.BLACK,
                                                node16, node17, 1, 1);
     RBTNode<Integer, String> node11 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node10 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node9 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node8 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> node7
       = new RBTNode<Integer, String>(25, "twenty-five", Colour.BLACK,
                                                node14, node15, 5, 2);
     RBTNode<Integer, String> node6
       = new RBTNode<Integer, String>(13, "thirteen", Colour.BLACK,
                                                node12, node13, 3, 2);
     RBTNode<Integer, String> node5
       = new RBTNode<Integer, String>(5, "five", Colour.BLACK,
                                                node10, node11, 1, 1);
     RBTNode<Integer, String> node4
       = new RBTNode<Integer, String>(-4, "negative four", Colour.BLACK,
                                                node8, node9, 1, 1);
     RBTNode<Integer, String> node3
       = new RBTNode<Integer, String>(20, "twenty", Colour.RED,
                                                node6, node7, 9, 3);
     RBTNode<Integer, String> node2
       = new RBTNode<Integer, String>(1, "one", Colour.BLACK,
                                                node4, node5, 3, 2);
     RBTNode<Integer, String> node1
       = new RBTNode<Integer, String>(8, "eight", Colour.BLACK,
                                                node2, node3, 13, 3);
     RBT<Integer, String> T = new RBT<Integer, String>(node1);
     return T;
   
   }
   
   // Tests of Searches in an Empty Tree
   // Check that the expected exception is thrown, that the resulting
   // tree is still a red-black tree, and that the resulting tree is
   // still an empty tree
   
   @Test
   public void TestEmptyTree1 () {
     RBT<Integer, String> T= new RBT<Integer, String>();
     RBTUtilities<Integer, String> utilities = new RBTUtilities<Integer, String>();
     try {
       String value = T.get(0);
     } catch (NoSuchElementException ex) {
       assertTrue("Search in empty tree results in invalid red-black tree",
                   utilities.isRBT(T, true));
       return;
     };
     assertTrue("Search in empty tree did not throw expected exception",
                false);
   }
   
   @Test
   public void TestEmptyTree2 () {
     RBT<Integer, String> T = new RBT<Integer, String>();
     try {
       String value = T.get(0);
     } catch (NoSuchElementException ex) {
       assertTrue("Search in empty tree produces a nonempty tree",
                  T.size() == 0);
     }
   }
   
   // The next check should pass along students changed code they should
   // not have.
   
   @Test
   public void CheckTree () {
    RBTUtilities<Integer, String> utilities = new RBTUtilities<Integer, String>();
    RBT<Integer, String> T = sampleTree();
    assertTrue("Check whether sample tree is a red-black tree failed",
                utilities.isRBT(T, true));
   }
   
   // Tests for various searches using sample tree
   
   public void TestFails1 (int testValue) {
     RBT<Integer, String> T = sampleTree();
     RBTUtilities<Integer, String> utilities = new RBTUtilities<Integer, String>();
     try {
       String value = T.get(testValue);
       assertTrue("Search for " + testValue + " failed to thrown NoSuchElementException.",
                   false);
     } catch (NoSuchElementException ex) {
       assertTrue("Tree is not a valid red-black tree after search for "
                     + testValue + ".", utilities.isRBT(T, true));
     }
   }
   
   public void TestFails2 (int testValue) {
     RBT<Integer, String> T = sampleTree();
     try {
       String value = T.get(testValue);
       assertTrue("Tree changed after unsuccessful search for "
                   + testValue + ".", T.size() == 13);
     } catch (NoSuchElementException ex) {
       assertTrue("Tree changed after unsuccessful search for "
                   + testValue + ".", T.size() == 13);
     }
   }
   
   public void TestSuccess1 (int testValue, String testResult) {
     RBT<Integer, String> T = sampleTree();
     try {
       String result = T.get(testValue);
       assertTrue("Search incorrectly returned string \""
                  + result + "\" after search for "
                  + testValue + ".", result == testResult );
     } catch (NoSuchElementException ex) {
       assertTrue("Search for " + testValue
                  + " incorrectly threw NoSuchElementException.", false);
     }
   }
   
   public void TestSuccess2 (int testValue) {
     RBT<Integer, String> T = sampleTree();
     RBTUtilities<Integer, String> utilities = new RBTUtilities<Integer, String>();
     try {
       String result = T.get(testValue);
     } catch (NoSuchElementException ex) {     
     };
     assertTrue("Search for " + testValue
                +" resulted in an invalid red-black tree.",
                utilities.isRBT(T, true));
   }
   
   public void TestSuccess3 (int testValue) {
     RBT<Integer, String> T = sampleTree();
     try {
       String result = T.get(testValue);
     } catch (NoSuchElementException ex) {
     };
     assertTrue("Tree changed after successful search for "
                + testValue + ".", T.size() == 13);
   }
   
   @Test
   public void TestTooSmall1 () {
     TestFails1(-5);
   }
   
   @Test
   public void TestTooSmall2 () {
     TestFails2(-5);
   }
   
   @Test
   public void TestSmallestLeaf1 () {
     TestSuccess1(-4, "negative four");
   }
   
   @Test
   public void TestSmallestLeaf2 () {
     TestSuccess2(-4);
   }
   
   @Test
   public void TestSmallestLeaf3 () {
     TestSuccess3(-4);
   }
   
   @Test
   public void TestIntermediateFailure1 () {
     TestFails1(6);
   }
   
   @Test
   public void TestIntermediateFailure2 () {
     TestFails2(6);
   }
   
   @Test
   public void TestFindRoot1 () {
     TestSuccess1(8, "eight");
   }
   
   @Test
   public void TestFindRoot2 () {
     TestSuccess2(8);
   }
   
   @Test
   public void TestFindRoot3 () {
     TestSuccess3(8);
   }
   
   @Test
   public void TestFindInternal1 () {
     TestSuccess1(13, "thirteen");
   }
   
   @Test
   public void TestFindInternal2 () {
     TestSuccess2(13);
   }
   
   @Test
   public void TestFindInternal3 () {
     TestSuccess3(13);
   }
   
   @Test
   public void TestFindLargestLeaf1 () {
     TestSuccess1(32, "thirty-two");
   }
   
   @Test
   public void TestFindLargestLeaf2 () {
     TestSuccess2(32);
   }
   
   @Test
   public void TestFindLargestLeaf3 () {
     TestSuccess3(32);
   }
   
   @Test
   public void TestTooLarge1 () {
     TestFails1(40);
   }
   
   @Test
   public void TestTooLarge2 () {
     TestFails2(40);
   }
   
 }