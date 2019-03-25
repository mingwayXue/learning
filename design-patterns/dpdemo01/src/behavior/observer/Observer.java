package behavior.observer;

import java.util.Enumeration;
import java.util.Vector;

/** 设计模式：观察者模式
 * 当一个对象变化时，其它依赖该对象的对象都会收到通知，并且随着变化（类似于邮件订阅功能）
 * Created by mingway on Date:2018-12-10 16:01.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class Observer {
	public static void main(String[] args) {
		Subject sub = new MySubject();
		sub.add(new Observer1());
		sub.add(new Observer2());

		sub.operation();
	}
}

interface Observer0 {
	void update();
}

class Observer1 implements Observer0 {

	@Override
	public void update() {
		System.out.println("observer1 has received!");
	}
}
class Observer2 implements Observer0 {

	@Override
	public void update() {
		System.out.println("observer2 has received!");
	}
}

interface Subject {
	/*增加观察者*/
	void add(Observer0 observer0);

	/*删除观察者*/
	void del(Observer0 observer0);

	/*通知所有的观察者*/
	void notifyObservers();

	/*自身的操作*/
	void operation();
}

abstract class AbstractSubject implements Subject {	//抽象类实现接口，可以不需要实现接口的所有方法；普通类实现接口，必须实现所有方法

	private Vector<Observer0> vector = new Vector<Observer0>();
	@Override
	public void add(Observer0 observer0) {
		vector.add(observer0);
	}

	@Override
	public void del(Observer0 observer0) {
		vector.remove(observer0);
	}

	@Override
	public void notifyObservers() {
		Enumeration<Observer0> enumo = vector.elements();
		while(enumo.hasMoreElements()){
			enumo.nextElement().update();
		}
	}
}

class MySubject extends AbstractSubject {
	@Override
	public void operation() {
		System.out.println("update self!");
		notifyObservers();	//通知（观察者模式）
	}
}
