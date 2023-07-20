package order.feign;

import order.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * name: 服务名
 * path：Controller上的 RequestMapping
 */
@FeignClient(name = "stock-service", path = "/stock"/*, configuration = FeignConfig.class*/)
public interface StockFeignService {

    @RequestMapping("/reduct")
    String add();
}
