package cn.zing.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-03 15:30
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {


    @Value("${user.name}")
    public String username;

    @GetMapping("/show")
    public String name() {

        return username;
    }
}


