package cn.zing.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-03 11:17
 */
@SpringBootApplication
public class ConfigApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(ConfigApplication.class, args);
//        while (true) {
//            String username = context.getEnvironment().getProperty("user.name");
//            String sex = context.getEnvironment().getProperty("sex");
//            System.out.println("---->" + username);
//            System.out.println("---->" + sex);
//            TimeUnit.SECONDS.sleep(1);
//        }


    }
}


