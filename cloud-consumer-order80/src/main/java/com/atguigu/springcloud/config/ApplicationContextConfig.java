package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @auther zzyy
 * @create 2020-02-18 17:27
 */
@Configuration
public class ApplicationContextConfig
{
    @Bean
    @LoadBalanced//负载均衡使用注解
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}
//applicationContext.xml <bean id="" class="">