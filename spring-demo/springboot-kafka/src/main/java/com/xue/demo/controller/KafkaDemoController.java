package com.xue.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mingway on 2018/8/17.
 */
@RestController
@Slf4j
public class KafkaDemoController {

    private static final String INDEX_TOPIC = "kafka_demo";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = INDEX_TOPIC)
    public void kafkaHandleMsg(String content) {
        log.info("------------kafka content-info:{}----------", content);
    }

    @RequestMapping("/hello_kafka/{content}")
    public void test01(@PathVariable String content) {
        kafkaTemplate.send(INDEX_TOPIC, content);
        log.info("----------------kafka producer send message----------------");
    }

}
