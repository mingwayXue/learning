package structure.facade;

/** 设计模式：外观模式
 * 外观模式是为了解决类与类之家的依赖关系的，像spring一样，可以将类和类之间的关系配置到配置文件中，而外观模式就是将他们的关系放在一个Facade类中，降低了类类之间的耦合度，该模式中没有涉及到接口
 * Created by mingway on Date:2018-12-10 12:09.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class Facade {
	public static void main(String[] args) {
		Computer computer = new Computer();
		computer.startup();
		computer.shutdown();
	}

}

class CPU {

	public void startup(){
		System.out.println("cpu startup!");
	}

	public void shutdown(){
		System.out.println("cpu shutdown!");
	}
}

class Memory {

	public void startup(){
		System.out.println("memory startup!");
	}

	public void shutdown(){
		System.out.println("memory shutdown!");
	}
}

class Disk {

	public void startup(){
		System.out.println("disk startup!");
	}

	public void shutdown(){
		System.out.println("disk shutdown!");
	}
}

/**
 * 依赖CPU Memory Disk类，但是又没有使用接口（外观模式）
 */
class Computer {
	private CPU cpu;
	private Memory memory;
	private Disk disk;

	public Computer(){
		cpu = new CPU();
		memory = new Memory();
		disk = new Disk();
	}

	public void startup(){
		System.out.println("start the computer!");
		cpu.startup();
		memory.startup();
		disk.startup();
		System.out.println("start computer finished!");
	}

	public void shutdown(){
		System.out.println("begin to close the computer!");
		cpu.shutdown();
		memory.shutdown();
		disk.shutdown();
		System.out.println("computer closed!");
	}
}

