package behavior.interpreter;

/** 设计模式：解释器模式
 * 对于一些固定文法构建一个解释句子的解释器，这种模式实现了一个表达式接口，该接口解释一个特定的上下文；
 * 使用场景：
 * 		1、可以将一个需要解释执行的语言中的句子表示为一个抽象语法树。
 * 		2、一些重复出现的问题可以用一种简单的语言来进行表达。
 * 		3、一个简单语法需要解释的场景。
 * Created by mingway on Date:2018-12-11 9:39.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class Interpreter {
	public static void main(String[] args) {

		// 计算9+2-8的值
		int result = new Minus().interpret((new Context(new Plus().interpret(new Context(9, 2)), 8)));
		System.out.println(result);
	}
}

//上下文
class Context {
	private int num1;
	private int num2;

	public Context(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
	}

	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
}

//表达式
interface Expression {
	int interpret(Context context);
}

//加法表达式
class Plus implements Expression {
	@Override
	public int interpret(Context context) {
		return context.getNum1()+context.getNum2();
	}
}

//减法表达式
class Minus implements Expression {
	@Override
	public int interpret(Context context) {
		return context.getNum1()-context.getNum2();
	}
}