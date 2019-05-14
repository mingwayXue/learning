package com.xue.scala.controller

import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

/**
  * Created by mingway on Date:2019-05-14 15:34.
  * 修改记录
  * 修改后版本:     修改人：  修改日期:     修改内容:
  */
@RestController
class HelloScala {

    @RequestMapping(value = Array("sayScalaHello"), method = Array(RequestMethod.GET))
    def sayScalaHello() = {
        "say Scala Hello..."
    }
}
