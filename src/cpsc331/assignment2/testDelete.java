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
 
 public class testDelete {
 
   // Utility Methods for Successful Deletions
   
   public void testSuccess1 (RBT<Integer, String> T, Integer value) {
   
     try {
       T.delete(value);
     } catch (NoSuchElementException ex) {
       assertTrue("Deletion of " + value
                   + " caused a NoSuchElementException to be thrown.",
                   false);
     };
     try {
       T.delete(value);
       assertTrue("Second attempt to delete " + value
                    + " confirms that first attempt failed.", false);
     } catch (NoSuchElementException ex1) {
     }
   }
   
   public void testSuccess2 (RBT<Integer, String> T, Integer value) {
   
     RBTUtilities<Integer, String> utilities = new RBTUtilities<Integer, String>();
     try {
       T.delete(value);
     } catch (NoSuchElementException ex) {
     };
     assertTrue("Deletion of " + value + " resulted in an invalid red-black tree.",
                 utilities.isRBT(T, true));
   
   }
   
   public void testSuccess3 (RBT<Integer, String> T, Integer value, Integer size) {
   
     try {
       T.delete(value);
     } catch (NoSuchElementException ex) { 
     };
     assertTrue("Deletion of " + value + " resulted in a red-black tree "
                 + "with the wrong size.", T.size() == size - 1);
   
   }
   
   // Utility Methods for Unsuccessful Deletions
   
   public void testFail1 (RBT<Integer, String> T, Integer value) {
   
     try {
       T.delete(value);
       assertTrue("Deletion of " + value + " failed to throw "
                   + " a NoSuchElementException.", false);
     } catch (NoSuchElementException ex) {
     }
   
   }
   
   public void testFail2 (RBT<Integer, String> T, Integer value) {
   
     RBTUtilities<Integer, String> utilities = new RBTUtilities<Integer, String>();
     try {
       T.delete(value);
     } catch (NoSuchElementException ex) {
     };
     assertTrue("Deletion of " + value + " resulted in an invalid"
                + " red-black tree.", utilities.isRBT(T, true));
   
   }
   
   public void testFail3 (RBT<Integer, String> T, Integer value, Integer size) {
   
     try {
       T.delete(value);
     } catch (NoSuchElementException ex) {
     };
     assertTrue("Deletion of " + value + " changed the size of the "
                + "red-black tree.", size == T.size());
   
   }
   
   @Test
   public void TestEmpty1 () {
     RBT<Integer, String> T = new RBT<Integer, String>();
     testFail1(T, 2);
   }
   
   @Test
   public void TestEmpty2 () {
     RBT<Integer, String> T = new RBT<Integer, String>();
     testFail2(T, 2);
   }
   
   @Test
   public void TestEmpty3 () {
     RBT<Integer, String> T = new RBT<Integer, String>();
     testFail3(T, 2, 0);
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
   public void TestSimpleSuccess1 () {
     RBT<Integer, String> T = sampleTree1();
     testSuccess1(T, 3);
   }
   
   @Test
   public void TestSimpleSuccess2 () {
     RBT<Integer, String> T = sampleTree1();
     testSuccess2(T, 3);
   }
   
   @Test
   public void TestSimpleSuccess3 () {
     RBT<Integer, String> T = sampleTree1();
     testSuccess3(T, 3, 1);
   }
   
   @Test
   public void TestFailLeft1 () {
     RBT<Integer, String> T = sampleTree1();
     testFail1(T, 1);
   }
   
   @Test
   public void TestFailLeft2 () {
     RBT<Integer, String> T = sampleTree1();
     testFail2(T, 1);
   }
   
   @Test
   public void TestFailLeft3 () {
     RBT<Integer, String> T = sampleTree1();
     testFail3(T, 1, 1);
   }
   
   @Test
   public void TestFailRight1 () {
     RBT<Integer, String> T = sampleTree1();
     testFail1(T, 5);
   }
   
   @Test
   public void TestFailRight2 () {
     RBT<Integer, String> T = sampleTree1();
     testFail2(T, 5);
   }
   
   @Test
   public void TestFailRight3 () {
    RBT<Integer, String> T = sampleTree1();
    testFail3(T, 5, 1);
   }
   
   // Deletions of red nodes
   
   RBT<Integer, String> sampleTree2() {
     RBTNode<Integer, String> n5 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n4 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n3 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n2
       = new RBTNode<Integer, String>(2, "two", Colour.RED, n4, n5, 1, 1);
     RBTNode<Integer, String> n1
       = new RBTNode<Integer, String>(4, "four", Colour.BLACK, n2, n3, 2, 1);
     RBT<Integer, String> T = new RBT<Integer, String>(n1);
     return T;
   }
   
   RBT<Integer, String> sampleTree3() {
     RBTNode<Integer, String> n5 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n4 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n3
       = new RBTNode<Integer, String>(4, "four", Colour.RED, n4, n5, 1, 1);
     RBTNode<Integer, String> n2 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n1
       = new RBTNode<Integer, String>(2, "two", Colour.BLACK, n2, n3, 2, 1);
     RBT<Integer, String> T = new RBT<Integer, String>(n1);
     return T;
   }
   
   @Test
   public void TestRemoveRed1() {
     RBT<Integer, String> T = sampleTree2();
     testSuccess1(T, 2); 
   }
   
   @Test
   public void TestRemoveRed2() {
     RBT<Integer, String> T = sampleTree2();
     testSuccess2(T, 2);
   }
   
   @Test
   public void TestRemoveRed3() {
     RBT<Integer, String> T = sampleTree2();
     testSuccess3(T, 2, 2);
   }
   
   @Test
   public void TestRemoveRed4() {
     RBT<Integer, String> T = sampleTree3();
     testSuccess1(T, 4);
   }
   
   @Test
   public void TestRemoveRed5() {
     RBT<Integer, String> T = sampleTree3();
     testSuccess2(T, 4);
   }
   
   @Test
   public void TestRemoveRed6() {
     RBT<Integer, String> T = sampleTree3();
     testSuccess3(T, 4, 2);
   }
   
   // Deletion of red-black node
   
   RBT<Integer, String> sampleTree4() {
     RBTNode<Integer, String> n5 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n4 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n3 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n2
       = new RBTNode<Integer, String>(2, "two", Colour.RED, n4, n5, 1, 1);
     RBTNode<Integer, String> n1
       = new RBTNode<Integer, String>(4, "four", Colour.BLACK, n2, n3, 2, 1);
     RBT<Integer, String> T = new RBT<Integer, String>(n1);
     return T;
   }
   
   @Test
   public void TestRedBlack1() {
    RBT<Integer, String> T = sampleTree4();
    testSuccess1(T, 4);
   }
   
   @Test
   public void TestRedBlack2() {
     RBT<Integer, String> T = sampleTree4();
     testSuccess2(T, 4);
   }
   
   @Test
   public void TestRedBlack3() {
     RBT<Integer, String> T = sampleTree4();
     testSuccess3(T, 4, 2);
   }
   
   RBT<Integer, String> sampleTree5() {
     RBTNode<Integer, String> n5 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n4 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n3
       = new RBTNode<Integer, String>(4, "four", Colour.RED, n4, n5, 1, 1);
     RBTNode<Integer, String> n2 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n1
       = new RBTNode<Integer, String>(2, "two", Colour.BLACK, n2, n3, 2, 1);
     RBT<Integer, String> T = new RBT<Integer, String>(n1);
     return T;
   }
   
   @Test
   public void TestRedBlack4() {
     RBT<Integer, String> T = sampleTree5();
     testSuccess1(T, 2);
   }
   
   @Test
   public void TestRedBlack5() {
     RBT<Integer, String> T = sampleTree5();
     testSuccess2(T, 2);
   }
   
   @Test
   public void TestRedBlack6() {
     RBT<Integer, String> T = sampleTree5();
     testSuccess3(T, 2, 2);
   }
   
   RBT<Integer, String> sampleTree6() {
     RBTNode<Integer, String> n9 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n8 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n7 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n6 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n5 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n4
       = new RBTNode<Integer, String>(2, "two", Colour.RED, n8, n9, 1, 1);
     RBTNode<Integer, String> n3
       = new RBTNode<Integer, String>(8, "eight", Colour.BLACK, n6, n7, 1, 1);
     RBTNode<Integer, String> n2
       = new RBTNode<Integer, String>(4, "four", Colour.BLACK, n4, n5, 2, 1);
     RBTNode<Integer, String> n1
       = new RBTNode<Integer, String>(6, "six", Colour.BLACK, n2, n3, 4, 2);
     RBT<Integer, String> T = new RBT<Integer, String>(n1);
     return T;
   }
   
   @Test
   public void TestRedBlack7() {
     RBT<Integer, String> T = sampleTree6();
     testSuccess1(T, 4);
   }
   
   @Test
   public void TestRedBlack8() {
     RBT<Integer, String> T = sampleTree6();
     testSuccess2(T, 4);
   }
   
   @Test
   public void TestRedBlack9() {
     RBT<Integer, String> T = sampleTree6();
     testSuccess3(T, 4, 4);
   }
   
   // Subcase 3(a)
   
   RBT<Integer, String> sampleTree7() {
     RBTNode<Integer, String> n19 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n18 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n17 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n16 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n15
       = new RBTNode<Integer, String>(18, "eighteen", Colour.BLACK, n18, n19, 1, 1);
     RBTNode<Integer, String> n14
       = new RBTNode<Integer, String>(14, "fourteen", Colour.BLACK, n16, n17, 1, 1);
     RBTNode<Integer, String> n13 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n12 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n11 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n10 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n9 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n8 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n7
       = new RBTNode<Integer, String>(16, "sixteen", Colour.RED, n14, n15, 3, 2);
     RBTNode<Integer, String> n6
       = new RBTNode<Integer, String>(10, "ten", Colour.BLACK, n12, n13, 1, 1);
     RBTNode<Integer, String> n5
       = new RBTNode<Integer, String>(6, "six", Colour.BLACK, n10, n11, 1, 1);
     RBTNode<Integer, String> n4
       = new RBTNode<Integer, String>(2, "two", Colour.BLACK, n8, n9, 1, 1);
     RBTNode<Integer, String> n3
       = new RBTNode<Integer, String>(12, "twelve", Colour.BLACK, n6, n7, 5, 2);
     RBTNode<Integer, String> n2
       = new RBTNode<Integer, String>(4, "four", Colour.BLACK, n4, n5, 3, 2);
     RBTNode<Integer, String> n1
       = new RBTNode<Integer, String>(8, "eight", Colour.BLACK, n2, n3, 9, 3);
     RBT<Integer, String> T = new RBT<Integer, String>(n1);
     return T;
   }
   
   @Test
   public void Test3a_1() {
     RBT<Integer, String> T = sampleTree7();
     testSuccess1(T, 2);
   }
   
   @Test
   public void Test3a_2() {
     RBT<Integer, String> T = sampleTree7();
     testSuccess2(T, 2);
   }
   
   @Test
   public void Test3a_3() {
     RBT<Integer, String> T = sampleTree7();
     testSuccess3(T, 2, 9);
   }
   
   // Subcase 3(b)
   
   RBT<Integer, String> sampleTree8() {
     RBTNode<Integer, String> n23 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n22 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n21 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n20 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n19 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n18 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n17 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n16 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n15 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n14 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n13 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n12 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n11
       = new RBTNode<Integer, String>(14, "fourteen", Colour.BLACK, n22, n23, 1, 1);
     RBTNode<Integer, String> n10
       = new RBTNode<Integer, String>(10, "ten", Colour.BLACK, n20, n21, 1, 1);
     RBTNode<Integer, String> n9
       = new RBTNode<Integer, String>(6, "six", Colour.BLACK, n18, n19, 1, 1);
     RBTNode<Integer, String> n8
       = new RBTNode<Integer, String>(2, "two", Colour.BLACK, n16, n17, 1, 1);
     RBTNode<Integer, String> n7
       = new RBTNode<Integer, String>(22, "twenty-two", Colour.BLACK, n14, n15, 1, 1);
     RBTNode<Integer, String> n6
       = new RBTNode<Integer, String>(18, "eighteen", Colour.BLACK, n12, n13, 1, 1);
     RBTNode<Integer, String> n5
       = new RBTNode<Integer, String>(12, "twelve", Colour.BLACK, n10, n11, 3, 2);
     RBTNode<Integer, String> n4
       = new RBTNode<Integer, String>(4, "four", Colour.BLACK, n8, n9, 3, 2);
     RBTNode<Integer, String> n3
       = new RBTNode<Integer, String>(20, "twenty", Colour.BLACK, n6, n7, 3, 2);
     RBTNode<Integer, String> n2
       = new RBTNode<Integer, String>(8, "eight", Colour.RED, n4, n5, 7, 3);
     RBTNode<Integer, String> n1
       = new RBTNode<Integer, String>(16, "sixteen", Colour.BLACK, n2, n3, 11, 3);
     RBT<Integer, String> T = new RBT<Integer, String>(n1);
     return T;
   }
   
   @Test
   public void Test3b_1() {
     RBT<Integer, String> T = sampleTree8();
     testSuccess1(T, 6);
   }
   
   @Test
   public void Test3b_2() {
     RBT<Integer, String> T = sampleTree8();
     testSuccess2(T, 6);
   }
   
   @Test
   public void Test3b_3() {
     RBT<Integer, String> T = sampleTree8();
     testSuccess3(T, 6, 11);
   }
   
   // Subcase 3(c)
   
   RBT<Integer, String> sampleTree9() {
    RBTNode<Integer, String> n19 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n18 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n17 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n16 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n15 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n14 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n13
      = new RBTNode<Integer, String>(14, "fourteen", Colour.BLACK, n18, n19, 1, 1);
    RBTNode<Integer, String> n12
      = new RBTNode<Integer, String>(10, "ten", Colour.BLACK, n16, n17, 1, 1);
    RBTNode<Integer, String> n11 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n10 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n9 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n8 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n7
      = new RBTNode<Integer, String>(18, "eighteen", Colour.BLACK, n14, n15, 1, 1);
    RBTNode<Integer, String> n6
      = new RBTNode<Integer, String>(12, "twelve", Colour.RED, n12, n13, 3, 2);
    RBTNode<Integer, String> n5
      = new RBTNode<Integer, String>(6, "six", Colour.BLACK, n10, n11, 1, 1);
    RBTNode<Integer, String> n4
      = new RBTNode<Integer, String>(2, "two", Colour.BLACK, n8, n9, 1, 1);
    RBTNode<Integer, String> n3
      = new RBTNode<Integer, String>(16, "sixteen", Colour.BLACK, n6, n7, 5, 2);
    RBTNode<Integer, String> n2
      = new RBTNode<Integer, String>(4, "four", Colour.BLACK, n4, n5, 3, 2);
    RBTNode<Integer, String> n1
      = new RBTNode<Integer, String>(8, "eight", Colour.BLACK, n2, n3, 9, 3);
    RBT<Integer, String> T = new RBT<Integer, String>(n1);
    return T;
   }
   
   @Test 
   public void Test3c_1() {
    RBT<Integer, String> T = sampleTree9();
    testSuccess1(T, 2);
   }
   
   @Test
   public void Test3c_2() {
     RBT<Integer, String> T = sampleTree9();
     testSuccess2(T, 2);
   }
   
   @Test
   public void Test3c_3() {
     RBT<Integer, String> T = sampleTree9();
     testSuccess3(T, 2, 9);
   }
   
   // Subcase 3(d)
   
   RBT<Integer, String> sampleTree10() {
    RBTNode<Integer, String> n23 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n22 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n21 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n20 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n19 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n18 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n17 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n16 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n15
      = new RBTNode<Integer, String>(22, "twenty-two", Colour.BLACK, n22, n23, 1, 1);
    RBTNode<Integer, String> n14
      = new RBTNode<Integer, String>(18, "eighteen", Colour.BLACK, n20, n21, 1, 1);
    RBTNode<Integer, String> n13
      = new RBTNode<Integer, String>(14, "fourteen", Colour.BLACK, n18, n19, 1, 1);
    RBTNode<Integer, String> n12
      = new RBTNode<Integer,String>(10, "ten", Colour.BLACK, n16, n17, 1, 1);
    RBTNode<Integer, String> n11 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n10 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n9 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n8 = new RBTNode<Integer, String>();
    RBTNode<Integer, String> n7
      = new RBTNode<Integer, String>(20, "twenty", Colour.BLACK,n14, n15, 3, 2);
    RBTNode<Integer, String> n6
      = new RBTNode<Integer, String>(12, "twelve", Colour.BLACK, n12, n13, 3, 2);
    RBTNode<Integer, String> n5
      = new RBTNode<Integer, String>(6, "six", Colour.BLACK, n10, n11, 1, 1);
    RBTNode<Integer, String> n4
      = new RBTNode<Integer, String>(2, "two", Colour.BLACK, n8, n9, 1, 1);
    RBTNode<Integer, String> n3
      = new RBTNode<Integer, String>(16, "sixteen", Colour.RED, n6, n7, 7, 3);
    RBTNode<Integer, String> n2
      = new RBTNode<Integer, String>(4, "four", Colour.BLACK, n4, n5, 3, 2);
    RBTNode<Integer, String> n1
      = new RBTNode<Integer, String>(8, "eight", Colour.BLACK, n2, n3, 11, 3);
    RBT<Integer, String> T = new RBT<Integer, String>(n1);
    return T;
   }
   
   @Test
   public void Test3d_1() {
     RBT<Integer, String> T = sampleTree10();
     testSuccess1(T, 2);
   }
   
   @Test
   public void Test3d_2() {
     RBT<Integer, String> T = sampleTree10();
     testSuccess2(T, 2);
   }
   
   @Test
   public void Test3d_3() {
     RBT<Integer, String> T = sampleTree10();
     testSuccess3(T, 2, 11);
   }
   
   // Subcase 3(e)
   
   RBT<Integer, String> sampleTree11() {
     RBTNode<Integer, String> n15 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n14 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n13 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n12 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n11 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n10 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n9 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n8 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n7
       = new RBTNode<Integer, String>(14, "fourteen", Colour.BLACK, n14, n15, 1, 1);
     RBTNode<Integer, String> n6
       = new RBTNode<Integer, String>(10, "ten", Colour.BLACK, n12, n13, 1, 1);
     RBTNode<Integer, String> n5
       = new RBTNode<Integer, String>(6, "six", Colour.BLACK, n10, n11, 1, 1);
     RBTNode<Integer, String> n4
       = new RBTNode<Integer, String>(2, "two", Colour.BLACK, n8, n9, 1, 1);
     RBTNode<Integer, String> n3
        = new RBTNode<Integer, String>(12, "twelve", Colour.BLACK, n6, n7, 3, 2);
     RBTNode<Integer, String> n2
       = new RBTNode<Integer, String>(4, "four", Colour.BLACK, n4, n5, 3, 2);
     RBTNode<Integer, String> n1
       = new RBTNode<Integer, String>(8, "eight", Colour.BLACK, n2, n3, 7, 3);
     RBT<Integer, String> T = new RBT<Integer, String>(n1);
     return T;
   }
   
   @Test
   public void Test3e_1() {
     RBT<Integer, String> T = sampleTree11();
     testSuccess1(T, 6);
   }
   
   @Test
   public void Test3e_2() {
     RBT<Integer, String> T = sampleTree11();
     testSuccess2(T, 6);
   }
   
   @Test
   public void Test3e_3() {
     RBT<Integer, String> T = sampleTree11();
     testSuccess3(T, 6, 7);
   }
   
   // Subcase 3(f)
   
   RBT<Integer, String> sampleTree12() {
     RBTNode<Integer, String> n19 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n18 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n17 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n16 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n15 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n14 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n13 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n12 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n11 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n10 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n9
       = new RBTNode<Integer, String>(6, "six", Colour.BLACK, n18, n19, 1, 1);
     RBTNode<Integer, String> n8
       = new RBTNode<Integer, String>(2, "two", Colour.BLACK, n16, n17, 1, 1);
     RBTNode<Integer, String> n7
       = new RBTNode<Integer, String>(18, "eighteen", Colour.BLACK, n14, n15, 1, 1);
     RBTNode<Integer, String> n6
       = new RBTNode<Integer, String>(14, "fourteen", Colour.BLACK, n12, n13, 1, 1);
     RBTNode<Integer, String> n5
       = new RBTNode<Integer, String>(10, "ten", Colour.BLACK, n10, n11, 1, 1);
     RBTNode<Integer, String> n4
       = new RBTNode<Integer, String>(4, "four", Colour.RED, n8, n9, 3, 2);
     RBTNode<Integer, String> n3
       = new RBTNode<Integer, String>(16, "sixteen", Colour.BLACK, n6, n7, 3, 2);
     RBTNode<Integer, String> n2
       = new RBTNode<Integer, String>(8, "eight", Colour.BLACK, n4, n5, 5, 2);
     RBTNode<Integer, String> n1
       = new RBTNode<Integer, String>(12, "twelve", Colour.BLACK, n2, n3, 9, 3);
     RBT<Integer, String> T = new RBT<Integer, String>(n1);
     return T;
   }
   
   @Test
   public void Test3f_1() {
     RBT<Integer, String> T = sampleTree12();
     testSuccess1(T, 14);
   }
   
   @Test
   public void Test3f_2() {
     RBT<Integer, String> T = sampleTree12();
     testSuccess2(T, 14);
   }
   
   @Test
   public void Test3f_3() {
     RBT<Integer, String> T = sampleTree12();
     testSuccess3(T, 14, 9);
   }
   
   // Subcase 3(g)
   
   RBT<Integer, String> sampleTree13() {
     RBTNode<Integer, String> n23 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n22 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n21 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n20 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n19 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n18 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n17 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n16 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n15
       = new RBTNode<Integer, String>(22, "twenty-two", Colour.BLACK, n22, n23, 1, 1);
     RBTNode<Integer, String> n14
       = new RBTNode<Integer, String>(18, "eighteen", Colour.BLACK, n20, n21, 1, 1);
     RBTNode<Integer, String> n13
       = new RBTNode<Integer, String>(14, "fourteen", Colour.BLACK, n18, n19, 1, 1);
     RBTNode<Integer, String> n12
       = new RBTNode<Integer, String>(10, "ten", Colour.BLACK, n16, n17, 1, 1);
     RBTNode<Integer, String> n11 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n10 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n9 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n8 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n7
       = new RBTNode<Integer, String>(20, "twenty", Colour.BLACK, n14, n15, 3, 2);
     RBTNode<Integer, String> n6
       = new RBTNode<Integer, String>(12, "twelve", Colour.BLACK, n12, n13, 3, 2);
     RBTNode<Integer, String> n5
       = new RBTNode<Integer, String>(6, "six", Colour.BLACK, n10, n11, 1, 1);
     RBTNode<Integer, String> n4
       = new RBTNode<Integer, String>(2, "two", Colour.BLACK, n8, n9, 1, 1);
     RBTNode<Integer, String> n3
       = new RBTNode<Integer, String>(16, "sixteen", Colour.RED, n6, n7, 7, 3);
     RBTNode<Integer, String> n2
       = new RBTNode<Integer, String>(4, "four", Colour.BLACK, n4, n5, 3, 2);
     RBTNode<Integer, String> n1
       = new RBTNode<Integer, String>(8, "eight", Colour.BLACK, n2, n3, 11, 3);
     RBT<Integer, String> T = new RBT<Integer, String>(n1);
     return T;
   }
   
   @Test
   public void Test3g_1() {
     RBT<Integer, String> T = sampleTree13();
     testSuccess1(T, 18);
   }
   
   @Test
   public void Test3g_2() {
     RBT<Integer, String> T = sampleTree13();
     testSuccess2(T, 18);
   }
   
   @Test
   public void Test3g_3() {
     RBT<Integer, String> T = sampleTree13();
     testSuccess3(T, 18, 11);
   }
   
   // Subcase 3(h)
   
   RBT<Integer, String> sampleTree14() {
     RBTNode<Integer, String> n19 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n18 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n17 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n16 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n15 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n14 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n13 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n12 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n11
       = new RBTNode<Integer, String>(10, "ten", Colour.BLACK, n18, n19, 1, 1);
     RBTNode<Integer, String> n10
       = new RBTNode<Integer, String>(6, "six", Colour.BLACK, n16, n17, 1, 1);
     RBTNode<Integer, String> n9 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n8 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n7
       = new RBTNode<Integer, String>(18, "eighteen", Colour.BLACK, n14, n15, 1, 1);
     RBTNode<Integer, String> n6
       = new RBTNode<Integer, String>(14, "fourteen", Colour.BLACK, n12, n13, 1, 1);
     RBTNode<Integer, String> n5
       = new RBTNode<Integer, String>(8, "eight", Colour.RED, n10, n11, 3, 2);
     RBTNode<Integer, String> n4
       = new RBTNode<Integer, String>(2, "two", Colour.BLACK, n8, n9, 1, 1);
     RBTNode<Integer, String> n3
       = new RBTNode<Integer, String>(16, "sixteen", Colour.BLACK, n6, n7, 3, 2);
     RBTNode<Integer, String> n2
       = new RBTNode<Integer, String>(4, "four", Colour.BLACK, n4, n5, 5, 2);
     RBTNode<Integer, String> n1
       = new RBTNode<Integer, String>(12, "twelve", Colour.BLACK, n2, n3, 9, 3);
     RBT<Integer, String> T = new RBT<Integer, String>(n1);
     return T;
   }
   
   @Test
   public void Test3h_1() {
     RBT<Integer, String> T = sampleTree14();
     testSuccess1(T, 18);
   }
   
   @Test
   public void Test3h_2() {
     RBT<Integer, String> T = sampleTree14();
     testSuccess2(T, 18);
   }
   
   @Test
   public void Test3h_3() {
     RBT<Integer, String> T = sampleTree14();
     testSuccess3(T, 18, 9);
   }
   
   // Subcase 3(i)
   
   RBT<Integer, String> sampleTree15() {
     RBTNode<Integer, String> n23 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n22 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n21 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n20 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n19 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n18 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n17 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n16 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n15 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n14 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n13 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n12 = new RBTNode<Integer, String>();
     RBTNode<Integer, String> n11
       = new RBTNode<Integer, String>(14, "fourteen", Colour.BLACK, n22, n23, 1, 1);
     RBTNode<Integer, String> n10
       = new RBTNode<Integer, String>(10, "ten", Colour.BLACK, n20, n21, 1, 1);
     RBTNode<Integer, String> n9
       = new RBTNode<Integer, String>(6, "six", Colour.BLACK, n18, n19, 1, 1);
     RBTNode<Integer, String> n8
       = new RBTNode<Integer, String>(2, "two", Colour.BLACK, n16, n17, 1, 1);
     RBTNode<Integer, String> n7
       = new RBTNode<Integer, String>(22, "twenty-two", Colour.BLACK, n14, n15, 1, 1);
     RBTNode<Integer, String> n6
       = new RBTNode<Integer, String>(18, "eighteen", Colour.BLACK, n12, n13, 1, 1);
     RBTNode<Integer, String> n5
       = new RBTNode<Integer, String>(12, "twelve", Colour.BLACK, n10, n11, 3, 2);
     RBTNode<Integer, String> n4
       = new RBTNode<Integer, String>(4, "four", Colour.BLACK, n8, n9, 3, 2);
     RBTNode<Integer, String> n3
       = new RBTNode<Integer, String>(20, "twenty", Colour.BLACK, n6, n7, 3, 2);
     RBTNode<Integer, String> n2
       = new RBTNode<Integer, String>(8, "eight", Colour.RED, n4, n5, 7, 3);
     RBTNode<Integer, String> n1
       = new RBTNode<Integer, String>(16, "sixteen", Colour.BLACK, n2, n3, 11, 3);
     RBT<Integer, String> T = new RBT<Integer, String>(n1);
     return T;
   }
   
   @Test
   public void Test3i_1() {
     RBT<Integer, String> T = sampleTree15();
     testSuccess1(T, 18);
   }
   
   @Test
   public void Test3i_2() {
     RBT<Integer, String> T = sampleTree15();
     testSuccess2(T, 18);
   }
   
   @Test
   public void Test3i_3() {
     RBT<Integer, String> T = sampleTree15();
     testSuccess3(T, 18, 11);
   }
   
   // Subcase 3(j)
   
   @Test
   public void Test3j_1() {
     RBT<Integer, String> T = sampleTree11();
     testSuccess1(T, 14);
   }
   
   @Test
   public void Test3j_2() {
     RBT<Integer, String> T = sampleTree11();
     testSuccess2(T, 14);
   }
   
   @Test
   public void Test3j_3() {
     RBT<Integer, String> T = sampleTree11();
     testSuccess3(T, 14, 7);
   }
 
 }