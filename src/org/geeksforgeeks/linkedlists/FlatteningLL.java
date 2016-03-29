package org.geeksforgeeks.linkedlists;

import java.util.Iterator;
/**Given a linked list where every node represents a linked list and contains two pointers of its type:
(i) Pointer to next node in the main list (we call it ‘right’ pointer in below code)
(ii) Pointer to a linked list where this node is head (we call it ‘down’ pointer in below code).
 * 
 * All linked lists are sorted. See the following example

       5 -> 10 -> 19 -> 28
       |    |     |     |
       V    V     V     V
       7    20    22    35
       |          |     |
       V          V     V
       8          50    40
       |                |
       V                V
       30               45
Write a function flatten() to flatten the lists into a single linked list. The flattened linked list should also be sorted. 
For example, for the above input list, output list should be 5->7->8->10->19->20->22->28->30->35->40->45->50.
 * @author Shiva
 *
 * @param <Item>
 */
public class FlatteningLL<Item extends Comparable<Item>> implements
		Iterable<FlatteningLL.Node> {

	private Node head = null;

	class Node {
		public Item data;
		public Node right;
		public Node down;

		public Node(Item data) {
			this.data = data;
			this.right = null;
			this.down = null;
		}
	}

	public void pushDown(Item item) {
		Node tmp = new Node(item);
		tmp.down = head;
		tmp.right = head.right;
		head.right = null;
		head = tmp;
	}

	public void pushRight(Item item) {
		Node tmp = new Node(item);
		tmp.right = head;
		head = tmp;
	}
/**
 * flatten the lists into a single linked list. The flattened linked list should also be sorted 
 * @param head
 * @return
 */
	public Node flatten(Node head) {
		Node current = head;
		while (current != null && current.right != null) {
			Node tmp = current.right.right;
			current = merge(current, current.right);
			current.right = tmp;
		}
		return head;
	}
/**
 * merge sort's merge
 * @param current
 * @param right
 * @return
 */
	private FlatteningLL<Item>.Node merge(FlatteningLL<Item>.Node current,
			FlatteningLL<Item>.Node right) {
		Node a = current, b = right;
		Node head = new Node(null);
		Node tail = head;
		while (true) {
			if (a == null) {
				tail.down = b;
				return head.down;
			} else if (b == null) {
				tail.down = a;
				return head.down;
			} else if (a.data.compareTo(b.data) <= 0) {
				tail.down = a;
				a = a.down;
				tail = tail.down;
			} else {
				tail.down = b;
				b = b.down;
				tail = tail.down;
			}
		}
	}

	@Override
	public Iterator<FlatteningLL.Node> iterator() {
		return new Iterator<FlatteningLL.Node>() {
			Node current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public FlatteningLL.Node next() {
				Node temp = current;
				current = current.right;
				return temp;
			}
		};
	}

	public static void main(String[] args) {

		FlatteningLL<Integer> llist = new FlatteningLL<Integer>();
		/**
	   5 -> 10 -> 19 -> 28
       |    |     |     |
       V    V     V     V
       7    20    22    35
       |          |     |
       V          V     V
       8          50    40
       |                |
       V                V
       30               45
		 */
		llist.pushRight(45);
		llist.pushDown(40);
		llist.pushDown(35);
		llist.pushDown(28);

		llist.pushRight(50);
		llist.pushDown(22);
		llist.pushDown(19);

		llist.pushRight(20);
		llist.pushDown(10);

		llist.pushRight(30);
		llist.pushDown(8);
		llist.pushDown(7);
		llist.pushDown(5);

		System.out.print("\n Linked list: ");
		for (FlatteningLL.Node i : llist)
			while (i != null) {
				System.out.print(i.data + " ");
				i = i.down;
			}

		System.out.print("\n Linked list after flattning: ");
		llist.flatten(llist.head);
		llist.printDown();
	}

	private void printDown() {
		Node tmp = head;
		while (tmp != null) {
			System.out.print(tmp.data + " ");
			tmp = tmp.down;
		}
		
	}

}
