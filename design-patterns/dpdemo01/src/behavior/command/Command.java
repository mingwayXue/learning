package behavior.command;

/** 设计模式：命令模式
 * 将请求封装成对象，以便使用不同的请求、日志、队列等来参数化其他对象。命令模式也支持撤销操作；
 * 例如实例中：Invoker是调用者（司令员），Receiver是被调用者（士兵），MyCommand是命令，实现了Command接口，持有接收对象
 * Created by mingway on Date:2018-12-10 17:40.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class Command {
	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		Command1 command = new MyCommand(receiver);
		Invoker invoker = new Invoker(command);
		invoker.action();
	}
}

//命令接口
interface Command1 {
	public void exec();
}

//命令对象（请求对象）
class MyCommand implements Command1 {

	private Receiver receiver;

	public MyCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void exec() {
		receiver.action();
	}
}

//被调用者
class Receiver {
	public void action(){
		System.out.println("command received!");
	}
}

//调用者
class Invoker {
	private Command1 command;

	public Invoker(Command1 command) {
		this.command = command;
	}

	public void action(){
		command.exec();
	}
}