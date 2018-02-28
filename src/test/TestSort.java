package test;

import java.util.Arrays;

import org.junit.Test;

/**
 * 排序测试
 * 
 * @author 杨小龙
 * @date 2018年2月27日下午2:16:35
 * @since 1.0
 */
public class TestSort {

	/**
	 * 冒泡排序
	 * 
	 * 冒泡排序思路： 
	 * 	1、将序列当中的左右元素，依次比较，保证右边的元素始终大于左边的元素； 
	 * 		（ 第一轮结束后，序列最后一个元素一定是当前序列的最大值；）
	 * 	2、对序列当中剩下的n-1个元素再次执行步骤1。 
	 * 	3、对于长度为n的序列，一共需要执行n-1轮比较 
	 * 		（利用while循环可以减少执行次数）
	 * 
	 * @author 杨小龙
	 * @date 2018年2月27日下午2:20:55
	 * @since 1.0
	 */
	@Test
	public void test_BubbleSort() {
		int[] array = { 8, 4, 6, 2, 1, 0, 15, 7, 3 };
		System.out.println("冒泡排序前：" + Arrays.toString(array));

		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < array.length - i; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		System.out.println("冒泡排序后：" + Arrays.toString(array));
	}

	/**
	 * 快速排序
	 * 
	 * 快速排序思路：
	 * 	 1、从序列当中选择一个基准数(pivot) 
	 * 		（在这里我们选择序列当中中间一个数做为基准数）
	 * 	2、将序列当中的所有数依次遍历，比基准数大的位于其右侧，比基准数小的位于其左侧 
	 * 	3、重复步骤1.2，直到所有子集当中只有一个元素为止。
	 * 
	 * @author 杨小龙
	 * @date 2018年2月27日下午2:44:00
	 * @since 1.0
	 */
	@Test
	public void test_QuickSort() {
		int[] array = { 8, 4, 6, 2, 1, 0, 15, 7, 3 };
		System.out.println("快速排序前：" + Arrays.toString(array));

		QuickSort instance = new QuickSort(array);
		instance.sort(0, array.length - 1);

		System.out.println("快速排序后：" + instance.getSortArray());
	}

	/**
	 * 堆排序
	 * 
	 * 堆排序思路：
	 * 	 1、首先将序列构建称为大顶堆； 
	 * 		（这样满足了大顶堆那条性质：位于根节点的元素一定是当前序列的最大值）
	 * 	2、取出当前大顶堆的根节点，将其与序列末尾元素进行交换；
	 * 		（此时：序列末尾的元素为已排序的最大值；由于交换了元素，当前位于根节点的堆并不一定满足大顶堆的性质）
	 * 	3、对交换后的n-1个序列元素进行调整，使其满足大顶堆的性质； 
	 * 	4、重复2.3步骤，直至堆中只有1个元素为止
	 * 
	 * 堆的相关定义： 
	 * 	1、若array[0，...，n-1]表示一颗完全二叉树的顺序存储模式，则双亲节点指针和孩子结点指针之间的内在关系如下： 
	 * 		任意一节点指针i： 父节点：i==0 ? null : (i-1)/2 左孩子：2*i + 1 右孩子：2*i + 2
	 * 	2、n个关键字序列array[0，...，n-1]，当且仅当满足下列要求：
	 * 		(0 <= i <= (n-1)/2) 
	 * 		① array[i] <= array[2*i + 1] 且 array[i] <= array[2*i + 2]； 称为小根堆； 
	 * 		② array[i] >= array[2*i + 1] 且 array[i] >= array[2*i + 2]； 称为大根堆； 
	 * 	3、建立大根堆：
	 *		①n个节点的完全二叉树array[0，...，n-1]，最后一个节点n-1是第(n-1-1)/2个节点的孩子。  
	 *			对第(n-1-1)/2个节点为根的子树调整，使该子树称为堆。 
	 *		② 对于大根堆，调整方法为：若【根节点的关键字】小于【左右子女中关键字较大者】，则交换。 
	 *		③ 之后向前依次对各节点（(n-2)/2 - 1）~ 0为根的子树进行调整，看该节点值是否大于其左右子节点的值，
	 * 			若不是，将左右子节点中较大值与之交换，交换后可能会破坏下一级堆，于是继续采用上述方法构建下一级的堆， 
	 * 			直到以该节点为根的子树构成堆为止。 
	 * 		④ 反复利用上述调整堆的方法建堆，直到根节点。
	 * 
	 * @author 杨小龙
	 * @date 2018年2月27日下午4:37:46
	 * @since 1.0
	 */
	@Test
	public void test_HeapSort() {
		int[] array = { 8, 4, 44, 6, 2, 1, 55, 0, 15, 7, 3, 66 };
		System.out.println("堆排序前：" + Arrays.toString(array));

		HeapSort instance = new HeapSort(array);
		instance.sort();

		System.out.println("堆排序后：" + instance.getSortArray());
	}

}

class QuickSort {
	private int[] sortArray;

	public QuickSort(int[] sortArray) {
		this.sortArray = sortArray;
	}

	/**
	 * 排序
	 * 
	 * @author 杨小龙
	 * @date 2018年2月27日下午4:27:42
	 * @since 1.0
	 */
	public void sort(int start, int end) {
		if (start < end) {
			// 划分两边，继续递归排序
			int index = quickSort(start, end);
			sort(0, index - 1);
			sort(index + 1, end);
		}
	}

	/**
	 * 将数组的某一段元素进行划分，小的在左边，大的在右边
	 * 
	 * @author 杨小龙
	 * @date 2018年2月27日下午4:27:14
	 * @since 1.0
	 */
	public int quickSort(int start, int end) {
		if (start < end) {
			// 每次都以最左边的元素作为基准值
			int pivot = this.sortArray[start];
			// start一旦等于end，就说明左右两个指针合并到了同一位置，可以结束此轮循环。
			while (start < end) {
				while (start < end && this.sortArray[end] >= pivot) {
					// 从右边开始遍历，如果比基准值大，就继续向左走
					end--;
				}
				// 上面的while循环结束时，就说明当前的a[end]的值比基准值小，应与基准值进行交换
				if (start < end) {
					// 交换
					swap(start, end);
					// 交换后，此时a[end]的值已经调到了正确的位置(基准值左边)，因此左边++
					start++;
				}
				while (start < end && this.sortArray[start] < pivot) {
					// 从左边开始遍历，如果比基准值小，就继续向右走
					start++;
				}
				// 上面的while循环结束时，就说明当前的a[start]的值比基准值大，应与基准值进行交换
				if (start < end) {
					// 交换
					swap(start, end);
					// 交换后，此时a[start]的值已经调到了正确的位置(基准值右边)，因此右边--
					end--;
				}
			}
		}
		// 这里返回start或者end皆可，此时的start和end都为基准值所在的位置
		return start;
	}

	public String getSortArray() {
		return Arrays.toString(this.sortArray);
	}

	/**
	 * 交换数组两个位置
	 */
	public void swap(int i, int j) {
		int temp = this.sortArray[i];
		this.sortArray[i] = this.sortArray[j];
		this.sortArray[j] = temp;
	}
}

class HeapSort {

	private int[] sortArray;

	public HeapSort(int[] sortArray) {
		this.sortArray = sortArray;
	}

	/**
	 * 左节点下标
	 */
	private int left(int i) {
		return i * 2 + 1;
	}

	/**
	 * 右节点下标
	 */
	private int right(int i) {
		return i * 2 + 2;
	}

	/**
	 * 父节点下标
	 */
	private int parent(int i) {
		return (i - 1) / 2;
	}

	public void sort() {
		int length = this.sortArray.length;
		// // 第一次构建大顶堆（i为最后节点的父节点下标）
		for (int i = parent(length - 1); i >= 0; i--) {
			heapSort(i, length);
		}
		// 将堆顶放到二叉树最后，并不计入大顶堆计算（数组长度-1），重新调整大顶堆
		for (int i = length - 1; i > 0; i --) {
			swap(0, i);
			heapSort(0, i - 1);
		}
	}

	public void heapSort(int i, int length) {
		int largestIndex = i;
		int left = left(i);
		int right = right(i);
		
		// 【左子节点】>【父节点】，置换【父节点】和【左子节点】
		if (left < length && this.sortArray[left] > this.sortArray[largestIndex]) {
			largestIndex = left;
		}
		// 【右子节点】>【父节点】，置换【父节点】和【右子节点】
		if (right < length && this.sortArray[right] > this.sortArray[largestIndex]) {
			largestIndex = right;
		}
		if (largestIndex != i) {
			swap(largestIndex, i);
		} else {
			return;
		}
		// 递归
		heapSort(largestIndex, length);
	}
	
	public String getSortArray() {
		return Arrays.toString(sortArray);
	}
	
	/**
	 * 交换数组两个位置
	 */
	public void swap(int i, int j) {
		int temp = this.sortArray[i];
		this.sortArray[i] = this.sortArray[j];
		this.sortArray[j] = temp;
	}

}

