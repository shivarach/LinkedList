package org.geeksforgeeks.linkedlists;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Single linked list implementation
 * @author Shiva
 *
 * @param <T>
 */
public class MySingleLinkedList<Item extends Comparable<Item>> implements Iterable<Item> {
	
	private int numOfElements; // number of elements in linked list
	public Node head; // head node in linked list
	
	public class Node {
		private Item item;
		private Node next;
		
		public Node(Item item) {
			this.item = item;
			this.next = null;
		}
	}
	
	public MySingleLinkedList() {
		head = null;
		numOfElements = 0;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int size() {
		return numOfElements;
	}
	
	
	/**
	 * Adds the node at first by default
	 * @param data
	 */
	// O(1)
	public void add(Item data) {
		addFirst(data);
	}

	/**
	 * Adds the node at first
	 * @param data
	 */
	//O(1)
	private void addFirst(Item data) {
		Node newNode = new Node(data);
		newNode.next = head;
		head = newNode;
	}
	
	/**
	 * Adds the element at last
	 * @param data
	 */
	// O(n)
	public void addLast(Item data) {
		Node temp = head;
		if (head == null)
			addFirst(data);
		else {
			while (temp.next != null)
				temp = temp.next;
			Node newNode = new Node(data);
			temp.next = newNode;
		}
			
		
	}
	
	/**
	 * Returns first element in linked list
	 * @return
	 */
	// O(1)
	public Item getFirst() {
		return head.item;
	}
	
	/**
	 * Returns last element in linked list
	 * @return
	 */
	// O(n)
	public Item getLast() {
		if (head == null)
            		throw new NullPointerException("No elements in linked list");
		Node temp = head;
		while(temp.next != null)
			temp = temp.next;
		return temp.item;
	}
	
	/**
	 * Removes first element in the linked list
	 */
	// O(1)
	public void removeFirst() {
		if (head == null)
			throw new NullPointerException("No elements in linked list");
		head = head.next;
	}
	
	/**
	 * Removes last element in the linked list
	 */
	// O(n)
	public void removeLast() {
		if (head == null)
			throw new NullPointerException("No elements in linked list");
		Node temp = head, prev = null;
		while(temp.next != null) {
			prev = temp;
			temp = temp.next;
		}
		if(prev != null) {
            		prev.next = null;
        	} else {
            		temp = null;
        }
	}
	
	/**
	 * Removes the specified node data
	 * @param data
	 */
	// O(n)
	public void remove(Item key) {
		if (head == null)
			throw new NullPointerException("No elements in linked list");
		Node prev = null;
		Node temp = head;
		// point temp to either item node or to the last node(if key item not found
		while(!(temp.item.equals(key)) || (temp.next == null)) {
			prev = temp;
			temp = temp.next;
		}
		// key exists in linked list and is pointed by temp
		if (temp.item.equals(key)) {
			if (prev == null) // deleting first node
				head = head.next;
			else if (temp.next == null) // deleting last node
				prev = null;
			else { // deleting a node between first and last
				prev.next = temp.next;
				temp = null;
			}
		}
		// key doesn't exits in linked list
		else
			throw new NoSuchElementException("The " + key.toString() + "is not available!");
		
	}
	
	/**
	 * Inserts a new node before data
	 * @param data
	 */
	// O(n)
	public void insertBefore(Item key, Item data) {
		if (head == null)
			head = new Node(data);
		Node prev = null;
		Node temp = head;
		while(!(temp.item.equals(key)) || (temp.next == null)) {
			prev = temp;
			temp = temp.next;
		}
		if (temp.item.equals(key)) {
			if (prev == null) // inserting at first
				addFirst(data);
			else {
				Node newNode = new Node(data);
				prev.next = newNode;
				newNode.next = temp;;
			}
		}
		
		else
			throw new NoSuchElementException("The " + key.toString() + "is not available!");	
	}
	
	/**
	 * Inserts a new node after data
	 * @param data
	 */
	// O(n)
	public void insertAfter(Item key, Item data) {
		if (head == null)
			head = new Node(data);
		Node temp = head;
		while(!((temp.item.equals(key)) || (temp.next == null))) {
			temp = temp.next;
		}
		if (temp.item.equals(key)) {
			if (temp.next == null) // inserting at last
				temp.next = new Node(data);
			else {
				Node newNode = new Node(data);
				newNode.next = temp.next;
				temp.next = newNode;
			}
		}
		
		else
			throw new NoSuchElementException("The " + key.toString() + "is not available!");	
	}
	
	/**
	 * Reverses the liked list(it would change head pointer)
	 * 
	 * @param head
	 * @return node of reversed linked list
	 */
	public Node reverse() {
		Node current = head, prev = null, next;
		while(current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		head = prev;
		return head;
	}
	
	public Node reverseRecursive() {
		Node earlierHead = reverseRecursive(head);
		earlierHead.next = null;// the above call doesn't set last variable to next
		return head;
	}
	
	private MySingleLinkedList<Item>.Node reverseRecursive(MySingleLinkedList<Item>.Node x) {
		if(x == null)
			return null;
		if(x.next == null)
		{
			head = x;
			return head;
		}
		Node tmp = reverseRecursive(x.next);
		tmp.next = x;
		return x;
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = head;
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Node temp = current;
			current = current.next;
			return temp.item;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	public static void main(String[] args) {
		MySingleLinkedList<Integer> integerList = new MySingleLinkedList<Integer>();
		
		integerList.add(1);
		System.out.println("add(1)");
		for (Integer list : integerList)
			System.out.print(list + " ");
		System.out.println("");
		
		integerList.addFirst(0);
		System.out.println("addFirst(0)");
		for (Integer list : integerList)
			System.out.print(list + " ");
		System.out.println("");
		
		integerList.addLast(2);
		System.out.println("addLast(2)");
		for (Integer list : integerList)
			System.out.print(list + " ");
		System.out.println("");
		
		integerList.insertBefore(0, -1);
		System.out.println("insertBefore(0, -1)");
		for (Integer list : integerList)
			System.out.print(list + " ");
		System.out.println("");
		
		integerList.insertBefore(1, 5);
		System.out.println("insertBefore(1, 5)");
		for (Integer list : integerList)
			System.out.print(list + " ");
		System.out.println("");
		
		integerList.insertAfter(2, 3);
		System.out.println("insertAfter(2, 3)");
		for (Integer list : integerList)
			System.out.print(list + " ");
		System.out.println("");
		
		integerList.add(-2);
		System.out.println("add(-2)");
		for (Integer list : integerList)
			System.out.print(list + " ");
		System.out.println("");
		
		integerList.remove(2);
		System.out.println("remove(2)");
		for (Integer list : integerList)
			System.out.print(list + " ");
		System.out.println("");
		
		integerList.removeFirst();
		System.out.println("removeFirst()");
		for (Integer list : integerList)
			System.out.print(list + " ");
		System.out.println("");
		
		integerList.removeLast();
		System.out.println("removeLast()");
		for (Integer list : integerList)
			System.out.print(list + " ");
		
		System.out.println("\n*************After reversing above linked list***************\n");
		integerList.reverse();
		for (Integer list : integerList)
			System.out.print(list + " ");
		
		System.out.println("\n*************After reversing above linked list using reverse recursive***************\n");
		integerList.reverseRecursive();
		for (Integer list : integerList)
			System.out.print(list + " ");
	}

}


