package cn.zing.order.sentinel.controller;

import cn.zing.order.sentinel.domain.User;
import cn.zing.order.sentinel.service.IOrderService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-02 11:15
 */
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    IOrderService orderService;


    @GetMapping("/add")
    public String add() {
        System.out.println("下单成功");
        return "生成订单";
    }

    @GetMapping("/flow")
//    @SentinelResource(value = "flow", blockHandler = "flowBlockHandler")
    public String flow() {
        System.out.println("正常访问");
        return "正常访问";
    }

    @GetMapping("/flowThread")
    @SentinelResource(value = "flow", blockHandler = "flowBlockHandler")
    public String flowThread() {
        System.out.println("正常访问");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "正常访问";
    }
    // 返回值类型必须和资源一样
    public String flowBlockHandler(BlockException ex) {
        ex.printStackTrace();
        return "流控";
    }

    // 关联流控 访问/add 触发/get
    @GetMapping("/get")
    public String get() {
        return "查询订单";
    }

    @GetMapping("/test1")
    public User test1() {
        return orderService.getUser();
    }

    @GetMapping("/test2")
    public User test2() {
        return orderService.getUser();
    }
}


