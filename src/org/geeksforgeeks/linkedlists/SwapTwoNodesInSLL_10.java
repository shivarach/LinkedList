package org.geeksforgeeks.linkedlists;

/**
 * Swap nodes in a linked list without swapping data
 * @author Suresh Baga
 *
 * @param <Item>
 */
public class SwapTwoNodesInSLL_10<Item> {
	Node head;
	private class Node {
		private Item data;
		private Node next;
		
		Node(Item data) {
			this.data = data;
		}
	}
	/**
	 * adds x at first
	 * @param x integer
	 */
	private void addItem(Item x) {
		Node temp = new Node(x);
		temp.next = head;
		head = temp;
	}
	
	public void swapNodes(Item x, Item y) {
		if (x == y) return; //if x and y are same do nothing
		Node prevX = null, currX = head, prevY = null, currY = head;
		
		// find prevX and currX
		while (currX != null && currX.data != x) {
			prevX = currX;
			currX = currX.next;
		}

		// find prevY and currY
		while (currY != null && currY.data != y) {
			prevY = currY;
			currY = currY.next;
		}
		
		// if either x or y not present, nothing to do
		if (currX == null || currY == null)
			return;
		
		// if x is not head of linkedlist
		if (prevX != null)
			prevX.next = currY;
		else // x is head 
			head = currY;
		
		// if y is not head of ll
		if (prevY != null)
			prevY.next = currX;
		else // y is head 
			head = currX;
		
		//swap next pointers
		Node temp = currX.next;
		currX.next = currY.next;
		currY.next = temp;
		
	}
	
	private void printList() {
		for (Node temp = head; temp != null; temp = temp.next)
			System.out.print(temp.data + " ");
		System.out.println();
	}
	public static void main(String[] args) {
		SwapTwoNodesInSLL_10<Integer> llist = new SwapTwoNodesInSLL_10<Integer>();
		 
        /* The constructed linked list is:
            1->2->3->4->5->6->7 */
        llist.addItem(7);
        llist.addItem(6);
        llist.addItem(5);
        llist.addItem(4);
        llist.addItem(3);
        llist.addItem(2);
        llist.addItem(1);
 
        System.out.print("\n Linked list before calling swapNodes() ");
        llist.printList();
 
        llist.swapNodes(7, 1);
 
        System.out.print("\n Linked list after calling swapNodes() ");
        llist.printList();
    }

}

