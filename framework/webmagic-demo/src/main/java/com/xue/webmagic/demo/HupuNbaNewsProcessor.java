package com.xue.webmagic.demo;

import com.alibaba.fastjson.JSON;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 批量爬取hupu nba 的文章内容信息
 * Created by mingway on Date:2019-05-17 15:35.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class HupuNbaNewsProcessor implements PageProcessor {

	// 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

	public static List<Map<String, Object>> mapList = new ArrayList<>();

	@Override
	public void process(Page page) {
		// 文章页，匹配 https://voice.hupu.com/nba/七位数字.html
		if (page.getUrl().regex("https://voice\\.hupu\\.com/nba/[0-9]{7}\\.html").match()) {
			page.putField("Title", page.getHtml().xpath("/html/body/div[4]/div[1]/div[1]/h1/text()").toString());
			page.putField("Content",
					page.getHtml().xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/p/text()").all().toString());
//			Map<String, Object> map = new HashMap<>();
//			map.put("title", page.getHtml().xpath("/html/body/div[4]/div[1]/div[1]/h1/text()").toString());
//			map.put("content", page.getHtml().xpath("/html/body/div[4]/div[1]/div[2]/div/div[2]/p/text()").all().toString());
//
//			mapList.add(map);
		}
		// 列表页
		else {
			// 文章url
			page.addTargetRequests(
					page.getHtml().xpath("/html/body/div[3]/div[1]/div[2]/ul/li/div[1]/h4/a/@href").all());
			// 翻页url
			page.addTargetRequests(
					page.getHtml().xpath("/html/body/div[3]/div[1]/div[3]/a[@class='page-btn-prev']/@href").all());
		}
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new HupuNbaNewsProcessor()).addUrl("https://voice.hupu.com/nba/1").addPipeline(new JsonFilePipeline())
				.thread(3).run();
		System.out.println(JSON.toJSONString(HupuNbaNewsProcessor.mapList));
	}
}
