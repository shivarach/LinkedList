package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

/**
 * Delete alternate nodes of a Linked List in iterative way
 * Given a Singly Linked List, starting
 * from the second node delete all alternate nodes of it. For example, if the
 * given linked list is 1->2->3->4->5 then your function should convert it to
 * 1->3->5, and if the given linked list is 1->2->3->4 then convert it to 1->3.
 * 
 * T:O(n/2)
 * S:O(1)
 * @author Shiva
 *
 * @param <Item>
 */
public class DeleteAlternateNodes_Iterative_31<Item extends Comparable<Item>>
		implements Iterable<Item> {

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

	public Node deleteAlternateNodes(Node temp) {
		while (temp != null && temp.next != null) {
			temp.next = temp.next.next;
			temp = temp.next;
		}
		return temp;
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

		DeleteAlternateNodes_Iterative_31<Integer> llist = new DeleteAlternateNodes_Iterative_31<Integer>();
		llist.addItem(7);
		llist.addItem(6);
		llist.addItem(5);
		llist.addItem(4);
		llist.addItem(3);
		llist.addItem(2);
		llist.addItem(1);

		System.out.print("\n Linked list: ");
		for (Integer i : llist)
			System.out.print(i + " ");

		System.out.print("\n\nAfter deleting alternate nodes: ");
		llist.deleteAlternateNodes(llist.head);
		for (Integer i : llist)
			System.out.print(i + " ");
	}

}
