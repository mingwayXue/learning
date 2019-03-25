package structure.adapter;

/** 设计模式：适配器模式
 * 	适配器模式的作用就是在原来的类上提供新功能；
 * 	主要可分为3种：类适配器、对象适配器、接口适配器
 * 	类适配器与对象适配器的使用场景一致，仅仅是实现手段稍有区别，二者主要用于如下场景：
 *　（1）想要使用一个已经存在的类，但是它却不符合现有的接口规范，导致无法直接去访问，这时创建一个适配器就能间接去访问这个类中的方法。
 *　（2）我们有一个类，想将其设计为可重用的类（可被多处访问），我们可以创建适配器来将这个类来适配其他没有提供合适接口的类。
 * 以上两个场景其实就是从两个角度来描述一类问题，那就是要访问的方法不在合适的接口里，一个从接口出发（被访问），一个从访问出发（主动访问）。
 *	接口适配器使用场景：
 *　（1）想要使用接口中的某个或某些方法，但是接口中有太多方法，我们要使用时必须实现接口并实现其中的所有方法，可以使用抽象类来实现接口，并不对方法进行实现（仅置空），然后我们再继承这个抽象类来通过重写想用的方法的方式来实现。这个抽象类就是适配器。
 * Created by mingway on Date:2018-12-10 9:18.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class Adapter {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}

	public static void test1() {
		Ps2 ps2 = new ClassAdapter();
		ps2.isPs2();
	}

	public static void test2() {
		Ps2 ps2 = new ObjectAdapter();
		ps2.isPs2();
	}

	public static void test3() {
		A a = new InterfaceAdapter();
		a.a();
		a.b();
	}
}


