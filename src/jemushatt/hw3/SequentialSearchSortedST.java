package jemushatt.hw3;

/**
 * The declaration of SequentialSearchST now declares that each key can be compared
 * with each other key via the Comparable interface
 *
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchSortedST<Key extends Comparable<Key>, Value> {
	int N;           // number of key-value pairs
	Node first;      // the linked list of key-value pairs

	// Nodes now store (key and value)
	class Node {
		Key   key;
		Value value;
		Node  next;

		public Node (Key key, Value val, Node next)  {
			this.key  = key;
			this.value  = val;
			this.next = next;
		}
	}

	public int size()                 { return N;  }
	public boolean isEmpty()          { return size() == 0; }
	public boolean contains(Key key)  { return get(key) != null; }


	/**
	 * Be sure to modify this method to stop once you have found a key that is
	 * larger than the key you are looking for.
	 *
	 * @param key
	 * @return
	 */
	public Value get(Key key) {
		Node n = first;
		/*
		while (n != null) {
			if (key.equals (n.key)) {
				return n.value;
			}

			n = n.next;
		}
		*/
		while(n!=null) {
			//If target key is less than current key, that means it is not present
			if(key.compareTo(n.key) <0) {
				return null;
			}
			else if(key.compareTo(key)==0) {
				return n.value;
			}
			else {
				n=n.next;
			}
		}
		return null;  // not present
	}

	/**
	 * Be sure to modify this method to insert the key into its proper place
	 * in ascending sorted order.
	 *
	 * @param key
	 * @return
	 */
	public void put(Key key, Value val) {
		//If key has no value, don't put it in
		if (val == null) {
			delete (key);
			return;
		}
		//If valid key value pair, put it in the right spot
		Node currentNode = first;

		while(currentNode!=null) {
			//If target key >= current key, that means we have found a spot, even if the values are equal
			if(key.compareTo(currentNode.key) > 0) {
				 Node nodeAfter = currentNode.next;
				 first.next=new Node(key,val,nodeAfter);
				 N++;
				 return;

			}
			else if(key.compareTo(currentNode.key)==0) {
				currentNode.value=val;
				return;
			}
		}
		// add as new node at beginning
		first = new Node (key, val, first);
		N++;
		/*
		Node n = first;
		while (n != null) {
			if (key.equals (n.key)) {
				n.value = val;
				return;
			}

			n = n.next;
		}

		// add as new node at beginning
		first = new Node (key, val, first);
		N++;
		*/
	}

	/**
	 * Can short-circuit this method once you hit a key value that is larger
	 * than the target key being deleted.
	 *
	 * @param key
	 */

	//A = current node: 2
	//B = target node: 5
	//2-5 = -3
	public void delete(Key key) {
		Node currentNode = first;
		Node nodeAfter = null;

		while(currentNode!=null) {
			int result = currentNode.key.compareTo(key);
			//If it is not in the collection
			if(result <0) {
				return;
			}
			//If we have found the node
			else if(result ==0) {

			}
		}
		/*
		Node prev = null;
		Node n    = first;
		while (n != null) {
			if (key.equals (n.key)) {
				if (prev == null) {       // no previous? Must have been first
					first = n.next;
				} else {
					prev.next = n.next;   // have previous one linke around
				}

				return;
			}

			prev = n;                     // don't forget to update!
			n = n.next;
		}
		*/
	}
}