import java.util.LinkedList;
import java.util.List;

//done
/**
 * A class to manage the number and the order of priorityQuene according to 
 * the urgency level of each patient
 * 
 *
 * @param <E>
 */
public class PriorityQueue<E extends Comparable<E>> {

	private Node head, tail;
	private int size; // size of the queue

	public PriorityQueue() {
	}

	/**
	 * insert the item to the queue according to the urgent level
	 */
	public void insert(E o) {
		Node n = new Node(o);
		// n should be inserted between current and prev
		Node current = head;
		Node prev;
		
		// if the previous one is more urgent -> return 1
		// find where the node should be inserted into
		while (current != null && o.compareTo(current.item) > 0) {
			current = current.next;
		}
		// special case, when prev is the last node
		if (current == null) {
			prev = tail;
		} else {
			prev = current.prev;
		}
		
		// add the new node to the queue
		if (prev != null) {
			prev.next = n;
			n.prev = prev;
		} else { 
			// special case, when the new node is the first node
			head = n;
		}
		n.next = current;
		if (current != null) {
			current.prev = n;
		} else {
			tail = n;
		}
		// update size
		size++;
	}

	/**
	 * return the data of the first node
	 */
	public E getFirst() {
		return head.item;
	}

	/**
	 * check if the queue is empty
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * return size of queue
	 */
	public int getSize() {
		return size;
	}


	/**
	 * return the patient of the first node and remove the node
	 * @return
	 */
	public E remove() {
		Node r = head;
		if (head != null) {
			head = head.next;
		}
		// when the queue is empty
		if (head == null) {
			tail = null;
		}
		// update size
		size--;
		return r.item;
	}
	
	private class Node {

		public E item;
		public Node next;
		public Node prev;

		public Node(E elements) {
			if (elements == null) {
				throw new IllegalArgumentException("elements = " + elements);
			}
			this.item = elements;
		}
	}
	
	public List<E> testForwardTraversal(PriorityQueue<E> list) {
		System.out.println("Forward Traversal");
		List<E> forward = new LinkedList<E>();
		if (head == null) { // if empty list
			return null;
		} else {
			for (Node n = head; n != null; n = n.next) {
				forward.add(n.item);
			}
			System.out.println(forward);
			return forward;
		}
	}
}