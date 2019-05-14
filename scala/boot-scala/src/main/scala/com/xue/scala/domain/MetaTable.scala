package com.xue.scala.domain

import javax.persistence.{Entity, GeneratedValue, Id, Table}

import scala.beans.BeanProperty

/**
  * Created by mingway on Date:2019-05-14 16:08.
  * 修改记录
  * 修改后版本:     修改人：  修改日期:     修改内容:
  */
@Entity
@Table
class MetaTable {

    @Id
    @GeneratedValue
    @BeanProperty
    var id: Int = _

    @BeanProperty
    var name: String = _

    @BeanProperty
    var tableType: String = _

    @BeanProperty
    var dbId: Int = _
}
