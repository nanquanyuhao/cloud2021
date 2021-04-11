package net.nanquanyuhao.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"net.nanquanyuhao.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
