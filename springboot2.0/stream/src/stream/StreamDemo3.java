package stream;

import java.util.Random;
import java.util.stream.Stream;

/**	流的中间操作
 * Created by mingway on Date:2018-11-29 10:17.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class StreamDemo3 {
	public static void main(String[] args) {
		String str = "my name is 007";

		System.out.println("---------map--------------");
		//map中间操作(转换)--把每个单词的长度打印出来
		Stream.of(str.split(" ")).map(s -> s.length()).forEach(System.out::println);

		System.out.println("---------filter--------------");
		//filter(过滤)
		Stream.of(str.split(" ")).filter(s -> s.length() > 2).map(s -> s.length()).forEach(System.out::println);

		System.out.println("---------flatMap--------------");
		//flatMap(A -> B属性（是个集合），最终得到的是A元素里面所有B属性集合)
		//intStream/longStream并不是Stram的子类，所以需要进行装箱boxed
		Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(i -> System.out.println((char) i.intValue()));

		System.out.println("---------peek--------------");
		//peek 一般用于debug，是中间操作，和forEach是终止操作
		Stream.of(str.split(" ")).peek(System.out::println).forEach(System.out::println);

		System.out.println("---------limit--------------");
		//limit使用，主要用于无限流
		new Random().ints().limit(10).forEach(System.out::println);
	}

}
