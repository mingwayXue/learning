package com.xue.scala.controller

import com.xue.scala.domain.MetaTable
import com.xue.scala.service.MetaTableService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

/**
  * Created by mingway on Date:2019-05-14 16:23.
  * 修改记录
  * 修改后版本:     修改人：  修改日期:     修改内容:
  */
@RestController
@RequestMapping(Array("/meta/table"))
class MetaTableController @Autowired()(metaTableService: MetaTableService) {

    @RequestMapping(value = Array("/"), method = Array(RequestMethod.POST))
    def save(metaTable: MetaTable) = {
        metaTableService.save(metaTable)
        "success"
    }

    @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
    def query(metaTable: MetaTable) = {
        metaTableService.query()
//        "success"
    }
}
