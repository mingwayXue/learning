# SpringBoot集成系列（1）：ElasticSearch

> **版本**：
> **SpringBoot**  	2.0.4.RELEASE
> **ElasticSearch**	6.2.4
> 

[TOC]

##  1.导入jar包

```java
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-elasticsearch</artifactId>
</dependency>
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
	<optional>true</optional>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>
```

> 注：lombok包必须结合插件使用，例如IDEA的lombok插件：`Lombok Plugin `

其中 `spring-boot-starter-data-elasticsearch` 包是 **spring-data** 提供的便捷式操作，使用它，可以快速的对ElasticSearch进行增删改查，使用方式类似于 `spring-data-jpa` ；

`lombok` 是一个可以简化开发的jar包，可以通过一些简单的注解达到简化开发的目的，比如：@Data,@Getter,@Slf4j等注解；

## 2.配置文件

springboot的配置文件（application.yml）如下：

```java
server:
  port: 8080
spring:
  data:
    elasticsearch:
      cluster-name: elasticsearch     #默认的集群名
      cluster-nodes: 127.0.0.1:9300   #配置es节点信息，逗号分隔，如果没有指定，则启动ClientNode（9200端口是http查询使用的。9300集群使用。这里使用9300.）
      repositories:
        enabled: true                 #开启类似于Spring Jpa的Repository操作
```

## 3.实体类

```java
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
@Data
//指定索引名，类型名，分片数，副本数
@Document(indexName = "es_demo", type = "student", shards = 3, replicas = 0)
public class Student {

	@Id
	private Long id;

	@Field
	private String name;

	@Field
	private Integer age;

	@Field
	private String remark;
}
```

> 注：ES中的 **索引(Index)** 可以类比为关系型数据库中的数据库，**类型(Type)** 类比为数据库表，**文档(Document)** 类比为数据库表中的数据

## 4.Dao层操作

```java
import com.xue.demo.domain.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
@Component	//@Component可以不添加（会报错），可以通过设置编译器忽略该报错
public interface StudentDao extends ElasticsearchRepository<Student, Long> {
	Student queryStudentById(Long id);
}
```

> `spring-boot-starter-data-elasticsearch` 的Dao层接口方法命名规则跟[Jpa命名规则](https://blog.csdn.net/liyang_nash/article/details/80704089)类似

##  5.测试

公共测试方法：Springboot自动添加的，后续的测试方法可通过继承该方法来进行Junit测试

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootElasticsearchApplicationTests {
	@Test
	public void contextLoads() {
	}
}
```

### 5.1简单查询

简单查询可以使用 `spring-boot-starter-data-elasticsearch` 提供的api接口或者自定义命名规则的方法去操作ES数据库；

```java
import com.xue.demo.SpringbootElasticsearchApplicationTests;
import com.xue.demo.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
@Slf4j
public class StudentDaoTest extends SpringbootElasticsearchApplicationTests{
	@Autowired
	private StudentDao dao;

	@Test
	public void save(){
		Student student = new Student();
		student.setId(2L);
		student.setAge(11);
		student.setName("李四");
		dao.save(student);
	}

	@Test
	public void queryById(){
		Student student = dao.queryStudentById(1L);
		log.info("student = {}", student);
	}
}
```

### 5.2复杂查询

ES的复杂查询可以通过 **ElasticsearchTemplate** 来实现ES自身的复杂、聚合查询等； 

```java
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
```

