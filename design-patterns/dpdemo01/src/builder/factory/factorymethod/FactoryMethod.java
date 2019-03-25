package builder.factory.factorymethod;

/**	设计模式：工厂方法模式
 * 定义一个创建对象的工厂接口，让子类决定实例化哪一个类，将实际创建工作推迟到子类当中
 * Created by mingway on Date:2018-12-08 12:16.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class FactoryMethod {
	public static void main(String[] args) {
		Food x1 = new FactoryForA().get();
		Food x2 = new FactoryForB().get();
	}
}

interface Food{}

class A implements Food{
	public A() {
		System.out.println("This is A");
	}
}
class B implements Food{
	public B() {
		System.out.println("This is B");
	}
}

/**
 * 工厂接口
 */
interface produce{
	Food get();
}

class FactoryForA implements produce{
	@Override
	public Food get() {
		return new A();
	}
}
class FactoryForB implements produce{
	@Override
	public Food get() {
		return new B();
	}
}