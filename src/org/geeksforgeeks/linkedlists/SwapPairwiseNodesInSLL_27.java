package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

/**
 * Swaps adjacent nodes(not data), actually data is swapped in geeksforgeeks
 * T:O(n)
 * S:O(1)
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class SwapPairwiseNodesInSLL_27<Item extends Comparable<Item>>
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
/********************************************
 *  Observe how pointer a and b 
 *********************************************/
	public void swapPairwiseNodes() {
		if (head == null || head.next == null)
			return;
		Node a = head, b = null, referenceP = null;
		while (a != null && a.next != null) {
			// a points to first element of pair at this stage
			b = a.next;
			a.next = b.next;
			b.next = a;
			// First time
			if (a == head)
				head = b;
			else
				referenceP.next = b;
			referenceP = a;
			a = a.next;
			
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

		SwapPairwiseNodesInSLL_27<Integer> llist = new SwapPairwiseNodesInSLL_27<Integer>();
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
		System.out.println("\n\n**************After Swapping**********************");
		
		llist.swapPairwiseNodes();
		
		System.out.print("\n Linked list: ");
		for (Integer i : llist)
			System.out.print(i + " ");
		
	}

}
