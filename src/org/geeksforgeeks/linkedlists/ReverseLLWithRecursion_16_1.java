package org.geeksforgeeks.linkedlists;

import java.util.Iterator;
/**
 * Reverse a linked list using recursion
 * TC = O (N)
 * @author SHIVA
 *
 * @param <Item>
 */
public class ReverseLLWithRecursion_16_1<Item> implements Iterable<Item> {
	
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
		Node oldHead = reverse(head);
		oldHead.next = null;
	}
	
	private Node reverse(Node x) {
		if(x == null)
			return null;
		if(x.next == null)
		{
			head = x;
			return head;
		}
		Node tmp = reverse(x.next);
		tmp.next = x;
		return x;
		
	}
	
	/*public void reverse(Node temp) {
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
	}*/
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
		
		ReverseLLWithRecursion_16_1<Integer> llist = new ReverseLLWithRecursion_16_1<Integer>();
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
