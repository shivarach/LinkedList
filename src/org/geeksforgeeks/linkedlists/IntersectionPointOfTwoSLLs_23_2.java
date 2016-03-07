package org.geeksforgeeks.linkedlists;

import java.util.HashSet;
import java.util.Iterator;
/**
 * Finds intersection of two linked lists using hash set.(It takes extra space O(n)). So {@link IntersectionPointOfTwoSLLs_23} is good choice
 * T:O(n)
 * S:O(n)
 * @author Shiva
 *
 * @param <Item>
 */
public class IntersectionPointOfTwoSLLs_23_2<Item extends Comparable<Item>> implements Iterable<Item> {


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
	
	public Node findIntersectionPoint(Node head1, Node head2) {
		Node temp = head1;
		HashSet<Node> hashSet = new HashSet<Node>();
		while (temp != null) {
			hashSet.add(temp);
			temp = temp.next;
		}
		temp = head2;
		while (temp != null) {
			if (hashSet.contains(temp))
				return temp;
			temp = temp.next;
		}
		return null;
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

		IntersectionPointOfTwoSLLs_23_2<Integer> llist = new IntersectionPointOfTwoSLLs_23_2<Integer>();
		llist.addItem(7);
		llist.addItem(6);
		llist.addItem(5);
		llist.addItem(4);
		llist.addItem(3);
		llist.addItem(2);
		llist.addItem(1);
		
		IntersectionPointOfTwoSLLs_23_2<Integer> llist2 = new IntersectionPointOfTwoSLLs_23_2<Integer>();
		llist2.addItem(30);
		llist2.addItem(20);
		llist2.addItem(10);
		
		// make an intersection
		llist2.head.next.next.next = llist.head.next.next.next.next;
		
		System.out.print("\n Linked list1 is : ");
		for (Integer i : llist)
			System.out.print(i + " ");
		
		System.out.print("\n Linked list2 is : ");
		for (Integer i : llist2)
			System.out.print(i + " ");
		
		System.out.print("\n Intersection point is : ");
		IntersectionPointOfTwoSLLs_23_2<Integer>.Node interSectionNode = new IntersectionPointOfTwoSLLs_23_2<Integer>().findIntersectionPoint(llist.head, llist2.head);
		if (interSectionNode != null)
			System.out.println(interSectionNode.data);
		else
			System.out.println(interSectionNode);
	}

	

}
