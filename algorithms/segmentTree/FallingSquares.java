package segmentTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class FallingSquares {
	/**
	 * 想象一下标准的俄罗斯方块游戏，X轴是积木最终下落到底的轴线
	 * 下面是这个游戏的简化版：
	 * 1）只会下落正方形积木
	 * 2）[a,b] -> 代表一个边长为b的正方形积木，积木左边缘沿着X = a这条线从上方掉落
	 * 3）认为整个X轴都可能接住积木，也就是说简化版游戏是没有整体的左右边界的
	 * 4）没有整体的左右边界，所以简化版游戏不会消除积木，因为不会有哪一层被填满。
	 *
	 * 给定一个N*2的二维数组matrix，可以代表N个积木依次掉落，
	 * 返回每一次掉落之后的最大高度
	 */
	public static class SegmentTree {
		private int[] max;
		private int[] change;
		private boolean[] update;

		public SegmentTree(int size) {
			int N = size + 1;
			max = new int[N << 2];
			change = new int[N << 2];
			update = new boolean[N << 2];
		}

		private void pushUp(int rt) {
			max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
		}

		// ln表示左子树元素结点个数，rn表示右子树结点个数
		private void pushDown(int rt, int ln, int rn) {
			if (update[rt]) {
				update[rt << 1] = true;
				update[rt << 1 | 1] = true;
				change[rt << 1] = change[rt];
				change[rt << 1 | 1] = change[rt];
				max[rt << 1] = change[rt];
				max[rt << 1 | 1] = change[rt];
				update[rt] = false;
			}
		}

		public void update(int L, int R, int C, int l, int r, int rt) {
			if (L <= l && r <= R) {
				update[rt] = true;
				change[rt] = C;
				max[rt] = C;
				return;
			}
			int mid = (l + r) >> 1;
			pushDown(rt, mid - l + 1, r - mid);
			if (L <= mid) {
				update(L, R, C, l, mid, rt << 1);
			}
			if (R > mid) {
				update(L, R, C, mid + 1, r, rt << 1 | 1);
			}
			pushUp(rt);
		}

		public int query(int L, int R, int l, int r, int rt) {
			if (L <= l && r <= R) {
				return max[rt];
			}
			int mid = (l + r) >> 1;
			pushDown(rt, mid - l + 1, r - mid);
			int left = 0;
			int right = 0;
			if (L <= mid) {
				left = query(L, R, l, mid, rt << 1);
			}
			if (R > mid) {
				right = query(L, R, mid + 1, r, rt << 1 | 1);
			}
			return Math.max(left, right);
		}

	}

	public HashMap<Integer, Integer> index(int[][] positions) {
		TreeSet<Integer> pos = new TreeSet<>();
		for (int[] arr : positions) {
			pos.add(arr[0]);
			pos.add(arr[0] + arr[1] - 1);
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		int count = 0;
		for (Integer index : pos) {
			map.put(index, ++count);
		}
		return map;
	}

	public List<Integer> fallingSquares(int[][] positions) {
		HashMap<Integer, Integer> map = index(positions);
		int N = map.size();
		SegmentTree segmentTree = new SegmentTree(N);
		int max = 0;
		List<Integer> res = new ArrayList<>();
		for (int[] arr : positions) {
			int L = map.get(arr[0]);
			int R = map.get(arr[0] + arr[1] - 1);
			int height = segmentTree.query(L, R, 1, N, 1) + arr[1];
			max = Math.max(max, height);
			res.add(max);
			segmentTree.update(L, R, height, 1, N, 1);
		}
		return res;
	}

}
