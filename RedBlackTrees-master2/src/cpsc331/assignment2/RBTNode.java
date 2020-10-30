package cpsc331.assignment2;

import cpsc331.assignment2.Colour;
import cpsc331.collections.ElementFoundException;

import java.util.NoSuchElementException;
//import cpsc331.collections.ElementFoundException;
/*
 * Provides a node in a red-black tree.
 *
 *
 */
class RBTNode<K extends Comparable<K>, V> {

  // Data Fields

  private boolean isNil;          // True if and only if this is a NIL node
  private K key;                  // The key stored at this node
  private V value;                // The value stored at this node
  private RBTNode<K, V> left;     // The left child of this node
  private RBTNode<K, V> right;    // The right child of this node
  private RBTNode<K, V> parent;   // The parent of this node
  private int setSize;            // The size of the set represented by the
                                  // subtree with this node as root
  private Colour colour;          // The colour of this node
  private int blackHeight;        // The blackheight of this node


  // Constructor of a Nil Node

  RBTNode() {

    this.isNil = true;
    this.key = null;
    this.value = null;
    this.left = null;
    this.right = null;
    this.parent = null;
    this.setSize = 0;
    this.colour = Colour.BLACK;
    this.blackHeight = 0;

  }
  
  // Constructor of an Internal Node
 
  RBTNode(K key, V value, Colour colour) {

    assert key != null;
    assert (colour == Colour.RED) || (colour == Colour.BLACK);
    
    this.isNil = false;
    this.key = key;
    this.value = value;
    this.left = new RBTNode<K, V>();
    this.left.setParent(this);
    this.right = new RBTNode<K, V>();
    this.right.setParent(this);
    this.parent = null;
    this.setSize = 1;
    this.colour = colour;
    this.blackHeight = 1;

  }
  
  // Constructor used for Testing
  
  RBTNode(K key, V value, Colour c,
          RBTNode<K, V> left, RBTNode<K, V> right,
          int size, int bh) {
          
    assert key != null;
    assert ((c == Colour.RED) || (c == Colour.BLACK));
    
    this.isNil = false;
    this.key = key;
    this.value = value;
    this.colour = c;
    this.left = left;
    if (left != null) {
      left.setParent(this);
    };
    this.right = right;
    if (right != null) {
      right.setParent(this);
    };
    this.parent = null;
    this.setSize = size;
    this.blackHeight = bh;
          
  }

  // Inspector method to report whether this is a NIL node

  boolean isNil() {

    return this.isNil;

  }

  // Inspector method to report the key at this node

  K key() {

    return this.key;

  }

  // Inspector method to report the value at this node

  V value() {

    return this.value;

  }

  // Inspector method to report the left child of this node

  RBTNode<K, V> left() {

    return this.left;

  }

  // Inspector method to report the right child of this node

  RBTNode<K, V> right() {

    return this.right;

  }

  // Inspector method to report the parent of this node

  RBTNode<K, V> parent() {

    return this.parent;

  }

  // Inspector method to report the size of the set represented by
  // the subtree with this node as root
  
  int size() {

    return this.setSize;

  }

  // Inspector method to report the colour of this node

  Colour colour() {

    return this.colour;

  }

  // Inspector method to report the blackHeight of this node
  
  int blackHeight() {

    return this.blackHeight;

  }

  // Mutator method the change the key at this node

  void setKey(K key) {
  
    assert !this.isNil : "Cannot set a key for a NIL node.";
    assert key != null : "Key cannot be null.";

    this.key = key;

  }

  // Mutator method to change the value at this node

  void setValue(V value) {
  
    assert !this.isNil : "Cannot set a value for a NIL node.";

    this.value = value;

  }

  // Mutator method to update the left child of this node

  void setLeft(RBTNode<K, V> left) {
  
    assert !this.isNil : "Cannot set the left child of a NIL node.";

    this.left = left;

  }

  // Mutator method to update the right child of this node

  void setRight(RBTNode<K, V> right) {
  
    assert !this.isNil : "Cannot set the right child of a NIL node.";

    this.right = right;

  }

  // Mutator method to update the parent of this node

  void setParent(RBTNode<K, V> parent) {

    this.parent = parent;

  }

  // Mutator method to update the size of the set represented by the
  // subtree with this node as root
  
  void setSize(int size) {
  
    assert !this.isNil : "Cannot set the size for a NIL node.";

    this.setSize = size;

  }

  // Mutator method to change the colour of this node

  void setColour(Colour colour) {
  
    assert (!this.isNil) || ((colour == Colour.BLACK)
       || (colour == Colour.DOUBLEBLACK)) :
       "Colour of NIL node must be black or double-black.";

    this.colour = colour;

  }

  // Mutator method to update the blackHeight of this node

  void setBlackHeight(int blackHeight) {
  
    assert !this.isNil : "Cannot set the blackheight of a NIL node.";

    this.blackHeight = blackHeight;

  }

  // NOTE: Many methods below this line are incomplete and must be filled in!

  // Searches for a node with the given non-null key in the subtree with
  // this node as root.
  //
  // If there is no node in the subtree with this node as root storing
  // this key, then the NIL node that would be replaced by a new node with
  //  this key, at the beginning of an insertion operation, should be returned
  // instead.
  
  RBTNode<K, V> search(K key) {
	  assert key != null : "Input key cannot be null."; 
	  RBTNode<K,V> x=this;
	  if(this.isNil) {
	    throw new NoSuchElementException();
	  }
	  while(!x.isNil) {
	   int cmp = key.compareTo(x.key);
	   if      (cmp < 0) x = x.left();
	   else if (cmp > 0) x = x.right();
	   else  return x;
	     
	   } 
	   if(x.key!=key)
	     throw new NoSuchElementException();
	    return null;

  }
 
  // Returns the root of the subtree obtained by performing a left rotation
  // at this node

  private RBTNode<K, V> leftRotate() {
    assert !this.isNil : "Left rotation at a NIL node is not permitted.";
    assert ((this.right() != null) && !(this.right().isNil)) :
      "Left rotation cannot be performed at node with NIL right child.";
    
    RBTNode<K, V> subtree1 = this.left;
    RBTNode<K, V> subtree2 = this.right.left();
    RBTNode<K, V> subtree3 = this.right.right();

    K rootKey = this.key;
    V rootValue = this.value;
    Colour colour = this.colour();
    
    this.setKey(this.right.key);
    this.setValue(this.right.value);
    this.setColour(this.right.colour());
    
    this.right.setKey(rootKey);
    this.right.setValue(rootValue);
    this.right.setColour(colour);
    
    this.setLeft(this.right);
    
    this.left.setLeft(subtree1);
    this.left.left.parent = this.left;
    this.left.setRight(subtree2);
    this.left.right.parent = this.left;
    this.right = subtree3;
    this.right.parent = this;
    
    this.left.setSize(this.left.left.size() + this.left.right.size() + 1);
    this.setSize(this.left.size() + this.right.size() + 1);
    
    return this;

  }

  // Returns the root of the subtree obtained by performing a right rotation
  // at this node


  private RBTNode<K, V> rightRotate() {
    assert !this.isNil : "Right rotation at a NIL node is not permitted.";
    assert ((this.left() != null) && !(this.left().isNil)) :
      "Right rotation cannot be performed at node with NIL left child.";
    
    RBTNode<K,V> subtree1 = this.left.left();
    RBTNode<K,V> subtree2 = this.left.right();
    RBTNode<K,V> subtree3 = this.right;

    K rootKey = this.key;
    V rootValue = this.value;
    Colour colour = this.colour();
    
    this.key = this.left.key();
    this.value = this.left.value();
    this.setColour(this.left.colour());
    
    this.left.setKey(rootKey);
    this.left.setValue(rootValue);
    this.left.setColour(colour);
    
    this.right = this.left;


    this.left = subtree1;
    this.left.parent = this;
    this.right.setLeft(subtree2);
    this.right.left.parent = this.right;
    this.right.setRight(subtree3);
    this.right.right.parent = this.right;
    
    this.right.setSize(this.right.left.size() + this.right.right.size() + 1);
    this.setSize(this.left.size() + this.right.size() + 1);

    return this;

  }

  // Performs the beginning of an insertion operation for
  // a binary search tree - inserting a new RED node into the
  // the tree with the required value and correcting the sizes
  // of all other nodes that the roots of subtrees whose set
  // sizes have changed. Returns a reference to the new node
  // that has been inserted - throwing an ElementFoundException and
  // leaving the tree unchanged if the key was in the tree
  // already

  private RBTNode<K, V> regular_insert(K key, V value)
                                       throws ElementFoundException {

    assert key != null : "Input key cannot be null.";
    
    if(this.isNil){
      this.isNil = false;
      this.setColour(Colour.RED);
      this.setKey(key);
      this.setValue(value);

      this.setLeft(new RBTNode<K,V>());
      this.setRight(new RBTNode<K,V>());

      this.left.setParent(this);
      this.right.setParent(this);
      this.setSize(1);
      this.setBlackHeight(1);
      return this;
    }
    else if(this.key() == key){
      throw new ElementFoundException("");
  
    }
    else if(key.compareTo(this.key) == - 1){
      this.setSize(this.size() + 1);
      return this.left.regular_insert(key, value);
    }
    else {
      this.setSize(this.size() + 1);
      return this.right.regular_insert(key, value);
    }


  }

  // The next eight methods each implement one of the
  // cases that might arise when a red node has a red parent
  // during an insertion.
  //
  // - The input is a reference to the GRANDPARENT of the red
  //   node that has a reference to a red parent.
  //
  // - The value returned should be the root of the subtree
  //   that replaces the subtree with the GRANDPARENT as root
  //   after the adjustment.
  //
  // In the description of cases that follows, z is the red
  // node that has a red parent.

  // Implements the adjustment for an insertion in case 1(a):
  // - z is a left child
  // - the parent of z is a left child
  // - the sibling of the parent of z is red

  private RBTNode<K, V> insert_case_1a(RBTNode<K, V> grandparent) {
  
    assert grandparent.left != null : "Parent cannot be null.";
    assert !(grandparent.left).isNil : "Parent cannot be NIL.";
    assert (grandparent.left).colour == Colour.RED : "Parent must be red.";
    assert grandparent.right != null : "Sibling of parent cannot be null.";
    assert !(grandparent.right).isNil : "Sibling of parent cannot be NIL.";
    assert (grandparent.right).colour == Colour.RED :
      "Sibling of parent must be red.";
    assert (grandparent.left).left != null : "z cannot be null.";
    assert !((grandparent.left).left).isNil : "z cannot be NIL.";
    assert ((grandparent.left).left).colour == Colour.RED : "z must be red.";

    grandparent.left.setColour(Colour.BLACK);
    grandparent.right.setColour(Colour.BLACK);
    grandparent.setColour(Colour.RED);
    grandparent.setBlackHeight(blackHeight + 1);
    
    return grandparent;

  }

  // Implements the adjustment for an insertion in case 1(b):
  // - z is a right child
  // - the parent of z is a left child
  // - the sibling of the parent of z is red

  private RBTNode<K, V> insert_case_1b(RBTNode<K, V> grandparent) {
  
    assert grandparent.left != null : "Parent cannot be null.";
    assert !(grandparent.left).isNil : "Parent  cannot be NIL.";
    assert (grandparent.left).colour == Colour.RED : "Parent must be red.";
    assert grandparent.right != null : "Sibling of parent cannot be null.";
    assert !(grandparent.right).isNil : "Sibling of parent cannot be NIL.";
    assert (grandparent.right).colour == Colour.RED :
      "Sibling of parent must be red.";
    assert (grandparent.left).right != null : "z cannot be null.";
    assert !((grandparent.left).right).isNil : "z cannot be NIL.";
    assert ((grandparent.left).right).colour == Colour.RED : "z must be red.";

    grandparent.left.setColour(Colour.BLACK);
    grandparent.right.setColour(Colour.BLACK);
    grandparent.setColour(Colour.RED);
    grandparent.setBlackHeight(blackHeight + 1);
    
    return grandparent;

  }

  // Implements the adjustment for an insertion in case 2:
  // - z is a left child
  // - the parent of z is a left child
  // - the sibling of the parent of z is black

  private RBTNode<K, V> insert_case_2(RBTNode<K, V> grandparent) {
  
    assert grandparent.left != null : "Parent cannot be null.";
    assert !(grandparent.left).isNil : "Parent cannot be NIL.";
    assert (grandparent.left).colour == Colour.RED : "Parent must be red.";
    assert grandparent.right != null : "Sibling of parent cannot be null.";
    assert (grandparent.right).colour == Colour.BLACK :
      "Sibling of parent must be black.";
    assert (grandparent.left).left != null : "z cannot be null.";
    assert !((grandparent.left).left).isNil : "z cannot be NIL.";
    assert ((grandparent.left).left).colour == Colour.RED : "z must be red.";

    grandparent.rightRotate();
    grandparent.setColour(Colour.BLACK);
    grandparent.right.setColour(Colour.RED);

    return grandparent;

  }

  // Implements the adjustment for an insertion in case 3:
  // - z is a right child
  // - the parent of z is a left child
  // - the sibling of the parent of z is black

  private RBTNode<K, V> insert_case_3(RBTNode<K, V> grandparent) {
  
    assert grandparent.left != null : "Parent cannot be null.";
    assert !(grandparent.left).isNil : "Parent cannot be NIL.";
    assert (grandparent.left).colour == Colour.RED : "Parent must be red.";
    assert grandparent.right != null : "Sibling of parent cannot be null.";
    assert (grandparent.right).colour == Colour.BLACK :
      "Sibling of parent must be black.";
    assert (grandparent.left).right != null : "z cannot be null.";
    assert !((grandparent.left).right).isNil : "z cannot be NIL.";
    assert ((grandparent.left).right).colour == Colour.RED : "z must be red.";
    
    grandparent.left.leftRotate();
    grandparent.rightRotate();
    grandparent.setColour(Colour.BLACK);
    grandparent.right.setColour(Colour.RED);

    return grandparent;

  }

  // Implements the adjustment for an insertion in case 4(a):
  // - z is a right child
  // - the parent of z is a right child
  // - the sibling of the parent of z is red

  private RBTNode<K, V> insert_case_4a(RBTNode<K, V> grandparent) {
  
    assert grandparent.right != null : "Parent cannot be null.";
    assert !(grandparent.right).isNil : "Parent cannot be NIL.";
    assert (grandparent.right).colour == Colour.RED : "Parent must be red.";
    assert grandparent.left != null : "Sibling of parent cannot be null.";
    assert !(grandparent.left).isNil : "Sibling of parent cannot be NIL.";
    assert (grandparent.left).colour == Colour.RED :
      "Sibling of parent must be red.";
    assert (grandparent.right).right != null : "z cannot be null.";
    assert !((grandparent.right).right).isNil : "z cannot be NIL.";
    assert ((grandparent.right).right).colour == Colour.RED : "z must be red.";

    grandparent.left.setColour(Colour.BLACK);
    grandparent.right.setColour(Colour.BLACK);
    grandparent.setColour(Colour.RED);
    grandparent.setBlackHeight(blackHeight + 1);
    return grandparent;

  }

  // Implements the adjustment for an insertion in case 4(b):
  // - z is a left child
  // - the parent of z is a right child
  // - the sibling of the parent of z is red

  private RBTNode<K, V> insert_case_4b(RBTNode<K, V> grandparent) {
  
    assert grandparent.right != null : "Parent cannot be null.";
    assert !(grandparent.right).isNil : "Parent cannot be NIL.";
    assert (grandparent.right).colour == Colour.RED : "Parent must be red.";
    assert grandparent.left != null : "Sibling of parent cannot be null.";
    assert !(grandparent.left).isNil : "Sibling of parent cannot be NIL.";
    assert (grandparent.left).colour == Colour.RED :
      "Sibling of parent must be red.";
    assert (grandparent.right).left != null : "z cannot be null.";
    assert !((grandparent.right).left).isNil : "z cannot be NIL.";
    assert ((grandparent.right).left).colour == Colour.RED : "z must be red.";

    // Details of this method must be replaced!
    grandparent.left.setColour(Colour.BLACK);
    grandparent.right.setColour(Colour.BLACK);
    grandparent.setColour(Colour.RED);
    grandparent.setBlackHeight(blackHeight + 1);
    return grandparent;

  }

  // Implements the adjustment for an insertion in case 5:
  // - z is a right child
  // - the parent of z is a right child
  // - the sibling of the parent of z is black

  private RBTNode<K, V> insert_case_5(RBTNode<K, V> grandparent) {
  
    assert grandparent.right != null : "Parent cannot be null.";
    assert !(grandparent.right).isNil : "Parent cannot be NIL.";
    assert (grandparent.right).colour == Colour.RED : "Parent must be red.";
    assert grandparent.left != null : "Sibling of parent cannot be null.";
    assert (grandparent.left).colour == Colour.BLACK :
      "Sibling of parent must be black.";
    assert (grandparent.right).right != null : "z cannot be null.";
    assert !((grandparent.right).right).isNil : "z cannot be NIL.";
    assert ((grandparent.right).right).colour == Colour.RED : "z must be red.";

    grandparent.leftRotate();
    grandparent.setColour(Colour.BLACK);
    grandparent.left.setColour(Colour.RED);
    return grandparent;

  }

  // Implements the adjustment for an insertion in case 6:
  // - z is a left child
  // - the parent of z is a right child
  // - the sibling of the parent of z is black

  private RBTNode<K, V> insert_case_6(RBTNode<K, V> grandparent) {
  
    assert grandparent.right != null : "Parent cannot be null.";
    assert !(grandparent.right).isNil : "Parent cannot be NIL.";
    assert (grandparent.right).colour == Colour.RED : "Parent must be red.";
    assert grandparent.left != null : "Sibling of parent cannot be null.";
    assert (grandparent.left).colour == Colour.BLACK :
      "Sibling of parent must be black.";
    assert (grandparent.right).left != null : "z cannot be null.";
    assert !((grandparent.right).left).isNil : "z cannot be NIL.";
    assert ((grandparent.right).left).colour == Colour.RED : "z must be red.";

    grandparent.right.rightRotate();
    grandparent.leftRotate();
    grandparent.setColour(Colour.BLACK);
    grandparent.left.setColour(Colour.RED);
    return grandparent;

  }

  // Method for an insertion of a node with a given key and value.
  // This should throw an ElementFoundException, leaving the tree unchanged,
  // if the given key is stored at a node in this tree already. 

  void insert(K key, V value) throws ElementFoundException {
  
    assert key != null : "Input key cannot be null!";

    try{
      RBTNode<K, V> insertedNode = regular_insert(key, value);
      if(insertedNode.parent != null && insertedNode.parent.colour() == Colour.RED){
        RBTNode<K, V> z =  insertedNode;
        while(z.parent != null && z.parent.colour() == Colour.RED){
          if(z.parent.left() == z && z.parent.parent.left() == z.parent() && z.parent.parent.right.colour() == Colour.RED){
            insert_case_1a(z.parent.parent);
            z = z.parent.parent;
          }
          else if(z.parent.right() == z && z.parent.parent.left() == z.parent && z.parent.parent.right.colour() == Colour.RED){
            insert_case_1b(z.parent.parent);
            z = z.parent.parent;
          }
          else if(z.parent.left() == z && z.parent.parent.left() == z.parent() && z.parent.parent.right.colour() == Colour.BLACK){
            insert_case_2(z.parent.parent);
            z = z.parent;
          }
          else if(z.parent.right() == z && z.parent.parent.left() == z.parent() && z.parent.parent.right.colour() == Colour.BLACK){
            insert_case_3(z.parent.parent);
            z = z.parent;
          }
          else if(z.parent.right() == z && z.parent.parent.right() == z.parent() && z.parent.parent.left.colour() == Colour.RED){
            insert_case_4a(z.parent.parent);
            z = z.parent.parent;
          }
          else if(z.parent.left() == z && z.parent.parent.right() == z.parent && z.parent.parent.left.colour() == Colour.RED){
            insert_case_4b(z.parent.parent);
            z = z.parent.parent;
          }
          else if(z.parent.right() == z && z.parent.parent.right() == z.parent() && z.parent.parent.left.colour() == Colour.BLACK){
            insert_case_5(z.parent.parent);
            z = z.parent;
          }
          else if(z.parent.left() == z && z.parent.parent.right() == z.parent && z.parent.parent.left.colour() == Colour.BLACK){
            insert_case_6(z.parent.parent);
            z = z.parent;
          }
          if(z.parent() == null && z.colour() == Colour.RED) {
        	  z.setColour(Colour.BLACK);
          }
        }
      }
      if(insertedNode.parent == null && insertedNode.colour() == colour.RED) {
    	  insertedNode.setColour(Colour.BLACK);
      }
    }
    catch(ElementFoundException e){
      throw e;
    }
    

  }

  // Implements the beginning of a deletion of a node - adjusting
  // the sizes of all nodes whose subtrees now represent sets
  // whose sizes have changed, and returning the node (called x
  // in later comments) that was promoted when a node was removed
  // from this tree. A NoSuchElementException should be thrown, and
  // the tree should be unchanged, if the input key was not stored
  // at any node in this tree before this method was called.

  private RBTNode<K, V> regular_delete(K key)
                                       throws NoSuchElementException {
  
    assert key != null : "Input key cannot be null.";

    // Remaining etails of this method must be replaced!
    return null;

  }

  // The next ten methods each implement one of the adjustments
  // that might be required when the tree includes a double-black
  // node that is not at the root.
  //
  //  - The input is a reference to the double-black node.
  //
  //  - In the descriptions that follow, x is the double-black
  //    root that is not at the root.

  // Implements the adjustment needed in case 3(a):
  // - x is a left child
  // - sibling of x is black
  // - right child of the sibling of x is red
  // A reference to x (whose colour has changed) should be returned
  // as output.

  private RBTNode<K, V> delete_case_3a(RBTNode<K, V> x) {
  
    RBTNode<K, V> beta = x.parent();
    
    assert (x.colour == Colour.DOUBLEBLACK) : "x must be double-black.";
    assert (x == beta.left()): "x must be a left child.";
    assert (beta.right().colour == Colour.BLACK) : "Sibling of x must be black.";
    assert ((beta.right()).right().colour == Colour.RED) :
      "Right child of sibling of x must be red.";

    // Additional details of this method must be replaced!
    return null;

  }

  // Implements the adjustment needed in case 3(b):
  // - x is a left child
  // - parent of x is red
  // - sibling of x is black
  // - both children of the siblings of x are black
  // A reference to the red-black node that results from this
  // change should be returned as output.

  private RBTNode<K, V> delete_case_3b(RBTNode<K, V> x) {
  
    RBTNode<K, V> beta = x.parent();
    
    assert (x.colour == Colour.DOUBLEBLACK) : "x must be double-black.";
    assert (x == beta.left()): "x must be a left child.";
    assert (beta.colour == Colour.RED) : "Parent of x must be red.";
    assert (beta.right().colour == Colour.BLACK) : "Sibling of x must be black.";
    assert ((beta.right()).left().colour == Colour.BLACK) :
      "Left child of sibling of x must be black.";
    assert ((beta.right()).right().colour == Colour.BLACK) :
      "Right child of sibling of x must be black.";

    // Details of this method must be replaced!
    return null;

  }

  // Implements the adjustment needed in case 3(c):
  // - x is a left child
  // - sibling of x is black
  // - left child of sibling of x is red
  // - right child of sibling of x is black
  // A reference to the double-black node that results from
  // this change should be returned as output.

  private RBTNode<K, V> delete_case_3c(RBTNode<K, V> x) {
  
    RBTNode<K, V> beta = x.parent();
    
    assert (x.colour == Colour.DOUBLEBLACK) : "x must be double-black.";
    assert (x == beta.left()): "x must be a left child.";
    assert ((beta.right()).colour == Colour.BLACK) : "Sibling of x must be black.";
    assert ((beta.right()).left().colour == Colour.RED) :
      "Left child of sibling of x must be red.";
    assert ((beta.right()).right().colour == Colour.BLACK) :
      "Right child of sibling of x must be black.";

    // Details of this method must be replaced!
    return null;

  }

  // Implements the adjustment needed in case 3(d):
  // - x is a left child
  // - parent of x is black
  // - sibling of x is red
  // - both children of the sibling of x are black
  // A reference to the double-black node that results from
  // this change should be returned as output.

  private RBTNode<K, V> delete_case_3d(RBTNode<K, V> x) {
  
    RBTNode<K, V> beta = x.parent();
    
    assert (x.colour == Colour.DOUBLEBLACK) : "x must be double-black.";
    assert (x == beta.left()): "x must be a left child.";
    assert (beta.colour == Colour.BLACK) : "Parent of x must be black.";
    assert (beta.right().colour == Colour.RED) : "Sibling of x must be red.";
    assert ((beta.right()).left().colour == Colour.BLACK) :
      "Left child of sibling of x must be black.";
    assert ((beta.right()).right().colour == Colour.BLACK) :
      "Right child of sibling of x must be black.";

    // Details of this method must be replaced!
    return null;

  }

  // Implements the adjustment needed in case 3(e):
  // - x is a left child
  // - parent of x is black
  // - sibling of x is black
  // - both children of the sibling of x are black
  // A reference to the double-black node that results from
  // this change should be returned as output.

  private RBTNode<K, V> delete_case_3e(RBTNode<K, V> x) {
  
    RBTNode<K, V> beta = x.parent();
    
    assert (x.colour == Colour.DOUBLEBLACK) : "x must be double-black.";
    assert (x == beta.left()): "x must be a left child.";
    assert (beta.colour == Colour.BLACK) : "Parent of x must be black.";
    assert (beta.right().colour == Colour.BLACK) : "Sibling of x must be black.";
    assert ((beta.right()).left().colour == Colour.BLACK) :
      "Left child of sibling of x must be black.";
    assert ((beta.right()).right().colour == Colour.BLACK) :
      "Right child of sibling of x must be black.";

    // Details of this method must be replaced!
    return null;

  }

  // Implements the adjustment needed in case 3(f):
  // - x is a right child
  // - sibling of x is black
  // - left child of the sibling of x is red
  // A reference to x (whose colour has changed) should be returned
  // as output.

  private RBTNode<K, V> delete_case_3f(RBTNode<K, V> x) {
  
    RBTNode<K, V> beta = x.parent();
    
    assert (x.colour == Colour.DOUBLEBLACK) : "x must be double-black.";
    assert (x == beta.right()): "x must be a right child.";
    assert (beta.left().colour == Colour.BLACK) : "Sibling of x must be black.";
    assert ((beta.left()).left().colour == Colour.RED) :
      "Left child of sibling of x must be red.";

    // Details of this method must be replaced!
    return null;

  }

  // Implements the adjustment needed in case 3(g):
  // - x is a right child
  // - parent of x is red
  // - sibling of x is black
  // - both children of the sibling of x are black
  // A reference to the red-black node that results from
  // this change should be returned as output.

  private RBTNode<K, V> delete_case_3g(RBTNode<K, V> x) {
  
    RBTNode<K, V> beta = x.parent();
    
    assert (x.colour == Colour.DOUBLEBLACK) : "x must be double-black.";
    assert (x == beta.right()): "x must be a right child.";
    assert (beta.colour == Colour.RED) : "Parent of x must be red.";
    assert (beta.left().colour == Colour.BLACK) : "Sibling of x must be black.";
    assert ((beta.left()).left().colour == Colour.BLACK) :
      "Left child of sibling of x must be black.";
    assert ((beta.left()).right().colour == Colour.BLACK) :
      "Right child of sibling of x must be black.";

    // Details of this method must be replaced!
    return null;

  }

  // Implements the adjustment needed in case 3(h):
  // - x is a right child
  // - sibling of x is black
  // - left child of the sibling of x is black
  // - right child of the sibling of x is red
  // A reference to the double-black node that results from
  // this change should be returned as output.

  private RBTNode<K, V> delete_case_3h(RBTNode<K, V> x) {
  
    RBTNode<K, V> beta = x.parent();
    
    assert (x.colour == Colour.DOUBLEBLACK) : "x must be double-black.";
    assert (x == beta.right()): "x must be a right child.";
    assert (beta.left().colour == Colour.BLACK) : "Sibling of x must be black.";
    assert ((beta.left()).left().colour == Colour.BLACK) :
      "Left child of sibling of x must be black.";
    assert ((beta.left()).right().colour == Colour.RED) :
      "Right child of sibling of x must be red.";

    // Details of this method must be replaced!
    return null;

  }

  // Implements the adjustment needed in case 3(i):
  // - x is a right child
  // - parent of x is black
  // - sibling of x is red
  // - both children of the sibling of x are black
  // A reference to the double-black node that results from
  // this change should be returned as output.

  private RBTNode<K, V> delete_case_3i(RBTNode<K, V> x) {
  
    RBTNode<K, V> beta = x.parent();
    
    assert (x.colour == Colour.DOUBLEBLACK) : "x must be double-black.";
    assert (x == beta.right()): "x must be a right child.";
    assert (beta.colour == Colour.BLACK) : "Parent of x must be black.";
    assert (beta.left().colour == Colour.RED) : "Sibling of x must be red.";
    assert ((beta.left()).left().colour == Colour.BLACK) :
      "Left child of sibling of x must be black.";
    assert ((beta.left()).right().colour == Colour.BLACK) :
      "Right child of sibling of x must be black.";

    // Details of this method must be replaced!
    return null;

  }

  // Implements the adjustment needed in case 3(j):
  // - x is a right child
  // - parent of x is black
  // - sibling of x is black
  // - both children of the sibling of x are black
  // A reference to the double-black node that results from
  // this change should be returned as output.

  private RBTNode<K, V> delete_case_3j(RBTNode<K, V> x) {
  
    RBTNode<K, V> beta = x.parent();
    
    assert (x.colour == Colour.DOUBLEBLACK) : "x must be double-black.";
    assert (x == beta.right()): "x must be a right child.";
    assert (beta.colour == Colour.BLACK) : "Parent of x must be black.";
    assert (beta.left().colour == Colour.BLACK) : "Sibling of x must be black.";
    assert ((beta.left()).left().colour == Colour.BLACK) :
      "Left child of sibling of x must be black.";
    assert ((beta.left()).right().colour == Colour.BLACK) :
      "Right child of sibling of x must be black.";

    // Details of this method must be replaced!
    return null;

  }

  // Method for the deletion of a node with a given key.
  // This should throw a NoSuchElementException, leaving the
  // tree unchanged, if there was no node storing the
  // given tree before the operation was performed.

  void delete(K key) throws NoSuchElementException {
  
    assert key != null : "Input key cannot be null.";

    // Remaining details of this method must be replaced!

  }
  
}
