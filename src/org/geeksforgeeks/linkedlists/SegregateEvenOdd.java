package org.geeksforgeeks.linkedlists;

import java.util.Iterator;
/**
 * Given a Linked List of integers, write a function to modify the linked list such that all even numbers appear before all the odd numbers in the modified linked list. Also, keep the order of even and odd numbers same.

Examples:
Input: 17->15->8->12->10->5->4->1->7->6->NULL
Output: 8->12->10->4->6->17->15->5->1->7->NULL

Input: 8->12->10->5->4->1->6->NULL
Output: 8->12->10->4->6->5->1->NULL

// If all numbers are even then do not change the list
Input: 8->12->10->NULL
Output: 8->12->10->NULL

// If all numbers are odd then do not change the list
Input: 1->3->5->7->NULL
Output: 1->3->5->7->NULL

T:O(n)
S:O(1)
 * @author Shiva
 *
 */
public class SegregateEvenOdd implements Iterable<SegregateEvenOdd.Node> {

	private Node head = null, tail = null;

	class Node {
		public int data;
		public Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	public void addItem(int item) {
		Node temp = new Node(item);
		if (tail == null)
			tail = temp;
		temp.next = head;
		head = temp;
	}

	/**
	 * adds node at last
	 * since we store tail pointer we can add element in o(1) time
	 * 
	 * @param node
	 */
	public void addItemAtLast(Node node) {
		node.next = null;
		tail.next = node;
		tail = tail.next;
	}

	/**
	 * Find last node and push all odd nodes after the tail
	 */
	public void segregateEvenAndOdd() {
		if (head == null || head.next == null)
			return;
		Node prev = new Node(0);
		prev.next = head;
		int length = 0;
		for (Node temp = head; temp != null; temp = temp.next, length++);
		Node temp = head, next = null;
		while (length-- > 0) {
			// temp is odd
			if (temp.data % 2 != 0) {
				if (head.data % 2 != 0) // if head is odd then move head
					head = head.next;
				
				next = temp.next;
				prev.next = temp.next;
				temp.next = null;
				// now add temp at last
				addItemAtLast(temp);
				temp = next;
			}
			// temp is even
			else {
				prev = temp;
				temp = temp.next;
			}

		}
		//when all elements in ll are odd
		if (head == null)
			head = temp;
	}

	@Override
	public Iterator<SegregateEvenOdd.Node> iterator() {
		return new Iterator<Node>() {
			Node current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Node next() {
				Node temp = current;
				current = current.next;
				return temp;
			}
		};
	}

	public static void main(String[] args) {

		SegregateEvenOdd llist = new SegregateEvenOdd();
		
		llist.addItem(8);
		llist.addItem(7);
		llist.addItem(6);
		llist.addItem(5);
		llist.addItem(4);
		llist.addItem(3);
		llist.addItem(2);
		llist.addItem(1);
		 
		/*
		llist.addItem(8);
		llist.addItem(6);
		llist.addItem(4);
		llist.addItem(2);
		llist.addItem(7);
		llist.addItem(5);
		llist.addItem(3);
		llist.addItem(1);*/

		System.out.print("\n Linked list: ");
		for (Node i : llist)
			System.out.print(i.data + " ");

		System.out.print("\n Linked list after segregating as even odd: ");
		llist.segregateEvenAndOdd();
		for (Node i : llist)
			System.out.print(i.data + " ");
	}

}
