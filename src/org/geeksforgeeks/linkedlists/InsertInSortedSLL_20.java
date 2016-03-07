package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

public class InsertInSortedSLL_20<Item extends Comparable<Item>> implements
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

	public void addItemIntoSortedList(Item item) {
		Node current;
		if (head == null || head.data == item) {
			addItem(item);
			return;
		} else {
			current = head;
			while ((current.next != null)
					&& (current.next.data.compareTo(item) <= 0))
				current = current.next;

			Node temp = new Node(item);
			temp.next = current.next;
			current.next = temp;
		}
	}

	/**
	 * Adds item at first
	 * 
	 * @param item
	 */
	public void addItem(Item item) {
		Node temp = new Node(item);
		temp.next = head;
		head = temp;
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

		InsertInSortedSLL_20<Integer> llist = new InsertInSortedSLL_20<Integer>();
		llist.addItem(70);
		llist.addItem(60);
		llist.addItem(50);
		llist.addItem(40);
		llist.addItem(30);
		llist.addItem(20);
		llist.addItem(10);

		System.out.print("\n Linked list ");
		for (Integer i : llist)
			System.out.print(i + " ");

		llist.addItemIntoSortedList(5);
		llist.addItemIntoSortedList(35);
		llist.addItemIntoSortedList(80);

		System.out
				.print("\n After adding 5, 35 and 80 into the sorted Linked list: ");
		for (Integer i : llist)
			System.out.print(i + " ");
	}

}
