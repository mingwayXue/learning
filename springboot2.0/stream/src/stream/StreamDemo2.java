package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**	流的创建
 * Created by mingway on Date:2018-11-29 9:47.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class StreamDemo2 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		int[] intArray = new int[]{1, 3, 5};

		//集合流
		list.stream();
		list.parallelStream();	//并行流

		//数组流
		Arrays.stream(intArray);

		//数字流
		IntStream.of(1, 3, 5);

		//使用random创建一个无限流（一般需要使用limit限制，否则报错）
		new Random().ints().limit(10);

		//自创建流,也是一个无限流
		Random random = new Random();
		Stream.generate(() -> random.nextInt()).limit(20);

	}
}
