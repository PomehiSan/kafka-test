package com.pomehi.controller;

import com.pomehi.kafka.MyKafkaProd;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    MyKafkaProd myKafkaProd;

    public Test(MyKafkaProd myKafkaProd) {
        this.myKafkaProd = myKafkaProd;
    }

    @GetMapping("/test")
    public void test() {
        myKafkaProd.sendMessage("Тебе сообщение лох");
    }

}
