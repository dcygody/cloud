package cn.zing.order.sentinel.service;

import cn.zing.order.sentinel.domain.User;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-05 13:14
 */
@Service
public class OrderServiceImpl implements IOrderService{


    @Override
    @SentinelResource(value = "getUser", blockHandler = "flowBlockUserHandler")
    public User getUser() {
        return new User("张三" + new Random().nextInt(10000));
    }

    public User flowBlockUserHandler(BlockException ex) {
        ex.printStackTrace();
        return new User("流控") ;
    }
}


