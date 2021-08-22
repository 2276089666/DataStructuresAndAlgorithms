package orderedList.skipListMap;

import java.util.ArrayList;

public class SkipListMaps {

    // 跳表的节点
    public static class SkipListNode<K extends Comparable<K>, V> {
        public K key;
        public V val;
        public ArrayList<SkipListNode<K, V>> nextNodes; // 多层链表(每个节点的层数可能不一),纵向

        public SkipListNode(K k, V v) {
            key = k;
            val = v;
            nextNodes = new ArrayList<SkipListNode<K, V>>();
        }

        /**
         * 判断key是否小于otherKey:
         *  1.head节点的key其实是null,默认把它当作最小的key
         *  2.每层最右的节点的key其实也是null,默认把它当成最大的key
         *
         * @param otherKey
         * @return
         */
        public boolean isKeyLess(K otherKey) {
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isKeyEqual(K otherKey) {
            return (key == null && otherKey == null)
                    || (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }

    }

	/**
	 * 跳表:宏观上是多层有序链表,上层链表对某些节点有缺失,最底层链表包含所有节点,并且都从小到大有序
     *      从底层到顶层每层的链表节点个数 N  N/2  N/4  N/8 ...
     *      查找数据的时候从顶层向底层往下找,时间复杂度O(log N)
	 * @param <K>
	 * @param <V>
	 */
	public static class SkipListMap<K extends Comparable<K>, V> {
        private static final double PROBABILITY = 0.5; // < 0.5 继续增加新节点链表层数，>=0.5 停
        private SkipListNode<K, V> head;
        private int size;
        private int maxLevel; // 层数,大到小->上到下

        public SkipListMap() {
            head = new SkipListNode<K, V>(null, null);
            head.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }

		/**
		 *
		 * 插入更新
		 * @param key
		 * @param value
		 */
		public void put(K key, V value) {
            if (key == null) {
                return;
            }
			// 从头节点的顶层出发,从左向右找小于key的最右节点,上层没有,找下层
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            // 最右节点存在,最右节点的最下层的右边一个节点key
            SkipListNode<K, V> find = less.nextNodes.get(0);
            // 更新节点
            if (find != null && find.isKeyEqual(key)) {
                find.val = value;
            } else {
            	// 新增操作,总节点个数+1
                size++;
                int newNodeLevel = 0;
                // 扔筛子,得到新节点的层数
                while (Math.random() < PROBABILITY) {
                    newNodeLevel++;
                }
                // 新节点的层数比头节点还大,更新头节点的链表层数
                while (newNodeLevel > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                // 初始化新节点
                SkipListNode<K, V> newNode = new SkipListNode<K, V>(key, value);
                for (int i = 0; i <= newNodeLevel; i++) {
                    newNode.nextNodes.add(null);
                }

                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                // 从上层向下层连指针
                while (level >= 0) {
                    // 最右节点当前层数的节点
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    if (level <= newNodeLevel) {
                    	// 将新节点的level层插入到最右节点level层的后面
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        pre.nextNodes.set(level, newNode);
                    }
                    level--;
                }
            }
        }

		/**
		 * 查找
		 * @param key
		 * @return
		 */
		public V get(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            // 最下层链表
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.val : null;
        }


		/**
		 * 删除
		 * 要把在不同层数的链表中,相同的key的全删掉
		 * @param key
		 */
		public void remove(K key) {
			if (containsKey(key)) {
				size--;
				int level = maxLevel;
				SkipListNode<K, V> pre = head;
				while (level >= 0) {
					pre = mostRightLessNodeInLevel(key, pre, level);
					SkipListNode<K, V> next = pre.nextNodes.get(level);
					// 删除当前level层的key节点
					if (next != null && next.isKeyEqual(key)) {
						pre.nextNodes.set(level, next.nextNodes.get(level));
					}
					// 在level层只有一个节点了，就是默认节点head
					if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
						head.nextNodes.remove(level);
						maxLevel--;
					}
					level--;
				}
			}
		}

		public boolean containsKey(K key) {
			if (key == null) {
				return false;
			}
			SkipListNode<K, V> less = mostRightLessNodeInTree(key);
			SkipListNode<K, V> next = less.nextNodes.get(0);
			return next != null && next.isKeyEqual(key);
		}

        /**
         *  从最高层开始，一路找下去，
         *  最终，找到第0层的小于key的最右的节点
         * @param key
         * @return
         */
        private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            if (key == null) {
                return null;
            }
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                //  从上层跳下层,像下楼梯的曲线一样
                cur = mostRightLessNodeInLevel(key, cur, level--);
            }
            return cur;
        }

        /**
         * 在level层中找到小于key的最右节点
         *
         * @param key
         * @param cur
         * @param level
         * @return
         */
        private SkipListNode<K, V> mostRightLessNodeInLevel(K key,
                                                            SkipListNode<K, V> cur,
                                                            int level) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(key)) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }



        public K firstKey() {
            return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
        }

        public K lastKey() {
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                SkipListNode<K, V> next = cur.nextNodes.get(level);
                while (next != null) {
                    cur = next;
                    next = cur.nextNodes.get(level);
                }
                level--;
            }
            return cur.key;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null ? next.key : null;
        }

        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.key : less.key;
        }

        public int size() {
            return size;
        }

    }

    // for test
    public static void printAll(SkipListMap<String, String> obj) {
        for (int i = obj.maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + " : ");
            SkipListNode<String, String> cur = obj.head;
            while (cur.nextNodes.get(i) != null) {
                SkipListNode<String, String> next = cur.nextNodes.get(i);
                System.out.print("(" + next.key + " , " + next.val + ") ");
                cur = next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SkipListMap<String, String> test = new SkipListMap<>();
        printAll(test);
        System.out.println("======================");
        test.put("A", "10");
        printAll(test);
        System.out.println("======================");
        test.remove("A");
        printAll(test);
        System.out.println("======================");
        test.put("E", "E");
        test.put("B", "B");
        test.put("A", "A");
        test.put("F", "F");
        test.put("C", "C");
        test.put("D", "D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.containsKey("B"));
        System.out.println(test.containsKey("Z"));
        System.out.println(test.firstKey());
        System.out.println(test.lastKey());
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));
        System.out.println("======================");
        test.remove("D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));

    }

}
