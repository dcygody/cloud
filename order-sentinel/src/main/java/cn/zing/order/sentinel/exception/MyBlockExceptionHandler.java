package cn.zing.order.sentinel.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: dcy
 * @create: 2023-06-05 12:46
 */
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, BlockException e) throws Exception {

        JSONObject r = new JSONObject();
        if (e instanceof FlowException) {
            r.put("msg", "限流");
        } else if (e instanceof DegradeException) {
            r.put("msg", "降级");
        }else if (e instanceof ParamFlowException) {
            r.put("msg", "热点参数限流");
        }else if (e instanceof SystemBlockException) {
            r.put("msg", "触发系统保护规则");
        }else if (e instanceof AuthorityException) {
            r.put("msg", "授权规则不通过");
        }

        response.setStatus(500);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getWriter(), r);



    }
}


