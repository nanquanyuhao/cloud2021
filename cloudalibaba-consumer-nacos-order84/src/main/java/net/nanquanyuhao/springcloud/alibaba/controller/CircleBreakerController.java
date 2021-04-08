package net.nanquanyuhao.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import net.nanquanyuhao.springcloud.entities.CommonResult;
import net.nanquanyuhao.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    // @SentinelResource(value = "fallback") // 没有配置
    // @SentinelResource(value = "fallback", fallback = "handlerFallback") // fallback 只负责业务异常（相当于服务端的服务降级）
    @SentinelResource(value = "fallback", blockHandler = "blockHandler") // blockHandler 只负责控制台违规配置
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {

        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id,
                CommonResult.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException，非法参数异常……");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException，该ID没有对应记录，空指针异常");
        }

        return result;
    }

    /**
     * 本例是 fallback
     *
     * @param id
     * @param e
     * @return
     */
    /*public CommonResult handlerFallback(Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(444, "兜底异常 handlerFallback，exception内容 " + e.getMessage(), payment);
    }*/

    /**
     * 本例是 blockException
     *
     * @param id
     * @param blockException
     * @return
     */
    public CommonResult blockHandler(Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(445, "blockHandler-sentinel限流，无此流水， blockException " +
                blockException.getMessage(), payment);
    }
}
