package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

import javax.activity.InvalidActivityException;
/**
 * Find start node of a cycle in ll
 * @author Shiva
 *
 * @param <Item>
 */
public class StartOfCycleInSLL_17_1<Item> implements Iterable<Item> {


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
	
	public Node getStartOfcycle() throws InvalidActivityException {
		Node slowP = head;
		Node fastP = getCycle(); //return node where two pointers met
		if(fastP == null)
			throw new InvalidActivityException("No cycle in llist");
		//make both pointers meet at start of cycle
		while (true) {
			if (slowP == fastP)
				return slowP;
			
			slowP = slowP.next;
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
				
				if (slowP == fastP)
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
		
		StartOfCycleInSLL_17_1<Integer> llist = new StartOfCycleInSLL_17_1<Integer>();
		 llist.addItem(7);
	        llist.addItem(6);
	        llist.addItem(5);
	        llist.addItem(4);
	        llist.addItem(3);
	        llist.addItem(2);
	        llist.addItem(1);
	        
	        System.out.print("\n Linked Linked list ");
	        for (Integer i : llist)
	        	System.out.print(i + " ");
	        System.out.println();
	        
	       // llist.makeAcycle(2);
	        try {
	        	llist.makeAcycle(2);
				System.out.print("\nStart of cycle is at: " + llist.getStartOfcycle().data);
			} catch (InvalidActivityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	

}
