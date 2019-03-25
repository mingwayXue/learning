package structure.proxy;

/** 设计模式：代理模式
 * 		与decorator模式的区别：使用代理模式，代理和真实对象之间的的关系通常在编译时就已经确定了，而装饰者能够在运行时递归地被构造
 * Created by mingway on Date:2018-12-10 10:48.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
/*
	装饰器模式关注于在一个对象上动态的添加方法，然而代理模式关注于控制对对象的访问。
换句话说，用代理模式，代理类（proxy class）可以对它的客户隐藏一个对象的具体信息。
因此，当使用代理模式的时候，我们常常在一个代理类中创建一个对象的实例。
并且，当我们使用装饰器模 式的时候，我们通常的做法是将原始对象作为一个参数传给装饰者的构造器。
 */
public class Proxy implements Sourceable{

	private Source source;

	public Proxy() {
		super();
		this.source = new Source();	//此时已经确定好对象实例，而decorator模式则还没确定
	}

	@Override
	public void method() {
		before();
		source.method();
		after();
	}
	private void after() {
		System.out.println("after proxy!");
	}
	private void before() {
		System.out.println("before proxy!");
	}

	public static void main(String[] args) {
		Sourceable proxy = new Proxy();
		proxy.method();
	}
}

interface Sourceable {
	void method();
}

class Source implements Sourceable {
	@Override
	public void method() {
		System.out.println("the original method!");
	}
}