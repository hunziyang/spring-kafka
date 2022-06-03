package com.yang.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class KafkaController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/{content}")
    public String test(@PathVariable("content") String content){
        kafkaTemplate.send("yang",content,content);
        return "success";
    }

    @KafkaListener(topics = "yang",groupId = "yang")
    public void listener(String input){
        System.out.println("c:"+input);
    }

}
