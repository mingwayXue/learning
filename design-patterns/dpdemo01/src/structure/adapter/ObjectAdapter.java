package structure.adapter;

/** 对象适配器
 * Created by mingway on Date:2018-12-10 9:31.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class ObjectAdapter implements Ps2{

	private Usb usb;

	public ObjectAdapter() {
		this.usb = new UsbImpl();
	}

	@Override
	public void isPs2() {
		usb.isUsb();
	}
}
