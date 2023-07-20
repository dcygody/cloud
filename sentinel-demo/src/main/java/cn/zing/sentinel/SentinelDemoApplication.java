package cn.zing.sentinel;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-03 16:10
 */
@SpringBootApplication
public class SentinelDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelDemoApplication.class, args);
    }

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {

        return new SentinelResourceAspect();
    }
}


