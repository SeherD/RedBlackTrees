package cpsc331.assignment2;

import cpsc331.assignment2.Colour;
import cpsc331.collections.ElementFoundException;
import java.util.NoSuchElementException;
import cpsc331.assignment2.RBTNode;

/*
 *
 * Provides a Red-Black Tree that can be used to implement either
 * a SortedSet or a SortedMap
 *
 */

class RBT<K extends Comparable<K>, V> {

  // Data Fields
  
  private RBTNode<K, V> root;    // The root of this red-black tree

  // Constructor of a Red-Black Tree storing an empty set or map

  RBT() {

    RBTNode<K, V> root = new RBTNode<K, V>();
    this.root = root;

  }

  // Constructor of a Red-Back Tree with a given node as root:
  // For use in testing of Red-Black Trees

  RBT(RBTNode<K, V> root) {

    assert root != null : "Root cannot be null.";
    this.root = root;

  }

  // Returns the root of this tree - useful for testing

  RBTNode<K, V> root() {
    return this.root;
  }

  // Inspector method to report the size of the set or map represented
  // by this Red-Black tree

  int size() {

    assert root != null : "Root cannot be null.";
    return this.root.size();

  }

  // Reports whether this set or map includes a node storing the
  // given key if the input key is not null. A NullPointerException
  // is thrown otherwise.

  boolean includes(K key) {

    assert root != null : "Root cannot be null.";
    RBTNode<K, V> node = this.root.search(key);
    return !node.isNil();

  }

  // Returns the value corresponding to a given key is in this map.
  // A NullPointerException is thrown if the input key is null; a
  // NoSuchElementException is thrown, instead, if the input key is not null
  // but there is not key-value pair with the input key stored in
  // this map.

  V get(K key) throws NullPointerException, NoSuchElementException {

    if (key != null) {
      RBTNode<K, V> node = this.root.search(key);
      if (node.isNil()) {
        throw new NoSuchElementException("Key not found.");
      } else {
        return node.value();
      }
    } else {
      throw new NullPointerException("Input key cannot be null.");
    }

  }

  // Inserts a node with the given key and value into this
  // red black tree, if the key is not null and not already
  // included.
  //
  // A NullPointerException is thrown if the key is null;
  // an ElementFoundException is thrown if the key is stored in the
  // tree already.

  void insert(K key, V  value) throws NullPointerException, 
                                      ElementFoundException {

    if (key != null) {
      this.root.insert(key, value);
    } else {
      throw new NullPointerException("Input key cannot be null.");
    }
  
  }

  // Updates the value for the given key with the given value,
  // if the input key is not null and already stored in this
  // tree. A NullPointerExcption is thrown if the input key
  // is null. A NoSuchElementException is thrown (and the tree
  // is not changed) if the key is not null but is also not
  // stored in the tree already.

  void change(K key, V value) throws NullPointerException,
                                     NoSuchElementException {

    if (key != null ) {
      RBTNode<K, V> node = this.root.search(key);
      if (node.isNil()) {
        throw new NoSuchElementException("This key was not found.");
      } else {
        node.setValue(value);
      };
    } else {
      throw new NullPointerException("Input key cannot be null.");
    }

  }

  // Removes the node with the given key, if this key is
  // not null and stored in the tree. A NullPointerException
  // is thrown if the input key is null, and a NoSuchElementException
  // is thrown if this input key is null but not stored in
  // the tree.

  void delete(K key) throws NullPointerException, NoSuchElementException {

    if (key != null ) {
      this.root.delete(key);
    } else {
      throw new NullPointerException("Input key cannot be null.");
    }

  }

}
