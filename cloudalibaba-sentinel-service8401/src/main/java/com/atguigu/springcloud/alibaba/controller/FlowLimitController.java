package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FlowLimitController {


    @GetMapping("/testA")
    public String testA(){
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "------testB";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "ok";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "deal";
    }


    public static void main(String[] args) {
        Map<String,Object> map = new HashMap();
        map.put("aa","111");
        map.put("bb","222");
        map.put("cc","333");
        map.put("dd","444");
        map.put("ee","555");
        map.put("ff","666");

        for (Map.Entry<String,Object> e:map.entrySet()){
            System.out.println(e.getKey()+" : "+e.getValue());
            System.out.println(e.hashCode());
        }

    }
}
