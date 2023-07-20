package product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-02 11:17
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/{id}")
    public String get(@PathVariable Integer id) {
        System.out.println("查询商品: " + id);
        return "查询商品: " + id + "->" + port;
    }
}


