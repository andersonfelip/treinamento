package com.pitang.treinamento;

public class Teste {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		ListNode node = swapPairs(head);
		System.out.println(node.val);
		System.out.println(node.next.val);
		System.out.println(node.next.next.val);
		System.out.println(node.next.next.next.val);
	}

	public static ListNode swapPairs(ListNode head) {
		if(head != null && head.next != null) {
			int tmp = head.val;
			head.val = head.next.val;
			head.next.val = tmp;
			
			swapPairs(head.next.next);
		}
		return head;
	}

	public static void swapValues(ListNode a, ListNode b) {
		
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
