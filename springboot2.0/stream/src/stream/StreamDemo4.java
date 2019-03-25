package stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** 流的终止操作
 * Created by mingway on Date:2018-11-29 11:05.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class StreamDemo4 {
	public static void main(String[] args) {
		String str = "my name is 007";

		//使用并行流
		str.chars().parallel().forEach(i -> System.out.println((char) i));		//此时打印出来的是一个无顺序的

		//使用并行流时，使用forEachOrdered终止操作时，可保证顺序
		str.chars().parallel().forEachOrdered(i -> System.out.println((char) i));

		//collect操作
		List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
		System.out.println(list);

		//reduce操作--拼接字符串
		Optional<String> reduce = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2);
		System.out.println(reduce.orElse(""));

		//max操作--求最大值
		Optional<String> max = Stream.of(str.split(" ")).max((s1, s2) -> s1.length() -  s2.length());
		System.out.println(max.get());

	}
}
