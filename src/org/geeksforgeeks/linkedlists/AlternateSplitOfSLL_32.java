package org.geeksforgeeks.linkedlists;

import java.util.Iterator;
/**
 * Alternating split of a given Singly Linked List
 * @author Shiva
 *
 * @param <Item>
 */
public class AlternateSplitOfSLL_32<Item extends Comparable<Item>> implements
		Iterable<Item> {

	private Node head = null;
	
	public AlternateSplitOfSLL_32() {
	}
	public AlternateSplitOfSLL_32(AlternateSplitOfSLL_32<Item>.Node head) {
		super();
		this.head = head;
	}

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
/**
 * Splits given linked list and return head pointer to second splitted linked list
 * @return head pointer to second splitted linked list
 */
	public Node splitAlternatively() {
		if (head == null || head.next == null)
			return null;
		Node a = head, b = head.next, head2 = b;
		while (b != null && b.next != null) {
			a.next = b.next;
			a = a.next;
			b.next = a.next;
			b = b.next;
		}
		return head2;
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

		AlternateSplitOfSLL_32<Integer> llist = new AlternateSplitOfSLL_32<Integer>();
		llist.addItem(0);
		llist.addItem(1);
		llist.addItem(0);
		llist.addItem(1);
		llist.addItem(0);
		llist.addItem(1);
		llist.addItem(0);

		System.out.print("\n Linked list: ");
		for (Integer i : llist)
			System.out.print(i + " ");
		System.out
				.println("\n############# After splitting the linked list #############");
		AlternateSplitOfSLL_32<Integer>.Node head2 = llist.splitAlternatively();
		AlternateSplitOfSLL_32<Integer> llist2 = new AlternateSplitOfSLL_32<Integer>(head2);

		System.out.print("Linkedlist 1: ");
		for (Integer i : llist)
			System.out.print(i + " ");
		
		System.out.print("\nLinkedlist 2: ");
		for (Integer i : llist2)
			System.out.print(i + " ");
	}

}
