package builder.builder;

/** 设计模式：建造者模式
 * Created by mingway on Date:2018-12-10 8:29.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class Builder {

	public static void main(String[] args) {
		//建造摩拜单车
		MoBikeBuilder moBikeBuilder = new MoBikeBuilder();
		EngineeringDepartment ed1 = new EngineeringDepartment(moBikeBuilder);
		//执行建造(填充属性)
		ed1.Construct();
		//产出单车
		Bike moBike = moBikeBuilder.getBike();
		System.out.println(moBike.getFrame());

		// 建造ofo单车
		BikeBuilder ofoBikeBuilder = new OfoBikeBuilder();
		EngineeringDepartment ed2 = new EngineeringDepartment(ofoBikeBuilder);
		ed2.Construct();// 指导组装
		Bike ofoBike = ofoBikeBuilder.getBike();
		System.out.println(ofoBike.getFrame());
	}
}

/**
 * description:
 * 自行车生产线接口
 * Created by chenzanjin on 2017/2/22.
 */
interface BikeBuilder {
	// 组装轮胎
	public void buildTyres();
	// 组装车架
	public void buildFrame();
	// 组装GPS定位装置
	public void buildGPS();
	// 获取自行车
	public Bike getBike();
}

/**
 * description:
 * 摩拜单车生产线
 * Created by chenzanjin on 2017/2/22.
 */
class MoBikeBuilder implements BikeBuilder {
	// 拥有单车对象
	Bike bike = new Bike();
	@Override
	public void buildTyres() {
		bike.setTyre("橙色轮胎");
	}

	@Override
	public void buildFrame() {
		bike.setFrame("橙色车架");
	}

	@Override
	public void buildGPS() {
		bike.setGps("mobike定制版GPS定位装置");
	}

	@Override
	public Bike getBike() {
		return bike;
	}
}

/**
 * description:
 * ofo单车生产线
 * Created by chenzanjin on 2017/2/22.
 */
class OfoBikeBuilder implements BikeBuilder {
	// 拥有单车对象
	Bike bike = new Bike();
	@Override
	public void buildTyres() {
		bike.setTyre("黑色轮胎");
	}

	@Override
	public void buildFrame() {
		bike.setFrame("黄色车架");
	}

	@Override
	public void buildGPS() {
		bike.setGps("ofo定制版GPS定位装置");
	}

	@Override
	public Bike getBike() {
		return bike;
	}
}

/**
 * description:
 * 自行车对象
 * Created by chenzanjin on 2017/2/22.
 */
class Bike {
	// 轮胎
	private String tyre;
	// 车架
	private String frame;
	// GPS定位装置
	private String gps;

	public String getTyre() {
		return tyre;
	}

	public void setTyre(String tyre) {
		this.tyre = tyre;
	}

	public String getFrame() {
		return frame;
	}

	public void setFrame(String frame) {
		this.frame = frame;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}
}

/**
 * description：
 * 工程部门作为指挥者，可以指导生产部门作业
 * Created by Administrator on 2017/2/22.
 */
class EngineeringDepartment {
	// 用户告知指挥者想要什么样的单车
	BikeBuilder bikeBuilder;
	public EngineeringDepartment(BikeBuilder bikeBuilder){
		this.bikeBuilder = bikeBuilder;
	}

	// 指导组装单车
	public void Construct(){
		bikeBuilder.buildTyres();
		bikeBuilder.buildFrame();
		bikeBuilder.buildGPS();
	}
}