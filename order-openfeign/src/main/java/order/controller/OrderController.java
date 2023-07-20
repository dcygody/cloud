package order.controller;

import order.feign.ProductFeignService;
import order.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-02 11:15
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    StockFeignService stockFeignService;

    @Autowired
    ProductFeignService productFeignService;

    @GetMapping("/add")
    public String add() {
        System.out.println("下单成功");

        String msg = stockFeignService.add();
        String p = productFeignService.get(new Random().nextInt(1000));
        return "Hello Feign: " + msg + "->" + p;
    }
}


