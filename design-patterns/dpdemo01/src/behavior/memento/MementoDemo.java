package behavior.memento;

/** 设计模式：备忘录模式
 * 主要目的是保存一个对象的某个状态，以便在适当的时候恢复对象
 * 例如：假设有原始类A，A中有各种属性，A可以决定需要备份的属性，备忘录类B是用来存储A的一些内部状态，类C呢，就是一个用来存储备忘录的，且只能存储，不能修改等操作
 * Created by mingway on Date:2018-12-11 8:46.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class MementoDemo {

	public static void main(String[] args) {
		Original original = new Original("test");
		Storage storage = new Storage(original.createMemento());
		System.out.println("存储的值：" + original.getValue());

		original.setValue("demo");
		System.out.println("修改后的值：" + original.getValue());

		original.restoreMemento(storage.getMemento());
		System.out.println("恢复后的值：" + original.getValue());
	}
}

//原始类，可创建备忘录对象
class Original {
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Original(String value) {
		this.value = value;
	}
	public Memento createMemento(){
		return new Memento(value);
	}
	public void restoreMemento(Memento memento){
		this.value = memento.getValue();
	}
}

//备忘录对象，包含相关类信息
class Memento {
	private String value;
	public Memento(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

//存储类，存储备忘类
class Storage {
	private Memento memento;
	public Storage(Memento memento) {
		this.memento = memento;
	}
	public Memento getMemento() {
		return memento;
	}
	public void setMemento(Memento memento) {
		this.memento = memento;
	}
}

