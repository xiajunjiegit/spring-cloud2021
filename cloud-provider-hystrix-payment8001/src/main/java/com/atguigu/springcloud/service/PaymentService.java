package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return "线程池: "+Thread.currentThread().getName()+" paymentInfo_OK,id: "+id+"\t"+"哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        Integer num = 5;
        try {
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: "+Thread.currentThread().getName()+" paymentInfo_Timeout,id: "+id+"\t"+"哈哈~"+" 超时（秒）:"+num;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池: "+Thread.currentThread().getName()+" 服务器异常，请稍后再试,id: "+id+"\t"+"嘤嘤~";
    }


    //=========服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), 
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
            })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if(id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }
}
