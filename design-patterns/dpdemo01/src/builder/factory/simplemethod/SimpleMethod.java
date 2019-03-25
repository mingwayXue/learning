package builder.factory.simplemethod;

/**	设计模式：简单工厂模式
 * 简单工厂模式是由一个工厂对象根据收到的消息决定要创建哪一个类的对象实例
 * Created by mingway on Date:2018-12-08 12:02.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class SimpleMethod {

	/**
	 * 缺点：只能使用StaticFactory里面存在的类
	 * @param args
	 */
	public static void main(String[] args) {
		get("B");
	}
	public static void get(String name) {
		Food x = null;
		if (name.equals("A")) {
			x = StaticFactory.getA();
		} else if (name.equals("B")) {
			x = StaticFactory.getB();
		} else {
			x = StaticFactory.getC();
		}
	}
}

interface Food{}

class A implements Food{
	public A(){
		System.out.println("This is A");
	}
}
class B implements Food{
	public B(){
		System.out.println("This is B");
	}
}
class C implements Food{
	public C(){
		System.out.println("This is C");
	}
}

/**
 * 静态工厂
 */
class StaticFactory {

	private StaticFactory(){}

	public static Food getA(){  return new A(); }
	public static Food getB(){  return new B(); }
	public static Food getC(){  return new C(); }
}
