package net.nanquanyuhao.springcloud.alibaba.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.nanquanyuhao.springcloud.alibaba.dao.AccountDao;
import net.nanquanyuhao.springcloud.alibaba.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("----->account-service中扣减账户余额开始");
        // 模拟超时异常，全局事务回滚
        accountDao.decrease(userId, money);
        log.info("----->account-service中扣减账户余额结束");
    }
}
