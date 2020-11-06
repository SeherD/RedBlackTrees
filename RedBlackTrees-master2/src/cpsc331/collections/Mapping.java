package cpsc331.collections;

import java.util.NoSuchElementException;

/**
*
* Provides an interface for a mapping whose keys are of type K
* and whose values are of type E.<br><br>
*
* Mapping Invariant: A finite collection S of pairs (k, v) of values
* where k has type&nbsp;K and is not null, and v has type&nbsp;V, is maintained.
* For each element k of K, the collection includes at most one ordered pair
* whose first entry is k, so that a partial function from&nbsp;K to&nbsp;V
* is being represented.
* <br><br>.
*
* @author Wayne Eberly
*
*/

public interface Mapping<K, V> {

  /**
  *
  * Returns the value associated with a given key, throwing a
  * NoSuchElementException if no value is currently associated
  * with the given key.<br><br>
  *
  * @param k the key whose value is to be returned
  * @return the value of this key
  * @throws NullPointerException if the input key is null
  * @throws NoSuchElementException if the key is not null, but no value
  *         is defined for this key
  * <br><br>
  *
  * Precondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The Mapping Invariant is satisfied. </li>
  * <li> A value k with type K has been provided as input. </li>
  * </ol>
  * Postcondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The Mapping Invariant is satisfied. </li>
  * <li> This Mapping has not been changed. </li>
  * <li> If k is null then a NullPointerException is thrown. </li>
  * <li> Otherwise (that is, if k is not null), if this Mapping includes
  *      some ordered pair whose first
  *      entry is the input key k, then the value v that is the
  *      second entry of this ordered pair (that is, the value
  *      associated with k) is returned as output.
  *      A NoSuchElementException is thrown otherwise. </li>
  * </ol>
  *
  */
 
  public V get (K k) throws NullPointerException, NoSuchElementException;
 
  /**
  *
  * @param k the key to be searched for
  * @return true if k is found; false otherwise
  * @throws NullPointerException if k is null
  * <br><br>
  * 
  * Reports whether a value for an input key k is presently
  * defined.<br><br>
  *
  * Precondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The Mapping Invariant is satisfied. </li>
  * <li> A value k with type K has been provided as input. </li>
  * </ol>
  * Postcondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The Mapping Invariant is satisfied. </li>
  * <li> This Mapping has not been changed. </li>
  * <li> If k is null then a NullPointerException is thrown. </li>
  * <li> Otherwise (that is, if k is not null), if this Mapping includes
  *      some ordered pair whose
  *      first entry is the input key k, then true is returned.
  *      Otherwise, false is returned. </li>
  * </ol>
  *
  */
 
  public default boolean defined (K k) throws NullPointerException {
 
    try {
     
      this.get(k);
      return true;
     
    } catch (NoSuchElementException e) {
     
      return false;
   
    }
   
  };
 
  /**
  *
  * Sets the value associated with an input key to be an input
  * value &mdash; replacing the value formerly associated with
  * this key if one already exists.<br><br>
  *
  * @param k the key for which a value is to be defined
  * @param v the value that is to be defined to this key
  * @throws NullPointerException if k is null
  * <br><br>
  *
  * Precondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The Mapping Invariant is satisfied. </li>
  * <li> A value k with type K and v with type V are provided
  *      as input. </li>
  * </ol>
  * Postcondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The Mapping Invariant is satisfied. </li>
  * <li> If k is null then a NullPointerException is thrown. </li>
  * <li> Otherwise (that is, if k is not null), if this Mapping included
  *      an ordered pair whose first entry
  *      is the input value&nbsp;k, then this is replaced by the
  *      ordered pair (k,&nbsp;v). Otherwise a new ordered pair
  *      (k,&nbsp;v) is added. No other changes to this Mapping
  *      are made. </li>
  * </ol>
  *
  */
 
  public void set (K k, V v) throws NullPointerException;

  /**
  *
  * Removes the ordered pair with a given input key k, returning a 
  * NoSuchElementException and leaving the Mapping unchanged if no
  * such ordered pair exists.<br><br>
  *
  * @param k the key for which a value is to be undefined
  * @throws NullPointerException if k is null
  * @throws NoSuchElementException if no value was defined for this key
  * <br><br>
  *
  * Precondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The Dictionary Invariant is satisfied. </li>
  * <li> A value k with type K is provided as input. </li>
  * </ol>
  * Postcondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> The Mapping Invariant is satisfied. </li>
  * <li> If k is null then a NullPointerException is thrown. </li>
  * <li> Otherwise (that is, if k is not null) if this Mapping includes
  *      an ordered pair (k,&nbsp;v) whose
  *      first entry is the input key k then this ordered pair is
  *      removed; no other changes are made. Otherwise a
  *      NoSuchElementException is thrown and this Mapping is not changed
  *      at all. </li>
  * </ol>
  *
  */

  public void remove (K k) throws NullPointerException, NoSuchElementException;

}
