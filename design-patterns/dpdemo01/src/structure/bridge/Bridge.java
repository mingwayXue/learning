package structure.bridge;

/** 设计模式：桥接模式
 * 	桥接模式就是把事物和其具体实现分开，使他们可以各自独立的变化；
 * 	类似于JDBC提供统一接口，每个数据库提供各自的实现，用一个叫做数据库驱动的程序来桥接；
 * Created by mingway on Date:2018-12-10 14:11.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class Bridge {

	public static void main(String[] args) {
		Bridge1 bridge = new MyBridge();
		Sourceable source1 = new SourceSub1();	//自实现的类
		bridge.setSource(source1);	//通过bridge设值
		bridge.method();	//实际上调用的source方法

		Sourceable source2 = new SourceSub2();
		bridge.setSource(source2);
		bridge.method();
	}
}

interface Sourceable {
	public void method();
}

class SourceSub1 implements Sourceable {
	@Override
	public void method() {
		System.out.println("this is the first sub!");
	}
}

class SourceSub2 implements Sourceable {
	@Override
	public void method() {
		System.out.println("this is the second sub!");
	}
}

abstract class Bridge1 {
	private Sourceable source;

	public void method(){
		source.method();
	}

	public Sourceable getSource() {
		return source;
	}

	public void setSource(Sourceable source) {
		this.source = source;
	}
}

class MyBridge extends Bridge1 {
	public void method(){
		getSource().method();
	}
}