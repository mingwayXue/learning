package com.xue.webmagic.demo;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/** 爬取博客园“java”标题信息
 * Created by mingway on Date:2019-05-17 11:22.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class MyProcessor implements PageProcessor {
	// 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	private static int count =0;

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
//		//判断链接是否符合http://www.cnblogs.com/任意个数字字母-/p/7个数字.html格式
//		if(!page.getUrl().regex("http://www.cnblogs.com/[a-z 0-9 -]+/p/[0-9]{7}.html").match()){
//			//加入满足条件的链接
//			page.addTargetRequests(
//					page.getHtml().xpath("//*[@id=\"post_list\"]/div/div[@class='post_item_body']/h3/a/@href").all());
//		}else{
//			//获取页面需要的内容
//			System.out.println("抓取的内容："+
//					page.getHtml().xpath("//*[@id=\"Header1_HeaderTitle\"]/text()").get()
//			);
//			count ++;
//		}
		page.addTargetRequest("http://www.cnblogs.com/cate/java/1");

		List<String> htmls = page.getHtml().xpath("//div[@class='post_item']//a[@class='titlelnk']/html()").all();
		System.out.println(htmls);

	}

	public static void main(String[] args) {
		long startTime, endTime;
		System.out.println("开始爬取...");
		startTime = System.currentTimeMillis();
		Spider.create(new MyProcessor()).addUrl("http://www.cnblogs.com/cate/java/").thread(5).run();
		endTime = System.currentTimeMillis();
		System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了"+count+"条记录");
	}

}
