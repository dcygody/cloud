package cn.zing.sentinel.controller;

import cn.zing.sentinel.pojo.User;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-03 16:05
 */
@RestController
@RequestMapping("/sentinel")
@Slf4j
public class HelloController {



    private static final String RESOURCE_NAME = "hello";
    private static final String USER_RESOURCE_NAME = "user";
    private static final String DEGRADE_RESOURCE_NAME = "degrade";


    @GetMapping("/hello")
    public String hello() {
        Entry entry = null;
        try {
            // 对资源进行限制
            entry = SphU.entry(RESOURCE_NAME);
            // 被保护的业务逻辑
            String str = "hello word";
            log.info("======" + str + "=======");
            return str;
        } catch (BlockException e) {
            e.printStackTrace();
            log.info("block");
            return "被限流了";
        } catch (Exception ex) {
            // 若需要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(ex, entry);
        }finally {
            if (null != entry) {
                entry.exit();
            }
        }

        return null;
    }

    @RequestMapping("/user")
    @SentinelResource(value = USER_RESOURCE_NAME, blockHandler = "blockHandlerForGetUser")
    public User getUser(String id) {

        return new User("zhangsan");
    }

    public User blockHandlerForGetUser(String id, BlockException ex) {
        ex.printStackTrace();

        return new User("流控");
    }

    @GetMapping("/degrade")
    @SentinelResource(value = DEGRADE_RESOURCE_NAME, entryType = EntryType.IN,
    blockHandler = "blockHandlerForFb")
    public User degrade(String id) {

        throw new RuntimeException("异常");
    }

    public User blockHandlerForFb(String id, BlockException ex) {

        return new User("熔断");
    }


    @PostConstruct
    private static void initFlowRules() {

        List<FlowRule> rules = new ArrayList<>();

        FlowRule rule = new FlowRule();

        rule.setResource(RESOURCE_NAME);

        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);

        rule.setCount(1);

        rules.add(rule);

        FlowRule rule2 = new FlowRule();

        rule2.setResource(USER_RESOURCE_NAME);

        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);

        rule2.setCount(1);

        rules.add(rule2);

        FlowRuleManager.loadRules(rules);

    }

    /**
     * 降级规则
     */
    @PostConstruct
    private static void initDegradeRule() {

        List<DegradeRule> degradeRules = new ArrayList<>();

        DegradeRule degradeRule = new DegradeRule();

        degradeRule.setResource(DEGRADE_RESOURCE_NAME);
        //配置规则： 异常数
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        // 触发熔断异常数: 2
        degradeRule.setCount(2);
        // 触发异常最小请求数
        degradeRule.setMinRequestAmount(2);
        degradeRule.setStatIntervalMs(60 * 1000);
        // 一分钟内 执行了两次,出现2次异常,就会触发熔断
        // 熔断持续时长
        degradeRule.setTimeWindow(10);


        degradeRules.add(degradeRule);
        DegradeRuleManager.loadRules(degradeRules);


    }
}


