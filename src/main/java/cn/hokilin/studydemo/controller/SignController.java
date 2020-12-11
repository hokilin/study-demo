package cn.hokilin.studydemo.controller;

import cn.hokilin.studydemo.response.ResponseData;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linhuankai
 * @date 2020/11/17 14:26
 */
@RestController
@RequestMapping("/sign")
public class SignController {

    /**
     * http://localhost:8888/sign/getSignUrl?signKey=5ea397ae-0833-4402-b9f7-601fb3a29c34&signSecret=YTc3MTUyMTktM
     * http://sign.frp.1dreams.com/sign/getSignUrl?signKey=5ea397ae-0833-4402-b9f7-601fb3a29c34&signSecret=YTc3MTUyMTktM
     * @param signKey
     * @param signSecret
     * @return
     */
    @GetMapping("/getSignUrl")
    public ResponseData getSignUrl(@RequestParam String signKey, @RequestParam String signSecret) {
        long timestamp = System.currentTimeMillis();
        String signMd5 = DigestUtils.md5DigestAsHex((signSecret + signKey + timestamp).getBytes());
        return ResponseData.success("sign=" + signMd5 + "&signKey=" + signKey + "&timestamp=" + timestamp);
    }
}
