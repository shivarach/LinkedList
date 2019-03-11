package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

/**
Look all the three approaches in https://www.geeksforgeeks.org/union-and-intersection-of-two-linked-lists/

 * Given two Linked Lists, create union and intersection lists that contain
 * union and intersection of the elements present in the given lists. Order of
 * elments in output lists doesn’t matter. (Assumption is evry ll doesn't have
 * any duplicates)
 * 
 * Example:
 * 
 * Input: List1: 10->15->4->20 lsit2: 8->4->2->10 Output: Intersection List:
 * 4->10 Union List: 2->8->20->4->15->10
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class IntersectionAndUnionOfTwoLLs_42<Item extends Comparable<Item>>
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

	/**
	 * Finds union of two lls(Assumption is evry ll doesn't have any duplicates)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public Node findUnion(IntersectionAndUnionOfTwoLLs_42<Item> a,
			IntersectionAndUnionOfTwoLLs_42<Item> b) {
		// Sort two lls
		a.mergeSort();
		b.mergeSort();

		Node a1 = a.head, b1 = b.head;
		while (true) {
			if (a1 == null)
				return addReminingItems(b1);
			else if (b1 == null)
				return addReminingItems(a1);
			else if (a1.data.compareTo(b1.data) < 0) {
				addItem(a1.data);
				a1 = a1.next;
			} else if (a1.data.compareTo(b1.data) > 0) {
				addItem(b1.data);
				b1 = b1.next;
			} else {
				addItem(a1.data);
				a1 = a1.next;
				b1 = b1.next;
			}
		}
	}

	/**
	 * Finds intersection of two linked lists(Assumption is evry ll doesn't have
	 * any duplicates)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public IntersectionAndUnionOfTwoLLs_42<Item> findIntersection(
			IntersectionAndUnionOfTwoLLs_42<Item> a,
			IntersectionAndUnionOfTwoLLs_42<Item> b) {
		// Sort two lls
		a.mergeSort();
		b.mergeSort();

		Node a1 = a.head, b1 = b.head;
		IntersectionAndUnionOfTwoLLs_42<Item> intersectionList = new IntersectionAndUnionOfTwoLLs_42<Item>();
		while (a1 != null && b1 != null) {
			int cmp = a1.data.compareTo(b1.data);
			if (cmp < 0)
				a1 = a1.next;
			else if (cmp > 0)
				b1 = b1.next;
			else {
				intersectionList.addItem(a1.data);
				a1 = a1.next;
				b1 = b1.next;
			}
		}
		return intersectionList;
	}

	private Node addReminingItems(Node temp) {
		while (temp != null) {
			addItem(temp.data);
			temp = temp.next;
		}
		return head;
	}

	/***************** merge sort starts *********************/
	public void mergeSort() {

		head = mergeSort(head);
	}

	private Node mergeSort(Node head1) {
		if (head1 == null || head1.next == null)
			return head1;
		Node a = head1;
		Node b = divide(head1);
		Node a1 = mergeSort(a);
		Node b1 = mergeSort(b);
		return merge(a1, b1);
	}

	/**
	 * Divides the linked list into two halves and returns head of second half
	 * of ll
	 * 
	 * @param head1
	 * @return
	 */
	private Node divide(Node head1) {
		if (head1 == null || head1.next == null)
			return head1;
		Node slow = head1, fast = head1.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		Node head2 = slow.next;
		slow.next = null;
		return head2;
	}

	/**
	 * merges two given sorted linked lists
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private Node merge(Node a, Node b) {
		Node left = a, right = b;
		Node head = new Node(null);
		Node tail = head;
		while (true) {
			if (left == null) {
				tail.next = right;
				return head.next;
			} else if (right == null) {
				tail.next = left;
				return head.next;
			} else if (left.data.compareTo(right.data) <= 0) {
				tail.next = left;
				left = left.next;
				tail = tail.next;
			} else {
				tail.next = right;
				right = right.next;
				tail = tail.next;
			}
		}
	}

	/****************** merge sort ends ***********************/

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

		IntersectionAndUnionOfTwoLLs_42<Integer> llist = new IntersectionAndUnionOfTwoLLs_42<Integer>();
		/*
		 * llist.addItem(7); llist.addItem(6);
		 */
		llist.addItem(5);
		llist.addItem(4);
		llist.addItem(3);
		llist.addItem(2);
		llist.addItem(1);

		System.out.print("\n Linked list1: ");
		for (Integer i : llist)
			System.out.print(i + " ");

		IntersectionAndUnionOfTwoLLs_42<Integer> llist2 = new IntersectionAndUnionOfTwoLLs_42<Integer>();
		llist2.addItem(7);
		llist2.addItem(6);
		llist2.addItem(5);
		/*
		 * llist.addItem(4); llist.addItem(3); llist.addItem(2);
		 */
		llist2.addItem(1);

		System.out.print("\n Linked list2: ");
		for (Integer i : llist2)
			System.out.print(i + " ");

		IntersectionAndUnionOfTwoLLs_42<Integer> result = new IntersectionAndUnionOfTwoLLs_42<Integer>();

		result.findUnion(llist, llist2);
		System.out
				.print("\n Uninon of above two linked lists is (order doesn't matter) : ");
		for (Integer i : result)
			System.out.print(i + " ");

		System.out
				.print("\n Intersection of above two linked lists is (order doesn't matter) : ");
		for (Integer i : result.findIntersection(llist, llist2))
			System.out.print(i + " ");
	}

}
