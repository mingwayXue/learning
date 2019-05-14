package com.xue.scala.service

import com.xue.scala.domain.MetaTable
import com.xue.scala.repository.MetaTableRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
  * Created by mingway on Date:2019-05-14 16:19.
  * 修改记录
  * 修改后版本:     修改人：  修改日期:     修改内容:
  */
@Service
class MetaTableService @Autowired()(metaTableRepository: MetaTableRepository) {

    @Transactional
    def save(metaTable: MetaTable) = {
        metaTableRepository.save(metaTable)
    }

    def query() = {
        metaTableRepository.findAll()
    }
}
