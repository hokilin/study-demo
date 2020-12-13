package cn.hokilin.studydemo.controller;

import cn.hokilin.studydemo.dynamicfield.DynamicField;
import cn.hokilin.studydemo.dynamicfield.MemberDTO;
import cn.hokilin.studydemo.dynamicfield.MemberParam;
import cn.hokilin.studydemo.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
@Slf4j
public class DynamicFieldController {
    @PostMapping("/addMember")
    @DynamicField
    public ResponseData addMember(@RequestBody MemberParam param) {
        log.info("接收到的请求体：" + param);

        MemberDTO dto = new MemberDTO();
        BeanUtils.copyProperties(param, dto);
        return ResponseData.success(dto);
    }
}
