package structure.flyweight;

import java.util.HashMap;
import java.util.Map;

/** 设计模式：享元模式
 * 所谓享元模式就是运行共享技术有效地支持大量细粒度对象的复用。系统使用少量对象,而且这些都比较相似，状态变化小，可以实现对象的多次复用。
 * 	   享元模式的核心在于享元工厂类，享元工厂类的作用在于提供一个用于存储享元对象的享元池，用户需要对象时，
 * 首先从享元池中获取，如果享元池中不存在，则创建一个新的享元对象返回给用户，并在享元池中保存该新增对象。
 * Created by mingway on Date:2018-12-10 15:11.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class Flyweight {
	public static void main(String[] args) {
		Shape shape1 = FlyweightFactory.getShape("红色");
		shape1.draw();

		Shape shape2 = FlyweightFactory.getShape("灰色");
		shape2.draw();

		Shape shape3 = FlyweightFactory.getShape("绿色");
		shape3.draw();

		Shape shape4 = FlyweightFactory.getShape("红色");
		shape4.draw();

		Shape shape5 = FlyweightFactory.getShape("灰色");
		shape5.draw();

		Shape shape6 = FlyweightFactory.getShape("灰色");
		shape6.draw();

		System.out.println("一共绘制了"+FlyweightFactory.getSum()+"中颜色的圆形");
	}
}

//抽象享元类
abstract class Shape {
	public abstract void draw();
}

//具体享元类
class Circle extends Shape{
	private String color;
	public Circle(String color){
		this.color = color;
	}

	@Override
	public void draw() {
		System.out.println("画了一个" + color +"的圆形");
	}
}

//享元工厂类
class FlyweightFactory{
	static Map<String, Shape> shapes = new HashMap<String, Shape>();

	public static Shape getShape(String key){
		Shape shape = shapes.get(key);
		//如果shape==null,表示不存在,则新建,并且保持到共享池中
		if(shape == null){
			shape = new Circle(key);
			shapes.put(key, shape);
		}
		return shape;
	}

	public static int getSum(){
		return shapes.size();
	}
}