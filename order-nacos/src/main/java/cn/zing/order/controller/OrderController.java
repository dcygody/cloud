package cn.zing.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-02 11:15
 */
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    RestTemplate restTemplate1;


    @GetMapping("/add")
    public String add() {
        System.out.println("下单成功");
        // restTemplate 远程调用
        String msg = restTemplate1.getForObject("http://stock-service/stock/reduct", String.class);
        return "Hello World: " + msg;
    }
}


