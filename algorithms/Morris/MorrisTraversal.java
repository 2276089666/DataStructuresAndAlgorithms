package Morris;

public class MorrisTraversal {

	/**
	 * Morris遍历
	 * 一种遍历二叉树的方式，并且时间复杂度O(N)，额外空间复杂度O(1) 
	 * 通过利用原树中大量空闲指针的方式，达到节省空间的目的
	 *
	 * 假设来到当前节点cur，开始时cur来到头节点位置
	 * 1）如果cur没有左孩子，cur向右移动(cur = cur.right)
	 * 2）如果cur有左孩子，找到左子树上最右的节点mostRight：
	 * 	a.如果mostRight的右指针指向空，让其指向cur，
	 * 	然后cur向左移动(cur = cur.left)
	 * 	b.如果mostRight的右指针指向cur，让其指向null，
	 * 	然后cur向右移动(cur = cur.right)
	 * 3）cur为空时遍历停止
	 *
	 * 建立一种机制：
	 * 对于没有左子树的节点只到达一次，
	 * 对于有左子树的节点会到达两次
	 * morris遍历时间复杂度依然是O(N)
	 */
	
	public static class Node {
		public int value;
		Node left;
		Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * 	当前节点cur,一开始来到整棵树的头
	 * 	当cur不等于null:
	 * 	(一).cur无左树,cur=cur.right
	 * 	(二).cur有左树,找到左树的最右节点mostRight
	 * 		1.如果mostRight.right指向null, mostRight.right=cur; cur=cur.left
	 * 		2.如果mostRight.right指向cur,	mostRight.right=null; cur=cur.right
	 *
	 *
	 */
	public static void morrisIn(Node head) {
		if (head == null) {
			return;
		}
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				// 分支(二)
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
				}
			}
			System.out.print(cur1.value + " ");
			// 分支(一)
			cur1 = cur1.right;
		}
		System.out.println();
	}

	public static void morrisPre(Node head) {
		if (head == null) {
			return;
		}
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					System.out.print(cur1.value + " ");
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
				}
			} else {
				System.out.print(cur1.value + " ");
			}
			cur1 = cur1.right;
		}
		System.out.println();
	}

	public static void morrisPos(Node head) {
		if (head == null) {
			return;
		}
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
					printEdge(cur1.left);
				}
			}
			cur1 = cur1.right;
		}
		printEdge(head);
		System.out.println();
	}

	public static void printEdge(Node head) {
		Node tail = reverseEdge(head);
		Node cur = tail;
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		reverseEdge(tail);
	}

	public static Node reverseEdge(Node from) {
		Node pre = null;
		Node next = null;
		while (from != null) {
			next = from.right;
			from.right = pre;
			pre = from;
			from = next;
		}
		return pre;
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(7);
		printTree(head);
		morrisIn(head);
		morrisPre(head);
		morrisPos(head);
		printTree(head);

	}

}
