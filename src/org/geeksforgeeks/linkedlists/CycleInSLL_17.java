package org.geeksforgeeks.linkedlists;

import java.util.Iterator;
/**
 * Dectect a loop in ll
 * @author Shiva
 *
 * @param <Item>
 */
public class CycleInSLL_17<Item> implements Iterable<Item> {


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
	// Floyd's cycle detection
	public boolean hasLoop() {
		Node slowP = head, fastP = head;
		while (fastP != null && fastP.next != null) {
			slowP = slowP.next;
			fastP = fastP.next.next;
			
			if (slowP == fastP)
				return true;
		}
		return false;
	}
	/**
	 * specific to this main client
	 */
	public void makeALoop(int j) {
		Node startOfLoop = null;
		int i = 0;
		Node temp = head;
		while(temp != null) {
			if (i == j)
				startOfLoop = temp;
			if (temp.next == null) {
				temp.next = startOfLoop;
				return;
			}
				
			temp = temp.next;
			i++;
		}
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

	public static void main(String[] args) {
		
		CycleInSLL_17<Integer> llist = new CycleInSLL_17<Integer>();
		 llist.addItem(7);
	        llist.addItem(6);
	        llist.addItem(5);
	        llist.addItem(4);
	        llist.addItem(3);
	        llist.addItem(2);
	        llist.addItem(1);
	 
	        llist.makeALoop(2);
	        System.out.println(llist.hasLoop());
	}

	

}
