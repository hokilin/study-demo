package cn.hokilin.studydemo.controller;

import cn.hokilin.studydemo.dynamicfield.DynamicField;
import cn.hokilin.studydemo.dynamicfield.MemberDTO;
import cn.hokilin.studydemo.dynamicfield.MemberParam;
import cn.hokilin.studydemo.response.ResponseData;
import cn.hokilin.studydemo.utils.DynamicFieldUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author linhuankai
 * @date 2020/12/11 14:35
 */
@RestController
@RequestMapping("/dyna")
@Slf4j
public class DynamicFieldController {

    private static HashMap<String, String> fieldRelationMap;

    static {
        fieldRelationMap = new HashMap<>(3);
        fieldRelationMap.put("extend1", "gender");
        fieldRelationMap.put("extend2", "birthday");
        fieldRelationMap.put("extend3", "phone");
    }


    @PostMapping("/addMember")
//    @DynamicField
    public ResponseData addMember(@RequestBody MemberParam param) {
        log.info("传入参数：" + param);
        DynamicFieldUtils.handleDynaFieldParam(param, fieldRelationMap);
        log.info("工具类处理后的参数：" + param);
        MemberDTO dto = new MemberDTO();
        BeanUtils.copyProperties(param, dto);
        return ResponseData.success(dto);
    }
}
