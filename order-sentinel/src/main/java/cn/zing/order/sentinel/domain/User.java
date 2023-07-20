package cn.zing.order.sentinel.domain;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-05 13:13
 */

public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


