package org.geeksforgeeks.linkedlists;

import java.util.Iterator;
/**
 * Reverse a linked list using iteration
 * TC = O (N)
 * @author SHIVA
 *
 * @param <Item>
 */
public class ReverseLLWithIteration_16<Item> implements Iterable<Item> {
	
	private Node head = null, refPointer = null;
	
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
	
	public void reverse() {
		Node prev = null;
		Node curr = head;
		Node nextP = null;
		
		while (curr != null) {
			nextP = curr.next;
			curr.next = prev;
			prev = curr;
			curr = nextP;
		}
		head = prev;
	}
	
	public void reverse(Node temp) {
		if(temp.next == null) {
			head = temp;
			refPointer = head;
			return;
		}
		reverse(temp.next);
		refPointer.next = temp;
		refPointer = temp;
		refPointer.next = null;// it makes sure that no link occurs between first two nodes(original nodes) of linkedlist
		return;
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
		
		ReverseLLWithIteration_16<Integer> llist = new ReverseLLWithIteration_16<Integer>();
		 llist.addItem(7);
	        llist.addItem(6);
	        llist.addItem(5);
	        llist.addItem(4);
	        llist.addItem(3);
	        llist.addItem(2);
	        llist.addItem(1);
	 
	        System.out.print("\n Linked list ");
	        for (Integer i : llist)
	        	System.out.print(i + " ");
	        System.out.print("\n Linked list after reversing ");
	        llist.reverse();
	        for (Integer i : llist)
	        	System.out.print(i + " ");
	}

	

}
