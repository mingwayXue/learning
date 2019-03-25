package builder.factory.abstractfactory;

/**
 * Created by mingway on Date:2018-12-10 10:24.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class AbstractFactoryTest {

	public static void main(String[] args) {
		AbstractFactory linuxAbstractFactory = new LinuxFactory();
		linuxAbstractFactory.createButton().processEvent();
		linuxAbstractFactory.createText().getWholeText();

		AbstractFactory winsAbstractFactory = new WindowsFactory();
		winsAbstractFactory.createButton().processEvent();
		winsAbstractFactory.createText().getWholeText();
	}
}
