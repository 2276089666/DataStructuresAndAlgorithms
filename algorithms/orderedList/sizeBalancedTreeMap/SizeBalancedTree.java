package orderedList.sizeBalancedTreeMap;

public class SizeBalancedTree {

	public static class SBTNode<K extends Comparable<K>, V> {
		public K key;
		public V value;
		public SBTNode<K, V> l;
		public SBTNode<K, V> r;
		public int size; // 不同的key的数量

		public SBTNode(K key, V value) {
			this.key = key;
			this.value = value;
			size = 1;
		}
	}

	/**
	 * SizeBalancedTree
	 * 平衡约束: 任何一个以叔叔节点为头节点的子树节点个数 >= 以侄子节点为头节点的子树节点个数
	 * 调整平衡策略: 调整平衡后,该子树的所有节点中谁的孩子节点发生了变化,谁就得去判断是否平衡,没有平衡左右旋变平衡(递归调用)
	 * 相对于AVL Tree优势: 平衡性约束相对模糊,删除的时候不调整平衡,只在新增时调平衡
	 * 缺点: 调平衡的时候复杂一些,调平衡的过程中节点中谁的孩子节点发生了变化,就得递归调平衡,但是均
	 * 		摊下来时间复杂度还是O(log N)
	 * @param <K>
	 * @param <V>
	 */
	public static class SizeBalancedTreeMap<K extends Comparable<K>, V> {
		private SBTNode<K, V> root;

		private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
			SBTNode<K, V> leftNode = cur.l;
			cur.l = leftNode.r;
			leftNode.r = cur;
			leftNode.size = cur.size;
			cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
			return leftNode;
		}

		private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
			SBTNode<K, V> rightNode = cur.r;
			cur.r = rightNode.l;
			rightNode.l = cur;
			rightNode.size = cur.size;
			cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
			return rightNode;
		}

		// 平衡调整
		private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
			if (cur == null) {
				return null;
			}

			if (cur.l != null && cur.l.l != null && cur.r != null && cur.l.l.size > cur.r.size) {  // LL 1次旋转,2次递归
				cur = rightRotate(cur);
				cur.r = maintain(cur.r);
				cur = maintain(cur);
			} else if (cur.l != null && cur.l.r != null && cur.r != null && cur.l.r.size > cur.r.size) {  // LR 2次旋转,3次递归
				cur.l = leftRotate(cur.l);
				cur = rightRotate(cur);
				cur.l = maintain(cur.l);
				cur.r = maintain(cur.r);
				cur = maintain(cur);
			} else if (cur.r != null && cur.r.r != null && cur.l != null && cur.r.r.size > cur.l.size) {  // RR 1次旋转,2次递归
				cur = leftRotate(cur);
				cur.l = maintain(cur.l);
				cur = maintain(cur);
			} else if (cur.r != null && cur.r.l != null && cur.l != null && cur.r.l.size > cur.l.size) { // RL 2次旋转,3次递归
				cur.r = rightRotate(cur.r);
				cur = leftRotate(cur);
				cur.l = maintain(cur.l);
				cur.r = maintain(cur.r);
				cur = maintain(cur);
			}
			return cur;
		}


		/**
		 * 现在，以cur为头的树上，加(key, value)这样的记录
		 * 加完之后，会对cur做检查，该调整调整
		 * 返回，调整完之后，整棵树的新头部
		 * @param cur
		 * @param key
		 * @param value
		 * @return
		 */
		private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
			if (cur == null) {
				return new SBTNode<K, V>(key, value);
			} else {
				cur.size++;
				if (key.compareTo(cur.key) < 0) {
					cur.l = add(cur.l, key, value);
				} else {
					cur.r = add(cur.r, key, value);
				}
				return maintain(cur);
			}
		}

		/**
		 * 在cur这棵树上，删掉key所代表的节点
		 * 返回cur这棵树的新头部
		 * @param cur
		 * @param key
		 * @return
		 */
		private SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
			cur.size--;
			if (key.compareTo(cur.key) > 0) {
				cur.r = delete(cur.r, key);
			} else if (key.compareTo(cur.key) < 0) {
				cur.l = delete(cur.l, key);
			} else { // 当前要删掉cur
				if (cur.l == null && cur.r == null) {
					// free cur memory -> C++
					cur = null;
				} else if (cur.l == null && cur.r != null) {
					// free cur memory -> C++
					cur = cur.r;
				} else if (cur.l != null && cur.r == null) {
					// free cur memory -> C++
					cur = cur.l;
				} else { // 有左有右,后继节点替代当前节点
					SBTNode<K, V> pre = null;
					SBTNode<K, V> des = cur.r;
					des.size--;
					while (des.l != null) {
						pre = des;
						des = des.l;
						des.size--;
					}
					if (pre != null) {
						pre.l = des.r;
						des.r = cur.r;
					}
					des.l = cur.l;
					des.size = des.l.size + (des.r == null ? 0 : des.r.size) + 1;
					// free cur memory -> C++
					cur = des;
				}
			}
			return cur;
		}

		private SBTNode<K, V> findLastIndex(K key) {
			SBTNode<K, V> pre = root;
			SBTNode<K, V> cur = root;
			while (cur != null) {
				pre = cur;
				if (key.compareTo(cur.key) == 0) {
					break;
				} else if (key.compareTo(cur.key) < 0) {
					cur = cur.l;
				} else {
					cur = cur.r;
				}
			}
			return pre;
		}

		private SBTNode<K, V> findLastNoSmallIndex(K key) {
			SBTNode<K, V> ans = null;
			SBTNode<K, V> cur = root;
			while (cur != null) {
				if (key.compareTo(cur.key) == 0) {
					ans = cur;
					break;
				} else if (key.compareTo(cur.key) < 0) {
					ans = cur;
					cur = cur.l;
				} else {
					cur = cur.r;
				}
			}
			return ans;
		}

		private SBTNode<K, V> findLastNoBigIndex(K key) {
			SBTNode<K, V> ans = null;
			SBTNode<K, V> cur = root;
			while (cur != null) {
				if (key.compareTo(cur.key) == 0) {
					ans = cur;
					break;
				} else if (key.compareTo(cur.key) < 0) {
					cur = cur.l;
				} else {
					ans = cur;
					cur = cur.r;
				}
			}
			return ans;
		}


		private SBTNode<K, V> getIndex(SBTNode<K, V> cur, int kth) {
			if (kth == (cur.l != null ? cur.l.size : 0) + 1) {
				return cur;
			} else if (kth <= (cur.l != null ? cur.l.size : 0)) {
				return getIndex(cur.l, kth);
			} else {
				return getIndex(cur.r, kth - (cur.l != null ? cur.l.size : 0) - 1);
			}
		}

		public int size() {
			return root == null ? 0 : root.size;
		}

		public boolean containsKey(K key) {
			if (key == null) {
				throw new RuntimeException("invalid parameter.");
			}
			SBTNode<K, V> lastNode = findLastIndex(key);
			return lastNode != null && key.compareTo(lastNode.key) == 0 ? true : false;
		}

		// 增加或修改节点
		public void put(K key, V value) {
			if (key == null) {
				throw new RuntimeException("invalid parameter.");
			}
			SBTNode<K, V> lastNode = findLastIndex(key);
			// 修改节点
			if (lastNode != null && key.compareTo(lastNode.key) == 0) {
				lastNode.value = value;
			} else {
				// 左旋右旋,根节点可能会变,在此更新根节点
				root = add(root, key, value);
			}
		}

		public void remove(K key) {
			if (key == null) {
				throw new RuntimeException("invalid parameter.");
			}
			if (containsKey(key)) {
				root = delete(root, key);
			}
		}

		public K getIndexKey(int index) {
			if (index < 0 || index >= this.size()) {
				throw new RuntimeException("invalid parameter.");
			}
			return getIndex(root, index + 1).key;
		}

		public V getIndexValue(int index) {
			if (index < 0 || index >= this.size()) {
				throw new RuntimeException("invalid parameter.");
			}
			return getIndex(root, index + 1).value;
		}

		public V get(K key) {
			if (key == null) {
				throw new RuntimeException("invalid parameter.");
			}
			SBTNode<K, V> lastNode = findLastIndex(key);
			if (lastNode != null && key.compareTo(lastNode.key) == 0) {
				return lastNode.value;
			} else {
				return null;
			}
		}

		public K firstKey() {
			if (root == null) {
				return null;
			}
			SBTNode<K, V> cur = root;
			while (cur.l != null) {
				cur = cur.l;
			}
			return cur.key;
		}

		public K lastKey() {
			if (root == null) {
				return null;
			}
			SBTNode<K, V> cur = root;
			while (cur.r != null) {
				cur = cur.r;
			}
			return cur.key;
		}

		public K floorKey(K key) {
			if (key == null) {
				throw new RuntimeException("invalid parameter.");
			}
			SBTNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
			return lastNoBigNode == null ? null : lastNoBigNode.key;
		}

		public K ceilingKey(K key) {
			if (key == null) {
				throw new RuntimeException("invalid parameter.");
			}
			SBTNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
			return lastNoSmallNode == null ? null : lastNoSmallNode.key;
		}

	}

	// for test
	public static void printAll(SBTNode<String, Integer> head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	// for test
	public static void printInOrder(SBTNode<String, Integer> head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.r, height + 1, "v", len);
		String val = to + "(" + head.key + "," + head.value + ")" + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.l, height + 1, "^", len);
	}

	// for test
	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		SizeBalancedTreeMap<String, Integer> sbt = new SizeBalancedTreeMap<String, Integer>();
		sbt.put("d", 4);
		sbt.put("c", 3);
		sbt.put("a", 1);
		sbt.put("b", 2);
		// sbt.put("e", 5);
		sbt.put("g", 7);
		sbt.put("f", 6);
		sbt.put("h", 8);
		sbt.put("i", 9);
		sbt.put("a", 111);
		System.out.println(sbt.get("a"));
		sbt.put("a", 1);
		System.out.println(sbt.get("a"));
		for (int i = 0; i < sbt.size(); i++) {
			System.out.println(sbt.getIndexKey(i) + " , " + sbt.getIndexValue(i));
		}
		printAll(sbt.root);
		System.out.println(sbt.firstKey());
		System.out.println(sbt.lastKey());
		System.out.println(sbt.floorKey("g"));
		System.out.println(sbt.ceilingKey("g"));
		System.out.println(sbt.floorKey("e"));
		System.out.println(sbt.ceilingKey("e"));
		System.out.println(sbt.floorKey(""));
		System.out.println(sbt.ceilingKey(""));
		System.out.println(sbt.floorKey("j"));
		System.out.println(sbt.ceilingKey("j"));
		sbt.remove("d");
		printAll(sbt.root);
		sbt.remove("f");
		printAll(sbt.root);

	}

}
