package builder.protype;

import java.util.ArrayList;

/** 浅拷贝
 * Created by mingway on Date:2018-12-10 8:53.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class Book implements Cloneable {

	private String title;// 标题
	private ArrayList<String> image = new ArrayList<String>();// 图片名列表

	public Book() {
		super();
	}

	/** 浅拷贝
	 * 重写拷贝方法
	 */
	@Override
	protected Book clone()  {
		try {
			Book book = (Book) super.clone();
			return book;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getImage() {
		return image;
	}

	public void addImage(String img) {
		this.image.add(img);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 打印内容
	 */
	public void showBook() {
		System.out.println("----------------------Start----------------------");

		System.out.println("title：" + title);
		for (String img : image) {
			System.out.println("image name:" + img);
		}

		System.out.println("----------------------End----------------------");
	}
}