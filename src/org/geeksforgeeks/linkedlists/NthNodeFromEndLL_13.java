package org.geeksforgeeks.linkedlists;
/**
 * Find n’th node from the end of a Linked List
 * @author Siva
 *
 * @param <Item>
 */
public class NthNodeFromEndLL_13<Item> {
	
	public Node head;
	
	private class Node {
		private Item data;
		private Node next;
		
		public Node (Item data) {
			this.data = data;
		}
	}
	/**
	 * Adds node at first
	 * @param item
	 */
	private void addItem(Item item) {
		Node node = new Node(item);
		node.next = head;
		head = node;
	}
	
	public Item nthNodeFromEnd(int n) {
		Node referencePointer = head, mainPointer = head;
		if (head == null)
			throw new NullPointerException("linked list is empty!");
		int count = 1;
		while(referencePointer.next != null) {
			if (++count > n)
				mainPointer = mainPointer.next;
			referencePointer = referencePointer.next;
		}
		if (count < n)
			throw new NullPointerException("linked list size is < " + n);
		return mainPointer.data;
	}

	public static void main(String[] args) {
	NthNodeFromEndLL_13<Integer> llist = new NthNodeFromEndLL_13<Integer>();
		/* The constructed linked list is:
        1->2->3->4->5->6->7 */
    llist.addItem(7);
    llist.addItem(6);
    llist.addItem(5);
    llist.addItem(4);
    llist.addItem(3);
    llist.addItem(2);
    llist.addItem(1);
    int n = 4;
    System.out.println("Node " + n + " from the end is: " + llist.nthNodeFromEnd(n));
	}

}
