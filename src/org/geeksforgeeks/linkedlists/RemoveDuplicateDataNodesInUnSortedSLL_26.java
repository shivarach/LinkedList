package org.geeksforgeeks.linkedlists;

import java.util.HashSet;
import java.util.Iterator;
/**
 * Remove duplicates from an unsorted linked list
 * T:O(n)
 * S:O(n)
 * @author Shiva
 *
 * @param <Item>
 */
public class RemoveDuplicateDataNodesInUnSortedSLL_26<Item extends Comparable<Item>> implements Iterable<Item> {


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
	
	public void removeDuplicates() {
		Node temp = head, prevTemp = null;
		HashSet<Item> hs = new HashSet<Item>();
		while (temp != null) {
			if (hs.contains(temp.data)) {
				prevTemp.next = temp.next;
			}
			else {
				hs.add(temp.data);
				prevTemp = temp;
			}
			temp = temp.next;
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

		RemoveDuplicateDataNodesInUnSortedSLL_26<Integer> llist = new RemoveDuplicateDataNodesInUnSortedSLL_26<Integer>();
		llist.addItem(3);
		llist.addItem(2);
		llist.addItem(4);
		llist.addItem(1);
		llist.addItem(3);
		llist.addItem(2);
		llist.addItem(1);

		System.out.print("\n Linked list: ");
		for (Integer i : llist)
			System.out.print(i + " ");
		System.out.println("\n\n************After removing duplicates*************");
		
		llist.removeDuplicates();
		
		System.out.print("\n Linked list: ");
		for (Integer i : llist)
			System.out.print(i + " ");
	}

	

}
