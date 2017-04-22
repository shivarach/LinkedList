package org.geeksforgeeks.linkedlists;

import java.util.Iterator;
/**
 * Given two numbers represented by two lists, write a function that returns sum list. The sum list is list representation of addition of two input numbers.

Example 1

Input:
  First List: 5->6->3  // represents number 365
  Second List: 8->4->2 //  represents number 248
Output
  Resultant list: 3->1->6  // represents number 613
Example 2

Input:
  First List: 7->5->9->4->6  // represents number 64957
  Second List: 8->4 //  represents number 48
Output
  Resultant list: 5->0->0->5->6  // represents number 65005
  
 * T: O(m + n) where m and n are sizes of lls
 * S: O(1)
 * @author Shiva
 *
 */
public class AddTwoNumbersRepresentedByLL_40 implements Iterable<AddTwoNumbersRepresentedByLL_40.Node> {


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
	public Node add( Node head1, Node head2 ) {
		Node temp1 = head1, temp2 = head2;
		int x = 0, y = 0, carry = 0, sum = 0;
		AddTwoNumbersRepresentedByLL_40 resultedLL = new AddTwoNumbersRepresentedByLL_40();
		while (temp1 != null || temp2 != null) {
			x = (temp1 == null) ? 0 :  temp1.data;
			y = (temp2 == null) ? 0 : temp2.data;
			sum = carry + x + y;
			carry = sum / 10;
			resultedLL.addItemLast(sum % 10);
			
			if (temp1 != null)
				temp1 = temp1.next;
			if (temp2 != null)
				temp2 = temp2.next;
		}
		if (carry > 0)
			resultedLL.addItemLast(carry);
		return resultedLL.head;
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
		
		AddTwoNumbersRepresentedByLL_40 llist1 = new AddTwoNumbersRepresentedByLL_40();
		llist1.addItem(9);
		llist1.addItem(3);
		llist1.addItem(2);
		llist1.addItem(1);
		
		System.out.print("\n Linked list: ");
		for (Node i : llist1)
			System.out.print(i.data + " ");
		
		AddTwoNumbersRepresentedByLL_40 llist2 = new AddTwoNumbersRepresentedByLL_40();
		llist2.addItem(7);
		llist2.addItem(6);
		llist2.addItem(5);

		System.out.print("\n Linked list: ");
		for (Node i : llist2)
			System.out.print(i.data + " ");
		
		System.out.println("\nSum of 9321 + 765 = 10086" );
		
		AddTwoNumbersRepresentedByLL_40 addedLL = new AddTwoNumbersRepresentedByLL_40();
		addedLL.head = addedLL.add(llist1.head, llist2.head);
		System.out.print("After adding the new linked list is: ");
		for (Node i : addedLL)
			System.out.print(i.data + " ");
	}
	
	
	

}
