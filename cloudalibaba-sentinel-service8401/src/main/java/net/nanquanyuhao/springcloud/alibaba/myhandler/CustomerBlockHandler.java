package net.nanquanyuhao.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import net.nanquanyuhao.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handlerExceprion(BlockException exception){
        return new CommonResult(4444, "按客户自定义, global handlerException---1");
    }
    public static CommonResult handlerExceprion2(BlockException exception){
        return new CommonResult(4444, "按客户自定义, global handlerException---2");
    }
}
