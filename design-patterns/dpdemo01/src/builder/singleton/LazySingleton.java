package builder.singleton;

/**	设计模式：单例模式（懒汉式，加载时不初始化）
 * Created by mingway on Date:2018-12-08 14:27.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class LazySingleton {
	//懒汉式单例模式
	//比较懒，在类加载时，不创建实例，因此类加载速度快，但运行时获取对象的速度慢


	private static LazySingleton intance = null;//静态私用成员，没有初始化

	private LazySingleton() {
		//私有构造函数
	}

	public static synchronized LazySingleton getInstance()    //静态，同步，公开访问点
	{
		if (intance == null) {
			intance = new LazySingleton();
		}
		return intance;
	}
}
