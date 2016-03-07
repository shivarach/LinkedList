package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

/**
 * Reverse a linked list using tail recursion
 * TC = O (N)
 * This is to understand than {@link ReverseLLWithRecursion_16_1} and optimized(since it is tail recursion)
 * @author SHIVA
 *
 * @param <Item>
 */
public class ReverseLLWithTailRecursion_16_2<Item> implements Iterable<Item> {

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

	public void reverse() {
		reverse(null, head);
	}

	public void reverse(Node prev, Node curr) {
		if (curr.next == null) {
			head = curr;
			curr.next = prev;
			return;
		}
		Node nextP = curr.next;
		curr.next = prev;
		reverse(curr, nextP); // tail recursion(Nothing is there after this so
								// compiler optimizes means it doesn't maintain
								// stack)
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

		ReverseLLWithTailRecursion_16_2<Integer> llist = new ReverseLLWithTailRecursion_16_2<Integer>();
		llist.addItem(7);
		llist.addItem(6);
		llist.addItem(5);
		llist.addItem(4);
		llist.addItem(3);
		llist.addItem(2);
		llist.addItem(1);

		System.out.print("\n Linked list ");
		for (Integer i : llist)
			System.out.print(i + " ");
		System.out.print("\n Linked list after reversing ");
		llist.reverse();
		for (Integer i : llist)
			System.out.print(i + " ");
	}

}
