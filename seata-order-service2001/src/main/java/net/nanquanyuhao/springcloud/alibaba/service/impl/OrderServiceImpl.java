package net.nanquanyuhao.springcloud.alibaba.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import net.nanquanyuhao.springcloud.alibaba.dao.OrderDao;
import net.nanquanyuhao.springcloud.alibaba.domain.Order;
import net.nanquanyuhao.springcloud.alibaba.service.AccountService;
import net.nanquanyuhao.springcloud.alibaba.service.OrderService;
import net.nanquanyuhao.springcloud.alibaba.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;

    @Resource
    private OrderDao orderDao;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * 简单说：
     * 下订单->减库存->减余额->改状态
     *
     * @param order
     */
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    @Override
    public void create(Order order) {

        log.info("---->开始新建订单");
        // 1 新建订单
        orderDao.create(order);

        log.info("---->订单微服务开始调用库存，做扣减Count");
        // 2 扣减库存
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("---->订单微服务开始调用库存，做扣减end");

        log.info("---->订单微服务开始调用账户，做扣减Money");
        // 3 扣减账户
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("---->订单微服务开始调用账户，做扣减end");

        // 4 修改订单状态，从0 到1，1表示已经完成
        log.info("---->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("---->修改订单状态结束");

        log.info("---->下订单结束了");
    }
}
