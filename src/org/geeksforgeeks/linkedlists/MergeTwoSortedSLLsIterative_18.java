package org.geeksforgeeks.linkedlists;

import java.util.Iterator;
/**
 * Merge two sorted linked lists (It doesn't create new ll)
 * Find {@link MergeTwoSortedSLLsRecursive_18} is easy to understand(recursive approach but inefficient when compared with iterative)
 * 
 * @author Siva
 *
 * @param <Item>
 */
public class MergeTwoSortedSLLsIterative_18<Item extends Comparable<Item>> implements Iterable<Item> {


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
	private Node mergeSortedLinkedlists(Node head2, Node head3) {
		Node head = new Node(null);
		Node tail = head, x = head2, y = head3;

		while (true) {
			if (x == null) {
				tail.next = y;
				return head.next;
			}
			else if (y == null) {
				tail.next = x;
				return head.next;
			}
			
			if (y.data.compareTo(x.data) <= 0) {
				tail.next = y;
				tail = tail.next;
				y = y.next;
			}
			else {
				tail.next = x;
				tail = tail.next;
				x = x.next;
			}
		}
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
		
		MergeTwoSortedSLLsIterative_18<Integer> llist = new MergeTwoSortedSLLsIterative_18<Integer>();
		llist.addItem(7);
		llist.addItem(5);
		llist.addItem(3);
		llist.addItem(1);
		llist.addItem(-1);

		MergeTwoSortedSLLsIterative_18<Integer> llist1 = new MergeTwoSortedSLLsIterative_18<Integer>();
		llist1.addItem(6);
		llist1.addItem(4);
		llist1.addItem(2);
		llist1.addItem(1);

		System.out.print("\n Linked list-1: ");
		for (Integer i : llist)
			System.out.print(i + " ");
		System.out.println();
		System.out.print("\n Linked list-2: ");
		for (Integer i : llist1)
			System.out.print(i + " ");
		System.out.println();
		
		MergeTwoSortedSLLsIterative_18<Integer> mergedList = new MergeTwoSortedSLLsIterative_18<Integer>();
		mergedList.head = mergedList.mergeSortedLinkedlists(llist.head, llist1.head);
		System.out.print("\n Merged Linked list: ");
		for (Integer i : mergedList)
			System.out.print(i + " ");
	}

	

}
