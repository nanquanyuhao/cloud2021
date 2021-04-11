package net.nanquanyuhao.springcloud.alibaba.dao;

import net.nanquanyuhao.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    /**
     * 新建订单
     */
    void create(Order order);

    /**
     * 修改订单状态，从0改为1
     *
     * @param userId
     * @param status
     */
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
