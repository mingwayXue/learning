package stream;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**	并行流
 * Created by mingway on Date:2018-11-29 11:47.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class StreamDemo5 {
	public static void main(String[] args) {
		IntStream.range(1, 100).parallel().peek(StreamDemo5::debug).count();	//parallel并行流调用

	}

	public static void debug(int i) {
		System.out.println(Thread.currentThread().getName() + " debug " + i);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
