package structure.adapter;

/** 接口适配器
 * Created by mingway on Date:2018-12-10 9:34.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class InterfaceAdapter extends Adapter1{

	//重写a方法
	@Override
	public void a() {
		System.out.println("实现A方法被调用。。。");
	}
}

interface A {
	void a();
	void b();
	void c();
	void d();
	void e();
}

class Adapter1 implements A {

	@Override
	public void a() {
		System.out.println("A....");
	}

	@Override
	public void b() {
		System.out.println("B....");
	}

	@Override
	public void c() {

	}

	@Override
	public void d() {

	}

	@Override
	public void e() {

	}
}
