package org.geeksforgeeks.linkedlists;

import java.util.Iterator;
/**
 * finds given ll is palindrom or not
 * T:O(n)
 * Auxiliary Space: O(n) if Function Call Stack size is considered, otherwise O(1).
 * @author Siva
 *
 * @param <Item>
 */
public class Palindrome_Recursive_22<Item extends Comparable<Item>> implements Iterable<Item> {


	private Node head = null, temp;

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
	
	public boolean isPalindrome() {
		temp = head;// this temp is used by following recursive function while unwinding
		return isPalindrome(head) ;
	}

	private boolean isPalindrome(Node right) {
		// if right reaches null, just return true so that on unwinding, right
		// points to last node
		if (right == null)
			return true;
		boolean palindrome = isPalindrome(right.next);
		if (palindrome == false)
			return false;
		else
			palindrome = (temp.data.equals(right.data));
		temp = temp.next;
		return palindrome;
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

		Palindrome_Recursive_22<Integer> llist2 = new Palindrome_Recursive_22<Integer>();
		llist2.addItem(1);
		llist2.addItem(2);
		llist2.addItem(3);
		llist2.addItem(4);
		llist2.addItem(3);
		llist2.addItem(2);
		llist2.addItem(1);

		System.out.print("\n Linked list: ");
		for (Integer i : llist2)
			System.out.print(i + " ");
		System.out.println(" is palindrome?: " + llist2.isPalindrome());

		Palindrome_Recursive_22<Integer> llist = new Palindrome_Recursive_22<Integer>();
		llist.addItem(7);
		llist.addItem(6);
		llist.addItem(5);
		llist.addItem(4);
		llist.addItem(3);
		llist.addItem(2);
		llist.addItem(1);

		System.out.print("\n Linked list: ");
		for (Integer i : llist)
			System.out.print(i + " ");
		System.out.println(" is palindrome?: " + llist.isPalindrome());
	}

}
