package behavior.strategy;

/** 设计模式：策略模式
 * 策略模式定义了一系列算法，并将每个算法封装起来，使他们可以相互替换，且算法的变化不会影响到使用算法的客户。
 * 需要设计一个接口，为一系列实现类提供统一的方法，多个实现类实现该接口，设计一个抽象类（可有可无，属于辅助类），提供辅助函数
 * Created by mingway on Date:2018-12-10 15:31.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class Strategy {
	public static void main(String[] args) {
		String test = "8+2";
		ICalculator iCalculator = new Plus();
		System.out.println(iCalculator.calculate(test));
	}
}

interface ICalculator {
	int calculate(String exp);
}

abstract class AbstractCalculator {

	public int[] split(String exp,String opt){		//默认算法的方法
		String array[] = exp.split(opt);
		int arrayInt[] = new int[2];
		arrayInt[0] = Integer.parseInt(array[0]);
		arrayInt[1] = Integer.parseInt(array[1]);
		return arrayInt;
	}
}

/**
 * 三个实现类算法（用户自选）
 */
class Plus extends AbstractCalculator implements ICalculator {	//+
	@Override
	public int calculate(String exp) {
		int arrayInt[] = split(exp,"\\+");
		return arrayInt[0]+arrayInt[1];
	}
}
class Minus extends AbstractCalculator implements ICalculator {	//-
	@Override
	public int calculate(String exp) {
		int arrayInt[] = split(exp,"-");
		return arrayInt[0]-arrayInt[1];
	}
}
class Multiply extends AbstractCalculator implements ICalculator {	//*
	@Override
	public int calculate(String exp) {
		int arrayInt[] = split(exp,"\\*");
		return arrayInt[0]*arrayInt[1];
	}
}
