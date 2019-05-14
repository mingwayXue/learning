package com.xue.scala.repository

import com.xue.scala.domain.MetaTable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
  * Created by mingway on Date:2019-05-14 16:17.
  * 修改记录
  * 修改后版本:     修改人：  修改日期:     修改内容:
  */
@Repository
trait MetaTableRepository extends CrudRepository[MetaTable, Int]{

}
