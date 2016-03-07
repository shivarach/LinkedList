package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

public class MergeTwoSortedSLLsRecursive_18<Item extends Comparable<Item>> implements Iterable<Item> {


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
		Node result = null, x = head2, y = head3;
		if (x == null)
			return y;
		if (y == null)
			return x;
		if (x.data.compareTo(y.data) <= 0) {
			result = x;
			result.next = mergeSortedLinkedlists(x.next, y);
		} else {
			result = y;
			result.next = mergeSortedLinkedlists(x, y.next);
		}
		return result;
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
		
		MergeTwoSortedSLLsRecursive_18<Integer> llist = new MergeTwoSortedSLLsRecursive_18<Integer>();
		llist.addItem(7);
		llist.addItem(5);
		llist.addItem(3);
		llist.addItem(1);
		llist.addItem(-1);

		MergeTwoSortedSLLsRecursive_18<Integer> llist1 = new MergeTwoSortedSLLsRecursive_18<Integer>();
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
		
		MergeTwoSortedSLLsRecursive_18<Integer> mergedList = new MergeTwoSortedSLLsRecursive_18<Integer>();
		mergedList.head = mergedList.mergeSortedLinkedlists(llist.head, llist1.head);
		System.out.print("\n Merged Linked list: ");
		for (Integer i : mergedList)
			System.out.print(i + " ");
	}

	

}
