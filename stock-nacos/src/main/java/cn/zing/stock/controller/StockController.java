package cn.zing.stock.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-02 11:17
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/reduct")
    public String add() {
        System.out.println("扣减库存");
        return "扣减库存->" + port;
    }
}


