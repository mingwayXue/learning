package structure.adapter;

/** 类适配器
 * Created by mingway on Date:2018-12-10 9:24.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class ClassAdapter extends UsbImpl implements Ps2 {

	@Override
	public void isPs2() {
		isUsb();
	}
}

interface Usb {
	void isUsb();
}

interface Ps2 {
	void isPs2();
}

class UsbImpl implements Usb {

	@Override
	public void isUsb() {
		System.out.println("使用USB接口");
	}
}




