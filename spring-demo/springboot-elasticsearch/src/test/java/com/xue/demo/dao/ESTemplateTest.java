package com.xue.demo.dao;

import com.xue.demo.SpringbootElasticsearchApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mingway on Date:2018-08-23 15:48.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Slf4j
public class ESTemplateTest extends SpringbootElasticsearchApplicationTests {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	//使用elasticsearchTemplate查询所有
	@Test
	public void findAll() {
		Client client = elasticsearchTemplate.getClient();

		//指定查询的index和type
		SearchRequestBuilder srb = client.prepareSearch("es_demo").setTypes("student");

		//查询所有
		SearchResponse sr = srb.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();

		SearchHits hits = sr.getHits();
		List<Map<String, Object>> list = new ArrayList<>();
		for (SearchHit hit : hits) {
			Map<String, Object> map = hit.getSource();
			list.add(map);
		}

		log.info("list = {}", list);
	}
}
