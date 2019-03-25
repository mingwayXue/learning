package builder.singleton;

/**	设计模式：单例模式（饿汉式，加载时初始化）
 * Created by mingway on Date:2018-12-08 14:27.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class EagerSingleton {
	//饿汉单例模式
	//在类加载时就完成了初始化，所以类加载较慢，但获取对象的速度快

	private static EagerSingleton instance = new EagerSingleton();//静态私有成员，已初始化

	private EagerSingleton() {
		//私有构造函数
	}

	public static EagerSingleton getInstance()    //静态，不用同步（类加载时已初始化，不会有多线程的问题）
	{
		return instance;
	}
}
