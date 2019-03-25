package structure.decorator;

/** 设计模式：装饰器模式（就是对已经存在的某些类进行装饰，以此来扩展一些功能(只添加，不覆盖)）
 *
 * Created by mingway on Date:2018-12-10 10:29.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class Decorator {
	public static void main(String[] args) {
		//使用装饰器
		Component component = new ConcreteDecorator(new ConcretComponent());		//传入具体实现类
		component.biu();
	}
}

//基础接口
interface Component {
	void biu();
}

//具体实现类
class ConcretComponent implements Component {
	public void biu() {
		System.out.println("biubiubiu");
	}
}

//装饰类
class Decorator1 implements Component {
	public Component component;

	public Decorator1(Component component) {
		this.component = component;	//与proxy模式最重要的区别，此时还没确定实现类
	}

	public void biu() {
		this.component.biu();
	}
}

//具体装饰类
class ConcreteDecorator extends Decorator1 {

	public ConcreteDecorator(Component component) {
		super(component);
	}

	public void biu() {
		System.out.println("ready?go!");
		this.component.biu();
	}
}



