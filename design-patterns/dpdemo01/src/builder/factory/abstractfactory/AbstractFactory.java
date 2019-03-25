package builder.factory.abstractfactory;

/** 抽象工厂模式：（工厂方法的基础上，出现了多个产品族）
 * 抽象工厂是围绕一个超级工厂创建其他工厂，该超级工厂又称为其他工厂的工厂。提供一个创建一系列相关或相互依赖对象的接口，而无需指定他们具体的类。
 * Created by mingway on Date:2018-12-10 10:17.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public interface AbstractFactory {
	Button createButton();

	Text createText();
}

class LinuxFactory implements AbstractFactory {
	@Override
	public Button createButton() {
		return new LinuxButton();
	}
	@Override
	public Text createText() {
		return new LinuxText();
	}
}

class WindowsFactory implements AbstractFactory {
	@Override
	public Button createButton() {
		return new WindowsButton();
	}
	@Override
	public Text createText() {
		return new WindowsText();
	}
}

interface Button {
	void processEvent();
}

interface Text {
	void getWholeText();
}

class LinuxButton implements Button {
	@Override
	public void processEvent() {
		System.out.println("Inside LinuxButton::processEvent() method.");
	}
}

class WindowsButton implements Button {
	@Override
	public void processEvent() {
		System.out.println("Inside WindowsButton::processEvent() method.");
	}
}

class LinuxText implements Text {
	@Override
	public void getWholeText() {
		System.out.println("Inside LinuxText::getWholeText() method.");
	}
}

class WindowsText implements Text {
	@Override
	public void getWholeText() {
		System.out.println("Inside WindowsText::getWholeText() method.");
	}
}












