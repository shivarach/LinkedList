package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

public class AddTwoNumbersRepresentedByLL implements Iterable<AddTwoNumbersRepresentedByLL.Node> {


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
		AddTwoNumbersRepresentedByLL resultedLL = new AddTwoNumbersRepresentedByLL();
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
			resultedLL.addItem(sum % 10);
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
		
		AddTwoNumbersRepresentedByLL llist1 = new AddTwoNumbersRepresentedByLL();
		llist1.addItem(4);
		llist1.addItem(3);
		llist1.addItem(2);
		llist1.addItem(1);
		
		System.out.print("\n Linked list: ");
		for (Node i : llist1)
			System.out.print(i.data + " ");
		
		AddTwoNumbersRepresentedByLL llist2 = new AddTwoNumbersRepresentedByLL();
		llist2.addItem(7);
		llist2.addItem(6);
		llist2.addItem(5);

		System.out.print("\n Linked list: ");
		for (Node i : llist2)
			System.out.print(i.data + " ");
		
		System.out.println("\nSum of 4321 + 765 = 5086" );
		
		AddTwoNumbersRepresentedByLL addedLL = new AddTwoNumbersRepresentedByLL();
		addedLL.head = addedLL.add(llist1.head, llist2.head);
		System.out.print("After adding the new linked list is: ");
		for (Node i : addedLL)
			System.out.print(i.data + " ");
	}
	
	
	

}
