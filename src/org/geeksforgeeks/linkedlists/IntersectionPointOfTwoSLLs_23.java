package org.geeksforgeeks.linkedlists;

import java.util.Iterator;
/**
 * Finds intersection node of two linked lists.
 * T:O(n)
 * S:O(1)
 * @author Shiva
 *
 * @param <Item>
 */
public class IntersectionPointOfTwoSLLs_23<Item extends Comparable<Item>> implements
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

	public void addItem(Item item) {
		Node temp = new Node(item);
		temp.next = head;
		head = temp;
	}
/**
 * 
 * @param head1
 * @param head2
 * @return
 */
	public Node findIntersectionPoint(Node head1, Node head2) {
		int x = 0, y = 0, diff = 0;
		// find length of ll1
		Node temp = head1;
		while (temp != null) {
			x++;
			temp = temp.next;
		}
		// find length of ll2
		temp = head2;
		while (temp != null) {
			y++;
			temp = temp.next;
		}
		// find difference, bigger ll and smaller ll
		Node bigList, smallList;
		if (x > y) {
			diff = x - y;
			bigList = head1;
			smallList = head2;
		} else {
			diff = y - x;
			bigList = head2;
			smallList = head1;
		}
		// move bigList to the size of diff so that both of the pointers bigList
		// and smallList would be at distance from intersecting node
		for (int i = 0; i < diff; i++)
			bigList = bigList.next;

		// Now move move until intersecting point reches.
		while (bigList != smallList) {
			bigList = bigList.next;
			smallList = smallList.next;
		}
		// bigList returns null if two linked lists doesn't intersect
		return bigList;
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

		IntersectionPointOfTwoSLLs_23<Integer> llist = new IntersectionPointOfTwoSLLs_23<Integer>();
		llist.addItem(7);
		llist.addItem(6);
		llist.addItem(5);
		llist.addItem(4);
		llist.addItem(3);
		llist.addItem(2);
		llist.addItem(1);
		
		IntersectionPointOfTwoSLLs_23<Integer> llist2 = new IntersectionPointOfTwoSLLs_23<Integer>();
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
		IntersectionPointOfTwoSLLs_23<Integer>.Node interSectionNode = new IntersectionPointOfTwoSLLs_23<Integer>().findIntersectionPoint(llist.head, llist2.head);
		if (interSectionNode != null)
			System.out.println(interSectionNode.data);
		else
			System.out.println(interSectionNode);
	}

	

}
