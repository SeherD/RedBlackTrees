package cpsc331.assignment2;

import cpsc331.collections.Dictionary;
import cpsc331.collections.ElementFoundException;
import java.util.NoSuchElementException;

/**
 *
 * Provides a Red-Black Tree - based implementation of a Dictionary.
 * <br />
 *
 * <p>
 *  <strong>Class Invariant:</strong>
 * </p>
 * <ol style="list-style-type: lower-alpha">
 * <li> A finite map from keys of type&nbsp;K to
 *      values of type&nbsp;V is represented. </li>
 * <li> There is a total order on the set K of possible keys. </li>
 * <li> None of the keys included in this map are null. </li>
 * </ol>
 *
 */

public class RBTMap<K extends Comparable<K>, V>
             implements Dictionary<K, V> {

  // Data Fields
  
  private RBT<K, V> T;  // The Red-Black Tree representing this Dictionary

 /**
 *
 * Constructor for an empty Dictionary
 * <br />
 *
 * <p>
 *  <strong>Precondition:</strong> None
 * </p>
 *
 * <p>
 *  <strong>Postcondition:</strong>
 * </p>
 * <ol style="list-style-type: lower-alpha">
 * <li> The class invariant is satisfied. </li>
 * <li> This Dictionary is empty. </li>
 * </ol>
 *
 */

  public RBTMap() {

    // These details must be changed!
    this.T = null;

  }

 /**
 *
 * Reports the number of keys for which values are defined.
 * <br />
 *
 * <p>
 *  <strong>Precondition:</strong>
 * </p>
 * <ol style="list-style-type: lower-alpha">
 * <li> The class invariant is satisfied. </li>
 * </ol><br />
 * <p>
 *  <strong>Postcondition:</strong>
 * </p>
 * <ol style="list-style-type: lower-alpha">
 * <li> The class invariant is satisfied. </li>
 * <li> The number of keys for which values are defined is returned
 *      as output. </li>
 * <li> The represented Dictionary has not been changed. </li>
 * </ol>
 *
 * @return the number of keys for which values are defined.
 *
 */

 public int size() {

    // These details must be changed!
    return 0;

  }
 
  /**
 *
 * Gets the value corresponding to a given key in this map.
 * <br />
 *
 * <p>
 *  <strong>Precondition:</strong>
 * </p>
 * <ol style="list-style-type: lower-alpha">
 * <li> The class invariant is satisfied. </li>
 * <li> A key of type K is supplied as input. </li>
 * </ol>
 * <p>
 *  <strong>Postcondition:</strong>
 * </p>
 * <ol style="list-style-type: lower-alpha">
 * <li> The class invariant is satisfied. </li>
 * <li> This map has not been changed. </li>
 * <li> If the input key is null then a NullPointerException is thrown. </li>
 * <li> If the input key is not null and there is a key-value pair in this map
 *      for the given key then the value in this pair is returned as output.
 *      A NoSuchElementException is thrown otherwise. </li>
 *  </li>
 *  </ol>
 *
 * @param key A key being asked about
 * @return the value corresponding to this key in this map
 * @throws NullPointerException if the input key is null
 * @throws NoSuchElementException if the input key is not null and
 *         there is no key-value pair for this key in this map
 *
 */

  public V get(K key) throws NullPointerException, NoSuchElementException {

    // These details must be changed!
    return null;

  }

// /**

 /**
 *
 * Sets the value for a given key in this map.
 * <br />
 *
 * <p>
 *  <strong>Precondition:</strong>
 * </p>
 * <ol style="list-style-type: lower-alpha">
 * <li> The class invariant is satisfied. </li>
 * <li> A key with type K and value with type V are given as input. </li>
 * <?ol>
 *
 * <p>
 *  <strong>Postcondition:</strong>
 * </p>
 * <ol style="list-style-type: lower-alpha">
 * <li> The class invariant is satisfied. </li>
 * <li> If the input key is null then a NullPointerException is thrown.
 * </li>
 * <li> If the input key is not null and there is no key-value pair for
 *      the given key then a key-value pair with the given key and value
 *      is added to this map. Otherwise, the value in the key-value pair
 *      for the input key is changed to the input value. The map is
 *      otherwise unchanged.
 * </li>
 * </ol>
 *
 * @param key The key for which a value is to be set.
 * @param value The corresponding value.
 * @throws NullPointerException if the input key is null.
 *
 */

  public void set(K key, V value) throws NullPointerException {

    // These details must be changed!
   
  }

  /**
 *
 * Removes the key-value pair for a given key.
 * <br />
 *
 * <p>
 *  <strong>Precondition:</strong>
 * </p>
 * <ol style="list-style-type: lower-alpha">
 * <li> The class invariant is satisfied. </li>
 * <li> A key of type K is given as input. </li>
 * </ol>
 * <p>
 *  <strong>Postcondition:</strong>
 * </p>
 * <ol style="list-style-type: lower-alpha">
 * <li> The class invariant is satisfied. </li>
 * <li> If the input key is null then a NullPointerException is thrown. </li>
 * <li> If the input key is not null and a key-value pair for this key
 *      is included in the map then this pair is removed from the map,
 *      which is otherwise unchanged. Otherwise a NoSuchElementException
 *      is thrown and the map is notchanged at all. </li>
 * </ol>
 *
 * @param key The key whose key-value pair is to be removed from this map.
 * @throws NullPointerException if the input key is null.
 * @throws NoSuchElementException if the input key is not null and the map did not
 *         include a key-value pair for this key.
 *
 */

  public void remove(K key) throws NullPointerException, NoSuchElementException {

    // These details must be changed!

  }

  /*
  *
  * Used for Testing
  *
  */
  
  RBT<K, V> thisTree() {
    return this.T;
  }

}
