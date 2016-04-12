package org.geeksforgeeks.linkedlists;

import java.util.Iterator;

/**
 * Given a linked list of co-ordinates where adjacent points either form a
 * vertical line or a horizontal line. Delete points from the linked list which
 * are in the middle of a horizontal or vertical line.
 * 
 * Examples:
 * 
 * Input: (0,10)->(1,10)->(5,10)->(7,10) 
 * 									| 
 * 								  (7,5)->(20,5)->(40,5) 
 * Output: Linked List should be changed to following (0,10)->(7,10) | (7,5)->(40,5) The given
 * linked list represents a horizontal line from (0,10) to (7, 10) followed by a
 * vertical line from (7, 10) to (7, 5), followed by a horizontal line from (7,
 * 5) to (40, 5).
 * 
 * Input: (2,3)->(4,3)->(6,3)->(10,3)->(12,3) 
 * Output: Linked List should be
 * changed to following (2,3)->(12,3) There is only one vertical line, so all
 * middle points are removed.
 * 
 * @author Shiva
 *
 * @param <Item>
 */
public class RemoveMiddleNodesInLineSegmetns_53<Item extends Comparable<Item>>
		implements Iterable<RemoveMiddleNodesInLineSegmetns_53<Item>.Node> {

	private Node head = null;

	class Node {
		public Item x;
		public Item y;
		public Node next;

		public Node(Item x, Item y) {
			this.x = x;
			this.y = y;
			this.next = null;
		}
	}

	public void addItem(Item x, Item y) {
		Node temp = new Node(x, y);
		temp.next = head;
		head = temp;
	}

	public Node removeMiddleNodes(Node head) throws Exception {
		Node ref = head;
		if (ref == null || ref.next == null)
			return head;
		Node tmp = head.next;
		// consider first two nodes and set isHorizontal
		boolean isHorizontal;
		if(head.x.compareTo(tmp.x) == 0)
			isHorizontal = false;
		else if(head.y.compareTo(tmp.y) == 0)
			isHorizontal = true;
		else
			throw new Exception("Invalid input!");
		//now run through end of the list
		while(tmp != null && tmp.next != null) {
			// tmp is horizontal to ref
			if(tmp.next.y.compareTo(tmp.y) == 0) {
				//ref is also horizontal
				if (isHorizontal)
					tmp = tmp.next;
				else {//ref is vertical
					ref.next = tmp;
					ref = tmp;
					isHorizontal = true;
					tmp = tmp.next;
					
				}
			}
			// tmp is vertical to ref
			else if(tmp.next.x.compareTo(tmp.x) == 0) {
				//ref is also vertical
				if (!isHorizontal)
					tmp = tmp.next;
				else {//ref is horizontal
					ref.next = tmp;
					ref = tmp;
					isHorizontal = false;
					tmp = tmp.next;
					
				}
			}
			else
				throw new Exception("Invalid input!");
		}
		// last node
		if (ref.x.compareTo(tmp.x) == 0 || ref.y.compareTo(tmp.y) == 0)
			ref.next = tmp;
		else
			throw new Exception("Invalid input!");
		return head;
	}

	@Override
	public Iterator<Node> iterator() {
		return new Iterator<Node>() {
			Node current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Node next() {
				Node temp = current;
				current = current.next;
				return temp;
			}
		};
	}

	public static void main(String[] args) throws Exception {

		RemoveMiddleNodesInLineSegmetns_53<Integer> llist = new RemoveMiddleNodesInLineSegmetns_53<Integer>();
		llist.addItem(40, 5);
		llist.addItem(20, 5);
		llist.addItem(7, 5);
		llist.addItem(7, 10);
		llist.addItem(5, 10);
		llist.addItem(1, 10);
		llist.addItem(0, 10);

		
		 System.out.print("\n Linked list: "); 
		 for (RemoveMiddleNodesInLineSegmetns_53<Integer>.Node i : llist)
			 System.out.print("(" + i.x + ", " + i.y + ") ");
		 
		 System.out.println("\n Linked list after deletin middle nodes: ");
		 llist.removeMiddleNodes(llist.head);
		 for (RemoveMiddleNodesInLineSegmetns_53<Integer>.Node i : llist)
			 System.out.print("(" + i.x + ", " + i.y + ") ");
		 
	}

}
