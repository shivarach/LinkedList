package org.geeksforgeeks.doublylinkedlist;

import java.util.Iterator;
/**
 * Doubly linked list operations
 * @author Shiva
 *
 * @param <Item>
 */
public class DoublyLinkedList<Item extends Comparable<Item>> implements
		Iterable<Item> {

	private Node head = null;

	private class Node {
		private Item data;
		private Node next;
		private Node prev;

		Node(Item data) {
			this.data = data;
		}
	}

	// adds node at first
	public void push(Item data) {
		Node newNode = new Node(data);
		newNode.next = head;
		if (head != null)
			head.prev = newNode;
		head = newNode;
		return;
	}

	// adds node at first
	public void pushAtEnd(Item data) {
		Node newNode = new Node(data);
		Node tmp = head;
		if (tmp == null) {
			head = newNode;
			return;
		}
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		newNode.prev = tmp;
		tmp.next = newNode;
		return;
	}

	public void insertAfter(Item item, Item newItem) throws Exception {
		Node newNode = new Node(newItem);
		Node tmp = head;
		// if list is empty
		if (tmp == null) {
			head = newNode;
			return;
		}
		while (tmp != null) {
			if (tmp.data.compareTo(item) == 0)
				break;
			tmp = tmp.next;
		}
		if (tmp == null) {
			throw new Exception(item + "Not found");
		}
		newNode.next = tmp.next;
		tmp.next = newNode;
		newNode.prev = tmp;
		if (tmp.next == null)
			newNode.next.prev = newNode;
		return;
	}

	public void insertBefore(Item item, Item newItem) throws Exception {
		Node newNode = new Node(newItem);
		Node tmp = head;
		// if list is empty
		if (tmp == null) {
			head = newNode;
			return;
		}
		while (tmp != null) {
			if (tmp.data.compareTo(item) == 0)
				break;
			tmp = tmp.next;
		}
		if (tmp == null) {
			throw new Exception(item + "Not found");
		}
		newNode.prev = tmp.prev;
		tmp.prev = newNode;
		newNode.next = tmp;
		if (newNode.prev != null)
			newNode.prev.next = newNode;
		else {
			head = newNode;
		}
		return;
	}
	
	void deleteNode(Item item) throws Exception {
		 
        /* base case */
        if (head == null || item == null) {
            return;
        }
        Node tmp = head;
		while (tmp != null) {
			if (tmp.data.compareTo(item) == 0)
				break;
			tmp = tmp.next;
		}
		if (tmp == null) {
			throw new Exception(item + "Not found");
		}
        /* If node to be deleted is head node */
        if (head == tmp) {
            head = tmp.next;
        }
 
        /* Change next only if node to be deleted is NOT the last node */
        if (tmp.next != null) {
            tmp.next.prev = tmp.prev;
        }
 
        /* Change prev only if node to be deleted is NOT the first node */
        if (tmp.prev != null) {
            tmp.prev.next = tmp.next;
        }
        return;
    }

	@Override
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			Node tmp = head;

			@Override
			public boolean hasNext() {
				return tmp != null;
			}

			@Override
			public Item next() {
				Node cur = tmp;
				tmp = tmp.next;
				return cur.data;
			}
		};
	}
	
	public static void main(String[] args) throws Exception {
		DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<Integer>();
		doublyLinkedList.push(1);
		doublyLinkedList.push(2);
		doublyLinkedList.push(3);
		doublyLinkedList.push(4);
		doublyLinkedList.pushAtEnd(5);
		doublyLinkedList.push(6);
		doublyLinkedList.insertAfter(3, 7);
		doublyLinkedList.insertAfter(5, 8);
		doublyLinkedList.insertBefore(1, 9);
		doublyLinkedList.insertBefore(6, 10);
		doublyLinkedList.push(11);
		
		doublyLinkedList.deleteNode(11);
		doublyLinkedList.deleteNode(8);
		doublyLinkedList.deleteNode(4);
		
		System.out.print("\n Linked list: ");
        for (Integer i : doublyLinkedList)
        	System.out.print(i + " ");
	}
}
