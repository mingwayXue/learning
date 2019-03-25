package com.xue.demo.dao;

import com.xue.demo.domain.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * Created by mingway on Date:2018-08-23 15:17.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Component
public interface StudentDao extends ElasticsearchRepository<Student, Long> {

	Student queryStudentById(Long id);
}
