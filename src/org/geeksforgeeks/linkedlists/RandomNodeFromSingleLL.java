package org.geeksforgeeks.linkedlists;

import org.shiva.utilities.ReservoirSampling;

public class RandomNodeFromSingleLL {
	private Node head = null;
	private class Node {
		int n;
		Node next;
		public Node(int n) {
			this.n = n;
			this.next = null;
		}
	}
	
	public void insertElement(int a) {
		Node node = new Node(a);
		Node temp = head;
		if (temp == null) {
			head = node;
		}
		else {
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = node;
		}
	}
	public static void main(String[] args) {
		int[] stream = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] result = ReservoirSampling.selectKSamplesS(stream, 2);
		for(int x : result) {
			System.out.println(x + " ");
		}
	}

}
