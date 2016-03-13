package org.geeksforgeeks.linkedlists;

import java.util.Iterator;
/**
 * Given two numbers represented by two lists, write a function that returns sum list. The sum list is list representation of addition of two input numbers.

Example 1

Input:
  First List: 1->2->3->4  // represents number 1234
  Second List: 5->6->7 //  represents number 567
Output
  Resultant list: 1->8->0->1  // represents number 1801

  
  T: O(m + n) where m and n are sizes of lls
  S: O(1)
 * @author Shiva
 *
 */
public class AddTwoNumbersRepresentedByLL_46 implements Iterable<AddTwoNumbersRepresentedByLL_46.Node> {


	private Node head = null;
	private Node tail;

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
		temp.next = head;
		head = temp;
	}
	
	public void addItemLast(int item) {
		if (head == null) {
			head = new Node(item);
			tail = head;
			return;
		}
		tail.next = new Node(item);
		tail = tail.next;
	}
	/**
	 * Adds numbers in two linked list to a new linked list
	 * @param head1
	 * @param head2
	 * @return
	 */
	public void add( Node head1, Node head2 ) {
		
		// find lengths of two linked lists
		int x = findLength(head1); 
		int y =  findLength(head2);		
		// find big and small linked lists and the difference between them
		Node big = head1;
		Node small = head2;
		int diff = x - y;
		if ( x < y) {
			big = head2;
			small = head1;
			diff = y - x;
		}
		int finalCarry = addNumbers(big, small, diff);
		if (finalCarry > 0)
			this.addItem(finalCarry); // add final carry also
		return;
	}
	/**
	 * fins length of a ll
	 * @param temp
	 * @return
	 */
	private int findLength(Node temp) {
		int length = 0;
		while( temp != null) {
			length++;
			temp = temp.next;
		}
		return length;
	}
	// finds sum and creates nodes in resultant ll
	private int addNumbers(Node big, Node small, int diff) {
		if (diff > 0) {
			int carry = addNumbers(big.next, small, --diff);
			int sum = carry + big.data;
			this.addItem(sum % 10);
			return sum / 10; // carry
		}
		if (big == null)//at the same time small is also null
			return 0;
		int carry = addNumbers(big.next, small.next, diff);
		int sum = carry + big.data + small.data;
		this.addItem(sum % 10);
		return sum / 10; // carry
	}

	@Override
	public Iterator<Node> iterator() {
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
		
		AddTwoNumbersRepresentedByLL_46 llist1 = new AddTwoNumbersRepresentedByLL_46();
		llist1.addItem(4);
		llist1.addItem(3);
		llist1.addItem(2);
		llist1.addItem(1);
		
		System.out.print("\n Linked list: ");
		for (Node i : llist1)
			System.out.print(i.data + " ");
		
		AddTwoNumbersRepresentedByLL_46 llist2 = new AddTwoNumbersRepresentedByLL_46();
		llist2.addItem(7);
		llist2.addItem(6);
		llist2.addItem(5);

		System.out.print("\n Linked list: ");
		for (Node i : llist2)
			System.out.print(i.data + " ");
		
		System.out.println("\nSum of 1234 + 567 = 1801" );
		
		AddTwoNumbersRepresentedByLL_46 addedLL = new AddTwoNumbersRepresentedByLL_46();
		addedLL.add(llist1.head, llist2.head);
		System.out.print("After adding the new linked list is: ");
		for (Node i : addedLL)
			System.out.print(i.data + " ");
	}
	
	
	

}
