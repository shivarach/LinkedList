package org.geeksforgeeks.linkedlists;
/**
 * Write a function to print the middle of a given linked list
 * @author Shiva R
 *
 * @param <Item>
 */
public class MiddleNodeLL_12<Item> {
	
	public Node head = null;
	
	private class Node
	{
		public Node(Item data) {
			this.data = data;
		}
		private Item data;
		private Node next;
	}
	
	/**
	 * Adds at first
	 * @param item
	 */
	public void addItem(Item item)
	{
		Node node = new Node(item);
		node.next = head;
		head = node;
	}
	
	public Item findMiddleNode() {
		Node slowPointer = head, fastPointer = null;
		
		if (slowPointer == null)
			throw new NullPointerException("ll is empty!");
		
		fastPointer = slowPointer.next;
		while (fastPointer != null && fastPointer.next != null) {
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;
		}
		return slowPointer.data;
	}

	public static void main(String[] args) {
		MiddleNodeLL_12<Integer> llist = new MiddleNodeLL_12<Integer>();
		llist.addItem(7);
        llist.addItem(6);
        llist.addItem(5);
        llist.addItem(4);
        llist.addItem(3);
        llist.addItem(2);
        llist.addItem(1);
        System.out.println("Middle node's data is: " + llist.findMiddleNode());
	}

}
