package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

/**
 * Function to check if a singly linked list is palindrome(Iterative) T:O(n)
 * S:O(1)
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class Palindrome_Iterative_22<Item extends Comparable<Item>> implements
		Iterable<Item> {

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

	/**
	 * finds whether a linked list is palindrome by reversing the second half,
	 * now check both the halves are same or not and finally reverse second half
	 * again to attain original ll
	 * 
	 * @return true if ll is palindrome
	 */
	public boolean isPalindrome() throws Exception {
		Node fastP = head, slowP = head, prevOfSlowP = null;

		if (head == null)
			throw new Exception("LL is empty!");
		else if (head.next == null)
			return true;
		// Now find the mid of linked list. If fastP is null then size is
		// even(slowP points to first element of second half) and fastP is not
		// null when size is odd(slowP ponts to mid element)
		while (fastP != null && fastP.next != null) {
			fastP = fastP.next.next;
			prevOfSlowP = slowP;
			slowP = slowP.next;
		}
		// skipping mid element if ll is odd
		if (fastP != null) {
			prevOfSlowP = slowP;
			slowP = slowP.next;
		}
		
		Node mid = reverse(slowP);

		// reversed ll length is always <= first part of original linked list.
		// so if it is odd the middle element we don't compare here
		Node left = head, right;
		for (right = mid; right != null; right = right.next, left = left.next) {
			if (!left.data.equals(right.data)) {
				prevOfSlowP.next = reverse(mid);
				return false;
			}

		}
		// ll is palindrom
		prevOfSlowP.next = reverse(mid);
		return true;
	}

	/**
	 * Reverses the liked list(it would change head2 pointer)
	 * 
	 * @param head2
	 * @return
	 */
	private Node reverse(Node head2) {
		Node current = head2, prev = null, next;
		while(current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		head2 = prev;
		return head2;
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

	public static void main(String[] args) throws Exception {

		Palindrome_Iterative_22<Integer> llist2 = new Palindrome_Iterative_22<Integer>();
		llist2.addItem(1);
		llist2.addItem(2);
		llist2.addItem(3);
		llist2.addItem(4);
		llist2.addItem(4);
		llist2.addItem(3);
		llist2.addItem(2);
		llist2.addItem(1);

		System.out.print("\n Linked list: ");
		for (Integer i : llist2)
			System.out.print(i + " ");
		System.out.println(" is palindrome?: " + llist2.isPalindrome());
		System.out.print("\n Again original Linked list is: ");
		for (Integer i : llist2)
			System.out.print(i + " ");

		Palindrome_Iterative_22<Integer> llist = new Palindrome_Iterative_22<Integer>();
		llist.addItem(8);
		llist.addItem(7);
		llist.addItem(6);
		llist.addItem(5);
		llist.addItem(4);
		llist.addItem(3);
		llist.addItem(2);
		llist.addItem(1);

		System.out.print("\n\n\n\n Linked list: ");
		for (Integer i : llist)
			System.out.print(i + " ");
		System.out.println(" is palindrome?: " + llist.isPalindrome());
		System.out.print("\n Again original Linked list is: ");
		for (Integer i : llist)
			System.out.print(i + " ");
	}

}
