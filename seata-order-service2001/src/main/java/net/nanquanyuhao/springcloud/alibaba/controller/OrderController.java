package net.nanquanyuhao.springcloud.alibaba.controller;

import net.nanquanyuhao.springcloud.alibaba.domain.CommonResult;
import net.nanquanyuhao.springcloud.alibaba.domain.Order;
import net.nanquanyuhao.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }
}
