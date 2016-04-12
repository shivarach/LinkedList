package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

/**
 * Merge sort of sll T: O(nlogn) S: O(1) Note: Mergesort of sll doesn't require
 * extra space
 * 
 * @author kmaram
 *
 * @param <Item>
 */
public class MergeSortOfSLL_34<Item extends Comparable<Item>> implements
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

	public void mergeSort() {

		head = mergeSort(head);
	}

	private Node mergeSort(Node head1) {
		if (head1 == null || head1.next == null)
			return head1;
		Node a = head1;
		Node b = divide(head1);
		Node a1 = mergeSort(a);
		Node b1 = mergeSort(b);
		return merge(a1, b1);
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
	private Node merge(Node a, Node b) {
		Node left = a, right = b;
		Node head = new Node(null);
		Node tail = head;
		while (true) {
			if (left == null) {
				tail.next = right;
				return head.next;
			} else if (right == null) {
				tail.next = left;
				return head.next;
			} else if (left.data.compareTo(right.data) <= 0) {
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

		MergeSortOfSLL_34<Integer> llist = new MergeSortOfSLL_34<Integer>();
		llist.addItem(6);
		llist.addItem(5);
		llist.addItem(3);
		llist.addItem(8);
		llist.addItem(1);
		llist.addItem(7);
		llist.addItem(4);

		System.out.print("\n Linked list: ");
		for (Integer i : llist)
			System.out.print(i + " ");

		System.out
				.println("\n\n############# After merge sort ##############\n");
		llist.mergeSort();
		System.out.print("\n Linked list: ");
		for (Integer i : llist)
			System.out.print(i + " ");
	}

}
