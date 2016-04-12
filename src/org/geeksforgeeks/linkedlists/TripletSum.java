package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

/**
 * Given three linked lists, say a, b and c, find one node from each list such
 * that the sum of the values of the nodes is equal to a given number. For
 * example, if the three linked lists are 12->6->29, 23->5->8 and 90->20->59,
 * and the given number is 101, the output should be tripel "6 5 90".
 * 
 * Algorithm: Reger quadratic algorithm of 3-sum problem in wikipedia
 * 
 * T:O(M * max(P, Q)) i.e. n^2(assuming size of each(three) linked lists is n)
 * S:O(1)
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class TripletSum implements Iterable<TripletSum.Node> {

	private Node head = null;

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
/**
 * finds triplet in n^2 time. Refer quadratic algorithm of 3-sum problem
 * @param a
 * @param b
 * @param c
 * @param sum
 * @throws Exception
 */
	public void findTriplet(TripletSum a, TripletSum b, TripletSum c, int sum) throws Exception {
		// sort b in ascending order
		b.mergeSort(true);
		// sort c in descending order
		c.mergeSort(false);
		// traverse through a and find a.data + b.data + c.data = sum exists
		for (Node x = a.head; x != null; x = x.next) {
			Node y = b.head, z = c.head;
			while (y != null && z != null) {
				int tempSum = x.data + y.data + z.data;
				if (tempSum < sum)
					y = y.next;
				else if (tempSum > sum)
					z = z.next;
				else {
					System.out.println(x.data + ", " + y.data + ", " + z.data);
					y = y.next;
					z = z.next;
				}
			}
		}
	}

	/***************** merge sort starts *********************/
	/**
	 * pass true to sort in ascending order and false to sort in descending
	 * order
	 * 
	 * @param asc
	 */
	public void mergeSort(boolean asc) {

		head = mergeSort(head, asc);
	}

	private Node mergeSort(Node head1, boolean asc) {
		if (head1 == null || head1.next == null)
			return head1;
		Node a = head1;
		Node b = divide(head1);
		Node a1 = mergeSort(a, asc);
		Node b1 = mergeSort(b, asc);
		return merge(a1, b1, asc);
	}

	/**
	 * Divides the linked list into two halves and returns head of second half
	 * of ll
	 * 
	 * @param head1
	 * @return
	 */
	private Node divide(Node head1) {
		if (head1 == null || head1.next == null)
			return head1;
		Node slow = head1, fast = head1.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		Node head2 = slow.next;
		slow.next = null;
		return head2;
	}

	/**
	 * merges two given sorted linked lists
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private Node merge(Node a, Node b, boolean asc) {
		Node left = a, right = b;
		Node head = new Node(0);
		Node tail = head;
		while (true) {
			if (left == null) {
				tail.next = right;
				return head.next;
			} else if (right == null) {
				tail.next = left;
				return head.next;
				// if asc = true sorts in ascending order else sorts in
				// descending order
			} else if (asc == true ? left.data <= right.data
					: left.data >= right.data) {
				tail.next = left;
				left = left.next;
				tail = tail.next;
			} else {
				tail.next = right;
				right = right.next;
				tail = tail.next;
			}
		}
	}

	/****************** merge sort ends ***********************/

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

	public static void main(String[] args) throws Exception {

		TripletSum llist = new TripletSum();
		llist.addItem(29);
		llist.addItem(6);
		llist.addItem(12);
		llist.addItem(50);
		System.out.print("\n Linked list1: ");
		for (Node i : llist)
			System.out.print(i.data + " ");
		
		TripletSum llist2 = new TripletSum();
		llist2.addItem(8);
		llist2.addItem(5);
		llist2.addItem(23);
		llist2.addItem(50);
		
		System.out.print("\n Linked list: ");
		for (Node i : llist2)
			System.out.print(i.data + " ");
		
		TripletSum llist3 = new TripletSum();
		llist3.addItem(59);
		llist3.addItem(20);
		llist3.addItem(90);
		llist3.addItem(1);

		System.out.print("\n Linked list: ");
		for (Node i : llist3)
			System.out.print(i.data + " ");

		TripletSum result = new TripletSum();
		System.out.println("\nTriplet sum(s): ");
		result.findTriplet(llist, llist2, llist3, 101);
		
	}

}
