package cn.zing.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 配置类配置负载均衡策略。不能放到@ComponentScan 注解能扫描的地方
 * @author: dcy
 * @create: 2023-06-02 17:49
 */
@Configuration
public class RibbonRandomRuleConfig {


    @Bean
    public IRule iRule() {

        return new RandomRule();
    }
}


