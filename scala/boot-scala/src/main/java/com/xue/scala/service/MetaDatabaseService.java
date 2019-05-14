package com.xue.scala.service;

import com.xue.scala.domain.MetaDatabase;
import com.xue.scala.repository.MetaDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mingway on Date:2019-05-14 15:50.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@Service
public class MetaDatabaseService {

	@Autowired
	private MetaDatabaseRepository metaDatabaseRepository;

	public void save(MetaDatabase metaDatabase) {
		metaDatabaseRepository.save(metaDatabase);
	}

	public Iterable<MetaDatabase> query() {
		return metaDatabaseRepository.findAll();
	}

}
