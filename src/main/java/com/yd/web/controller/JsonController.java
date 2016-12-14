package com.yd.web.controller;

import com.google.common.collect.ImmutableMap;
import com.yd.web.service.DemoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * Created by wubo on 2016/12/14.
 */
@RestController
public class JsonController {

    @Autowired
    DemoService demoService;

    @RequestMapping(value = "json", method = RequestMethod.GET)
    public Object returnJson() throws Exception {
        System.out.println("==================json==============");

        Future<String> task1 = demoService.doTaskOne();
        Future<String> task2 = demoService.doTaskTwo();
        while(true) {
            if(task1.isDone() && task2.isDone()) {
                // 三个任务都调用完成，退出循环等待
                System.out.println("===============   获取任务结果  =================");
                System.out.println(task1.get() + "          " + task2.get());
                System.out.println("===============   获取任务结果  =================");

                break;
            }
            Thread.sleep(1000);
        }


        return ImmutableMap.of("key1", "value1", "key2", "value2");
    }

/*    @ApiOperation(value = "获取用户信息", notes = "根据传递的用户Id获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户Id", required = true, dataType = "String")
    })*/
    @RequestMapping(value = "getNameById", method = RequestMethod.GET)
    public Object getNameById(String id) {
        return demoService.getNameById(id);
    }


}
