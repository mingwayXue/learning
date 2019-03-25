package behavior.visitor;

/** 设计模式：访问者模式
 * 访问者模式把数据结构和作用于结构上的操作解耦合，使得操作集合可相对自由地演化。访问者模式适用于数据结构相对稳定算法又易变化的系统；
 * 访问者模式就是一种分离对象数据结构与行为的方法，通过这种分离，可达到为一个被访问者动态添加新的操作而无需做其它的修改的效果。
 * Created by mingway on Date:2018-12-11 9:11.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class VisitorDemo {
	public static void main(String[] args) {
		Visitor visitor = new MyVisitor();
		Subject subject = new MySubject();

		subject.accept(visitor);	//通过visitor访问subject的信息，然后根据这些信息，做出自己的拓展
	}
}

interface Visitor {
	void visit(Subject sub);
}

class MyVisitor implements Visitor {

	@Override
	public void visit(Subject sub) {
		System.out.println("visit the subject："+sub.getSubject());
	}
}

interface Subject {
	void accept(Visitor visitor);
	String getSubject();
}

class MySubject implements Subject {
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String getSubject() {
		return "love";
	}
}