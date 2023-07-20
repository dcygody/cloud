package order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * name: 服务名
 * path：Controller上的 RequestMapping
 */
@FeignClient(name = "product-service", path = "/product")
public interface ProductFeignService {

    @RequestMapping("/{id}")
    String get(@PathVariable("id") Integer id);
}
