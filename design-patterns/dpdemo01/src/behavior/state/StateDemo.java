package behavior.state;

/** 设计模式：状态模式
 * 当对象的状态改变时，同时改变其行为（表现）
 * Created by mingway on Date:2018-12-11 9:03.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class StateDemo {
	public static void main(String[] args) {
		State state = new State();
		Context context = new Context(state);

		//设置第一种状态
		state.setValue("state1");
		context.method();

		//设置第二种状态
		state.setValue("state2");
		context.method();
	}

}

//状态类
class State {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void method1(){
		System.out.println("execute the first opt!");
	}

	public void method2(){
		System.out.println("execute the second opt!");
	}
}

//状态修改类
class Context {

	private State state;

	public Context(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void method() {
		if (state.getValue().equals("state1")) {
			state.method1();
		} else if (state.getValue().equals("state2")) {
			state.method2();
		}
	}
}