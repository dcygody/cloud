package order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * 全局配置 Feign日志级别 @Configuration
 * 局部配置 不需要加注解; 或者配置文件指定
 * @author: dcy
 * @create: 2023-06-03 10:01
 */
//@Configuration //
public class FeignConfig {


    @Bean
    public Logger.Level feignLoggerLevel() {

        return Logger.Level.FULL;
    }
}


