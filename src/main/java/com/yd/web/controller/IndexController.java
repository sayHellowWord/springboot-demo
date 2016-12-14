package com.yd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wubo on 2016/12/14.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(ModelMap map){
        System.out.println("====================index========================");
        // 加入一个属性，用来在模板中读取
        map.addAttribute("demo", "这是模板测试");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }


}
