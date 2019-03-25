package stream;

import java.util.stream.IntStream;

/**
 * Created by mingway on Date:2018-11-29 9:25.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class StreamDemo1 {
	public static void main(String[] args) {
		int[] nums = {1, 2, 3};
		//外部迭代
		int sum1 = 0;
		for (int i : nums) {
			sum1 += i;
		}
		System.out.println(sum1);

		//内部迭代(使用stream)
		//其中map就是中间操作（返回stream流的操作）
		//sum为终止操作
		int sum2 = IntStream.of(nums).map(i -> i * 2).sum();
		System.out.println(sum2);
	}
}

