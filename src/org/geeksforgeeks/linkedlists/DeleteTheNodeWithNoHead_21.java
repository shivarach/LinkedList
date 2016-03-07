package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

/**
 * Given only a pointer/reference to a node to be deleted in a singly linked
 * list, how do you delete it?
 * 
 * @author Shiva R
 *
 * @param <Item>
 */
public class DeleteTheNodeWithNoHead_21<Item extends Comparable<Item>>
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

	/**
	 * Deletes the specified node in O(1) time. No head pointer is available(so
	 * shouldn't tarverse), only reference to that node is given
	 * 
	 * @param node
	 */
	public void deleteNode(Node node) {
		node.data = node.next.data;
		node.next = node.next.next;
	}

	public Node addItem(Item item) {
		Node temp = new Node(item);
		temp.next = head;
		head = temp;
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

		DeleteTheNodeWithNoHead_21<Integer> llist = new DeleteTheNodeWithNoHead_21<Integer>();
		llist.addItem(7);
		llist.addItem(6);
		llist.addItem(5);
		llist.addItem(4);
		DeleteTheNodeWithNoHead_21<Integer>.Node node = llist.addItem(3);
		llist.addItem(2);
		llist.addItem(1);

		System.out.print("\n Linked list: ");
		for (Integer i : llist)
			System.out.print(i + " ");

		llist.deleteNode(node);
		
		System.out.print("\n After calling deleteNode(node) Linked list: ");
		for (Integer i : llist)
			System.out.print(i + " ");
	}

}
