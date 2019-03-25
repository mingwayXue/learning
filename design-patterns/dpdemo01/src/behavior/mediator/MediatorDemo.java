package behavior.mediator;

/** 设计模式：中介者模式
 * 定义了一个中介对象来封装一系列对象之间的交互关系。中介者使各个对象之间不需要显式地相互引用，从而使耦合性降低，而且可以独立地改变它们之间的交互行为。
 * 类似spring容器的功能
 * Created by mingway on Date:2018-12-11 9:33.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class MediatorDemo {
	public static void main(String[] args) {
		Mediator mediator = new MyMediator();
		mediator.createMediator();

		mediator.workAll();
	}
}

interface Mediator {
	void createMediator();
	void workAll();
}

class MyMediator implements Mediator {

	private User user1;
	private User user2;

	public User getUser1() {
		return user1;
	}

	public User getUser2() {
		return user2;
	}

	@Override
	public void createMediator() {
		user1 = new User1(this);
		user2 = new User2(this);
	}

	@Override
	public void workAll() {
		user1.work();
		user2.work();
	}
}

abstract class User {

	private Mediator mediator;

	public Mediator getMediator(){
		return mediator;
	}

	public User(Mediator mediator) {
		this.mediator = mediator;
	}

	public abstract void work();
}

class User1 extends User {

	public User1(Mediator mediator){
		super(mediator);
	}

	@Override
	public void work() {
		System.out.println("user1 exe!");
	}
}

class User2 extends User {

	public User2(Mediator mediator){
		super(mediator);
	}

	@Override
	public void work() {
		System.out.println("user2 exe!");
	}
}