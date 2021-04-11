package net.nanquanyuhao.springcloud.alibaba.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.nanquanyuhao.springcloud.alibaba.dao.StorageDao;
import net.nanquanyuhao.springcloud.alibaba.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------>storage-service中扣减库存开始");
        storageDao.decrease(productId, count);
        log.info("------>storage-service中扣减库存结束");
    }
}
