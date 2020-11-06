package cpsc331.assignment2;

import cpsc331.assignment2.Colour;
import java.util.NoSuchElementException;
import cpsc331.collections.ElementFoundException;
import cpsc331.assignment2.RBTNode;
import cpsc331.assignment2.RBT;

public class RBTUtilities<K extends Comparable<K>, V> {

  /*
  *
  * Reports whether an input tree is a red-black tree.
  *
  */
  
  boolean isRBT(RBT<K, V> T, boolean verbose) {
  
    RBTNode<K, V> root = T.root();
    if (root != null) {
      if (root.colour() == Colour.BLACK) {
        RBTNode<K, V> parent = root.parent();
        if (parent == null) {
          return linksOK(root, verbose)
                   && isSearchTree(root, verbose)
                   && colourOK(root, verbose)
                   && sizeOK(root, verbose)
                   && blackHeightOK(root, verbose);
        } else {
          if (verbose) {
            System.out.println("Parent of root of tree is not null.");
          };
          return false;
        }
      } else {
        if (verbose) {
          System.out.println("Root of tree is not black.");
        };
        return false;
      }
    } else {
      if (verbose) {
        System.out.println("Root of tree is null.");
      };
      return false;
    }
    
  }
    
  /*
  *
  * Checks that links between children and parents and the
  * "isNil" attribute is sensibly set
  *
  */
    
  boolean linksOK(RBTNode<K, V> x, boolean verbose) {
  
    if (x.isNil()) {
      K key = x.key();
      if (key == null) {
        V value = x.value();
        if (value == null) {
          RBTNode<K, V> leftChild = x.left();
          if (leftChild == null) {
            RBTNode<K, V> rightChild = x.right();
            if (rightChild == null) {
              return true;
            } else {
              if (verbose) {
                System.out.println("Right child of NIL node is not null.");
              };
              return false;
            }
          } else {
            if (verbose) {
              System.out.println("Left child of NIL node is not null.");
            };
            return false;
          }
        } else {
          if (verbose) {
            System.out.print("Node with null key and value ");
            System.out.print(value.toString() + " is set as NIL.");
          };
          return false;
        }
      } else {
        if (verbose) {
          System.out.print("Node with key " + key.toString());
          System.out.println(" is set as NIL.");
        };
        return false;
      }
    } else {
      K key = x.key();
      if (key != null) {
        RBTNode<K, V> leftChild = x.left();
        if (leftChild != null) {
          RBTNode<K, V> leftParent = leftChild.parent();
           if (x == leftParent) {
             RBTNode<K, V> rightChild = x.right();
             if (rightChild != null) {
               RBTNode<K, V> rightParent = rightChild.parent();
               if (x == rightParent) {
                 return(linksOK(leftChild, verbose)
                    && linksOK(rightChild, verbose));
               } else {
                 if (verbose) {
                   System.out.print("Parent of right child of node with key ");
                   System.out.print(key.toString());
                   System.out.println(" is incorrectly set.");
                 };
                 return false;
               }
             } else {
               if (verbose) {
                 System.out.print("Right child of node with key ");
                 System.out.print(key.toString());
                 System.out.println(" is null.");
               };
               return false;
             }
           } else {
             if (verbose) {
               System.out.print("Parent of left child of node with key ");
               System.out.print(key.toString());
               System.out.println(" is incorrectly set.");
             };
             return false;
           }
        } else {
          if (verbose) {
            System.out.print("Left child of node with key ");
            System.out.print(key.toString());
            System.out.println(" is null.");
          };
          return false;
        }
      } else {
        if (verbose) {
          System.out.println("Non-NIL node has a null key.");
        };
        return false;
      }
    }
    
  }
  
  /*
  *
  * Checks that this tree is organized as a search tree.
  *
  */
  
  boolean isSearchTree(RBTNode<K, V> x, boolean verbose) {
  
    if (x.isNil()) {
      return true;
    } else {
      K key = x.key();
      RBTNode<K, V> leftChild = x.left();
      RBTNode<K, V> rightChild = x.right();
      if (leftChild.isNil()) {
        if (rightChild.isNil()) {
          return true;
        } else {
          K rightKey = rightChild.key();
          if (key.compareTo(rightKey) < 0) {
            return isSearchTree(rightChild, verbose);
          } else {
            if (verbose) {
             System.out.print("Key of right child of node with key ");
             System.out.print(key.toString());
             System.out.println(" is too small.");
            };
            return false;
          }
        }
      } else {
        if (rightChild.isNil()) {
          K leftKey = leftChild.key();
          if (key.compareTo(leftKey) > 0) {
            return isSearchTree(leftChild, verbose);
          } else {
            if (verbose) {
              System.out.print("Key of left child of node with key ");
              System.out.print(key.toString());
              System.out.println(" is too large.");
            };
            return false;
          }
        } else {
          K leftKey = leftChild.key();
          K rightKey = rightChild.key();
          if (key.compareTo(leftKey) > 0) {
            if (key.compareTo(rightKey) < 0) {
              return isSearchTree(leftChild, verbose)
                && isSearchTree(rightChild, verbose);
            } else {
             if (verbose) {
               System.out.print("Key of right child of node with key ");
               System.out.print(key.toString());
               System.out.println(" is too small.");
             };
             return false;
            }
          } else {
            if (verbose) {
              System.out.print("Key of left child of node with key ");
              System.out.print(key.toString());
              System.out.println(" is too large.");
            };
            return false;
          }
        }
      }
    }
    
  }
  
  /*
  *
  * Checks that colour properties are satisfied.
  *
  */
  
  boolean colourOK(RBTNode<K, V> x, boolean verbose) {
  
    if (x.isNil()) {
      if (x.colour() == Colour.BLACK) {
        return true;
      } else {
        if (verbose) {
          System.out.println("Colour of NIL node is not black.");
        };
        return false;
      }
    } else {
      K key = x.key();
      RBTNode<K, V> left = x.left();
      RBTNode<K, V> right = x.right();
      if (x.colour() == Colour.BLACK) {
        return colourOK(left, verbose) && colourOK(right, verbose);
      } else if (x.colour() == Colour.RED) {
        if (left.colour() == Colour.BLACK) {
          if (right.colour() == Colour.BLACK) {
            return colourOK(left, verbose) && colourOK(right, verbose);
          } else {
            if (verbose) {
              System.out.print("Right child of red node with key ");
              System.out.print(key.toString());
              System.out.println(" is not black.");
            };
            return false;
          }
        } else {
          if (verbose) {
            System.out.print("Left child of red node with key ");
            System.out.print(key.toString());
            System.out.println(" is not black.");
          };
          return false;
        }
      } else {
        if (verbose) {
          System.out.print("Node with key ");
          System.out.print(key.toString());
          System.out.println(" is neither red nor black.");
        };
        return false;
      }
    }
    
  }
  
  /*
  *
  * Checks that set sizes are correctly defined.
  *
  */
  
  boolean sizeOK(RBTNode<K, V> x, boolean verbose) {
  
    if (x.isNil()) {
      if (x.size() == 0) {
        return true;
      } else {
        if (verbose) {
          System.out.println("Size of NIL node is nonzero.");
        };
        return false;
      }
    } else {
      K key = x.key();
      RBTNode<K, V> leftChild = x.left();
      RBTNode<K, V> rightChild = x.right();
      if (sizeOK(leftChild, verbose) && sizeOK(rightChild, verbose)) {
        if (x.size() == leftChild.size() + rightChild.size() + 1) {
          return true;
        } else {
          if (verbose) {
            System.out.print("Size of node with key ");
            System.out.print(key.toString());
            System.out.println(" is incorrectly set.");
          };
          return false;
        }
      } else {
        return false;
      }
    }
  
  }
  
  /*
  *
  * Checks that black heights are well defined, and correctly recorded
  *
  */
  
  boolean blackHeightOK(RBTNode<K, V> x, boolean verbose) {
  
    if (x.isNil()) {
      if (x.blackHeight() == 0) {
        return true;
      } else {
        if (verbose) {
          System.out.println("Black height of NIL node is nonzero.");
        };
        return false;
      }
    } else {
      K key = x.key();
      RBTNode<K, V> leftChild = x.left();
      RBTNode<K, V> rightChild = x.right();
      int bh = x.blackHeight();
      if (blackHeightOK(leftChild, verbose)
        && blackHeightOK(rightChild, verbose)) {
        int leftBH;
        if (leftChild.colour() == Colour.BLACK) {
          leftBH = leftChild.blackHeight() + 1;
        } else {
          leftBH = leftChild.blackHeight();
        };
        if (bh == leftBH) {
          int rightBH;
          if (rightChild.colour() == Colour.BLACK) {
            rightBH = rightChild.blackHeight() + 1;
          } else {
            rightBH = rightChild.blackHeight();
          };
          if (bh == rightBH) {
            return true;
          } else {
            if (verbose) {
              System.out.print("Black height of node with key ");
              System.out.print(key.toString());
              System.out.println(" and its right child are not consistent.");
            };
            return false;
          }
        } else {
          if (verbose) {
            System.out.print("Black height of node with key ");
            System.out.print(key.toString());
            System.out.println(" and its left child are not consistent.");
          };
          return false;
        }
      } else {
        return false;
      }
    }
  
  }
  
 /*
 *
 * Tests a search for a non-null key.
 *
 */
 
 public void testSearch(RBT<K, V> T, K key, V value, boolean success) {
 
   try {
   
     System.out.print("Searching for \"");
     System.out.println(key.toString() + "\".");
     V foundValue = T.get(key);
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
  
   if (!isRBT(T, false)) {
     System.out.println("Resulting tree is not a valid red-black tree.");
     isRBT(T, true);
   };
   System.out.println("");

 }
 
 /*
 * 
 * Tests for an insertion of a given non-null key with a given value.
 *
 */
 
 public void testInsert(RBT<K, V> T, K key, V value, boolean success) {
 
   System.out.print("Trying to insert \"" + key.toString() + "\" with " );
   if (value == null) {
     System.out.println("a null value.");
   } else {
     System.out.println("value \"" + value.toString() + "\".");
   };
   try {
     T.insert(key, value);
     if (success) {
       System.out.println("Insertion was apparently successful.");
     } else {
       System.out.println("Search failed to throw the expected FoundException.");
     }
   } catch (ElementFoundException ex) {
     if (success) {
       System.out.println("Search incorrectly threw a FoundException.");
     } else {
       System.out.println("Search threw a FoundException, as expected.");
     }
   };
   
   try {
   
     V newValue = T.get(key);
     if (success) {
       if (value != null) {
         if (newValue != null) {
          if (!value.equals(newValue)) {
            System.out.print("Value does not seem to have been ");
            System.out.println("set to \"" + value.toString() + "\", as required.");
          }
         } else {
           System.out.print("Value does not seem to have been ");
           System.out.println("set to \"" + value.toString() + "\", as required.");
         }
       } else {
         if (newValue != null) {
           System.out.print("Value does not seem to have been set ");
           System.out.println("to null, as required.");
         }
       }
     };
   
   } catch (NoSuchElementException ex){
     System.out.print("A value for key \"" + key.toString() + "\" ");
     System.out.println("does not seem to exist.");
   };
   
   if (!isRBT(T, false)) {
     System.out.println("Resulting tree is not a valid red-black tree.");
     isRBT(T, true);
   };
   
   System.out.println("");
 
 }
 
 /*
 *
 * Tests an attempt to change the value assigned to a non-null key.
 *
 */
 
 public void testChange(RBT<K, V> T, K key, V value, boolean success) {
 
   System.out.print("Trying to change the value assigned for ");
   System.out.print("\"" + key.toString() + "\" to ");
   if (value == null) {
     System.out.println("null.");
   } else {
     System.out.println("\"" + value.toString() + "\".");
   };
   try {
     T.change(key, value);
     if (!success) {
       System.out.print("Attempt failed to throw the expected ");
       System.out.println("NoSuchElementException.");
     };
   } catch (NoSuchElementException ex) {
     if (success) {
       System.out.println("A NoSuchElementException was unexpectedly thrown.");
     } else {
       System.out.println("A NoSuchElementException was thrown, as expected.");
     };
   };
   
   try {
 
     V newValue = T.get(key);
     if (success) {
       if (value != null) {
         if (newValue != null) {
           if (value.equals(newValue)) {
             System.out.println("Change in value was successful.");
           } else {
             System.out.print("Value does not seem to have been ");
             System.out.println("set to \"" + value.toString() + "\", as required.");
           }
         } else {
           System.out.print("Value does not seem to have been ");
           System.out.println("set to \"" + value.toString() + "\", as required.");
         }
       } else {
         if (newValue != null) {
           System.out.print("Value does not seem to have been set ");
           System.out.println("to null, as required.");
         } else {
           System.out.println("Change in value was successful.");
         };
       }
     };
   
   } catch (NoSuchElementException ex){
     if (success) {
       System.out.print("Operation failed: ");
       System.out.print("A value for key \"" + key.toString() + "\" ");
       System.out.println("does not seem to exist.");
     };
   };
 
   if (!isRBT(T, false)) {
     System.out.println("Resulting tree is not a valid red-black tree.");
     isRBT(T, true);
   };
   System.out.println("");
   
 }
 
 /*
 *
 * Tests the deletion of a given non-null key
 *
 */
 
 public void testDelete(RBT<K, V> T, K key, boolean success) {
 
   System.out.print("Trying to delete the value assigned to ");
   System.out.println("\"" + key.toString() + "\".");
   try {
     T.delete(key);
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

   if (!isRBT(T, false)) {
     System.out.println("Resulting tree is not a valid red-black tree.");
     isRBT(T, true);
   };
   System.out.println("");
   
 }

}