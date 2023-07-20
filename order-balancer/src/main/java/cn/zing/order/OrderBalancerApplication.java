package cn.zing.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-02 11:19
 */
@SpringBootApplication
public class OrderBalancerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderBalancerApplication.class, args);
    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate1(RestTemplateBuilder builder) {

        return builder.build();

    }
}


