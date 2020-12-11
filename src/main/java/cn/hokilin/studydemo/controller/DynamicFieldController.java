package cn.hokilin.studydemo.controller;

import cn.hokilin.studydemo.dynamicfield.DynamicField;
import cn.hokilin.studydemo.dynamicfield.MemberParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linhuankai
 * @date 2020/12/11 14:35
 */
@RestController
@RequestMapping("/dyna")
public class DynamicFieldController {
    @PostMapping("/addMember")
    @DynamicField
    public void addMember(@RequestBody MemberParam param) {
        System.out.println("接收到的请求体==============：" + param);
    }
}
