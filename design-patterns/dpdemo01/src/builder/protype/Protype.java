package builder.protype;

/** 设计模式：原型模式
 * 一般用于对象的复制（分为浅拷贝和深拷贝）
 * 	（1）实现Cloneable接口。在java语言有一个Cloneable接口，它的作用只有一个，就是在运行时通知虚拟机可以安全地在实现了此接口的类上使用clone方法。在java虚拟机中，只有实现了这个接口的类才可以被拷贝，否则在运行时会抛出CloneNotSupportedException异常。
 *　（2）重写Object类中的clone方法。Java中，所有类的父类都是Object类，Object类中有一个clone方法，作用是返回对象的一个拷贝，但是其作用域protected类型的，一般的类无法调用，因此Prototype类需要将clone方法的作用域修改为public类型。
 *	注意：
 *		1.拷贝不会执行构造函数
 *		2.在应用过程中，应尽量使用深拷贝（对象里的实例一同拷贝）
 *		3.在使用clone方法时，类的成员变量不能使用final修饰
 * Created by mingway on Date:2018-12-10 8:45.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class Protype {
	public static void main(String[] args) {
		shallowCopy();

		deepCopy();
	}

	public static void shallowCopy() {
		// 1.构建书本对象
		Book book1 = new Book();
		// 2.编辑书本，添加图片
		book1.setTitle("书1");
		book1.addImage("浅拷贝");
		book1.showBook();

		// 以原型文档为原型，拷贝一份副本
		Book book2 = (Book) book1.clone();
		book2.showBook();
		// 修改图书副本，不会影响原始书本
		book2.setTitle("书2");
		book2.addImage("图2");	//会影响book1的image
		book2.showBook();

		// 再次打印原始书本
		book1.showBook();
	}

	public static void deepCopy() {
		// 1.构建书本对象
		Book1 book1 = new Book1();
		// 2.编辑书本，添加图片
		book1.setTitle("书1");
		book1.addImage("深拷贝");
		book1.showBook();

		// 以原型文档为原型，拷贝一份副本
		Book1 book2 = (Book1) book1.clone();
		book2.showBook();
		// 修改图书副本，不会影响原始书本
		book2.setTitle("书2");
		book2.addImage("图2");	//不会影响book1的image
		book2.showBook();

		// 再次打印原始书本
		book1.showBook();
	}
}
