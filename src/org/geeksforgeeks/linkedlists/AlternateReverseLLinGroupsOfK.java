package org.geeksforgeeks.linkedlists;

import java.security.InvalidParameterException;
import java.util.Iterator;
/**
 * Given a linked list, write a function to reverse every alternate k nodes (where k is an input to the function) in an efficient way. Give the complexity of your algorithm.

Example:
Inputs:   1->2->3->4->5->6->7->8->9->NULL and k = 3
Output:   3->2->1->4->5->6->9->8->7->NULL. 
T: O(n)
S: O(1)
 * @author Shiva
 *
 * @param <Item>
 */
public class AlternateReverseLLinGroupsOfK<Item extends Comparable<Item>> implements Iterable<Item> {


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
	
	public void altReverseInGroupsOfK(int k)
	{
		if (k == 0)
			throw new InvalidParameterException("Invalid input: " + k);
		this.head = altReverseInGroupsOfK(head, k);
	}
	private Node altReverseInGroupsOfK(Node head, int k) {
		Node cur = head;
		Node prev = null;
		Node next = null;
		int count = 0;
		while ( count < k && cur != null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
			count++;
		}
		if (head != null)
			head.next = cur;
		// here count > 1 because in above while loop cur points to k + 1th node
		while (count > 1 && cur != null) {
			cur = cur.next;
			count--;
		}
		
		if (cur != null) {
			cur.next = altReverseInGroupsOfK(cur.next, k);
		}
		return prev;
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

		AlternateReverseLLinGroupsOfK<Integer> llist = new AlternateReverseLLinGroupsOfK<Integer>();
		llist.addItem(10);
		llist.addItem(9);
		llist.addItem(8);
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
		
		System.out.println("\nAfter reversing in groups of 3:");
		llist.altReverseInGroupsOfK(3);
		for (Integer i : llist)
			System.out.print(i + " ");
	}
	

}
