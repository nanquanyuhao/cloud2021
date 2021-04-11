package net.nanquanyuhao.springcloud.alibaba.service;

import java.math.BigDecimal;

public interface AccountService {

    /**
     * 扣减账户金额
     *
     * @param userId
     * @param money
     */
    void decrease(Long userId, BigDecimal money);
}
