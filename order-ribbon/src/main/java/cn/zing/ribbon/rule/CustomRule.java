package cn.zing.ribbon.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @description: 自定义负载均衡策略
 * @author: dcy
 * @create: 2023-06-02 18:07
 */
public class CustomRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {

        ILoadBalancer loadBalancer = this.getLoadBalancer();

        List<Server> reachableServers = loadBalancer.getReachableServers();

        int anInt = ThreadLocalRandom.current().nextInt(reachableServers.size());
        Server server = reachableServers.get(anInt);
        if (server.isAlive()) {
            return server;
        }
        return null;
    }
}


