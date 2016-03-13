package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

import javax.activity.InvalidActivityException;
/**
 * Detect and remove cycle in linked list
 * Find DetectAndRemoveCycleInSLL_39.png and DetectAndRemoveCycleInSLL_39_2
 * @author Shiva
 *
 * @param <Item>
 */
public class DetectAndRemoveCycleInSLL_39<Item> implements Iterable<Item> {


	private Node head = null;

	private class Node {
		public Item data;
		public Node next;

		public Node(Item data) {
			this.data = data;
			this.next = null;
		}
	}

	public void addItem(Item item) {
		Node temp = new Node(item);
		temp.next = head;
		head = temp;
	}
	
	public void removeCycle() throws InvalidActivityException {
		if (head == null)
			throw new NullPointerException();
		Node slowP = head;
		Node fastP = getCycle(); //returns node where two pointers meet in cycle
		Node prevFastP = null;
		if(fastP == null)
			throw new InvalidActivityException("No cycle in llist");
		//make both pointers meet at start of cycle
		while (true) {
			if (slowP == fastP) {
				if (prevFastP != null)
					prevFastP.next = null;
				else // single node
					slowP.next = null;
				return;
			}
			slowP = slowP.next;
			prevFastP = fastP;
			fastP = fastP.next;
		}
		
	}
	
		/**
		 * Floyd's cycle detection 
		 * @retuns where two pointers meet in cycle
		 */
		public Node getCycle() {
			Node slowP = head, fastP = head;
			while (fastP.next != null) {
				slowP = slowP.next;
				fastP = fastP.next.next;
				
				if (slowP == fastP) // both pointers meet if there exists a cycle
					return slowP;
			}
			return null;
		}
	@Override
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			Node current = head;
			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Item next() {
				Node temp = current;
				current = current.next;
				return temp.data;
			}
		};
	}
	
	/**
	 * specific to this main client
	 */
	public void makeAcycle(int j) {
		Node startOfcycle = null;
		int i = 0;
		Node temp = head;
		while(temp != null) {
			if (i == j)
				startOfcycle = temp;
			if (temp.next == null) {
				temp.next = startOfcycle;
				return;
			}
				
			temp = temp.next;
			i++;
		}
	}

	public static void main(String[] args) {
		
		DetectAndRemoveCycleInSLL_39<Integer> llist = new DetectAndRemoveCycleInSLL_39<Integer>();
		 llist.addItem(7);
	        llist.addItem(6);
	        llist.addItem(5);
	        llist.addItem(4);
	        llist.addItem(3);
	        llist.addItem(2);
	        llist.addItem(1);
	        
	        try {
	        	llist.makeAcycle(2);
	       
	        	if (llist.getCycle() != null)
	        		System.out.println("Linkedlist has cycle!");
	        		
				llist.removeCycle();
				
				 System.out.print("\n Linked list after removing cycle ");
			        for (Integer i : llist)
			        	System.out.print(i + " ");
			        System.out.println();
			} catch (InvalidActivityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	

}
