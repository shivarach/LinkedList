package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

/**
 * Intersection of two Sorted Linked Lists Given two lists sorted in increasing
 * order, create and return a new list representing the intersection of the two
 * lists. The new list should be made with its own memory — the original lists
 * should not be changed.
 * 
 * For example, let the first linked list be 1->2->3->4->6 and second linked
 * list be 2->4->6->8, then your function should create and return a third list
 * as 2->4->6.
 * 
 * This is iterative approach
 * T:O(n)
 * S:O(1)
 * @author Shiva
 *
 * @param <Item>
 */
public class IntersectionOfTwoSortedSLLsIterative_30<Item extends Comparable<Item>>
		implements Iterable<Item> {

	private Node head = null, tail = null;

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
	
	public void addItemAtLast(Item item) {
		
		Node temp = new Node(item); 
		if (tail == null){
			head = temp;
			tail = temp;
			return;
		}
		tail.next = temp;
		tail = temp;
		
	}
	
	private Node findIntersection(Node head1, Node head2) {
		Node left = head1, right = head2;
		IntersectionOfTwoSortedSLLsIterative_30<Item> resultedLL = new IntersectionOfTwoSortedSLLsIterative_30<Item>();
		while ( left != null && right != null) {
			if (left.data.compareTo(right.data) < 0)
				left = left.next;
			else if ( left.data.compareTo(right.data) > 0)
				right = right.next;
			else {
				resultedLL.addItemAtLast(left.data);
				left = left.next;
				right = right.next;
			}
		}
			
		return resultedLL.head;
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

		IntersectionOfTwoSortedSLLsIterative_30<Integer> llist = new IntersectionOfTwoSortedSLLsIterative_30<Integer>();
		llist.addItem(6);
		llist.addItem(4);
		llist.addItem(3);
		llist.addItem(2);
		llist.addItem(1);

		System.out.print("\n Linked list: ");
		for (Integer i : llist)
			System.out.print(i + " ");
		
		IntersectionOfTwoSortedSLLsIterative_30<Integer> llist2 = new IntersectionOfTwoSortedSLLsIterative_30<Integer>();
		llist2.addItem(8);
		llist2.addItem(6);
		llist2.addItem(4);
		llist2.addItem(2);

		System.out.print("\n Linked list: ");
		for (Integer i : llist2)
			System.out.print(i + " ");
		
		IntersectionOfTwoSortedSLLsIterative_30<Integer> resultedLL = new IntersectionOfTwoSortedSLLsIterative_30<Integer>();
		resultedLL.head = resultedLL.findIntersection(llist.head, llist2.head);
		System.out.print("\n\nIntersection of above two linked lists is : ");
		for (Integer i : resultedLL)
			System.out.print(i + " ");
		
		
	}

	

}
