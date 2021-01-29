package cn.hokilin.studydemo.mob;

import cn.hokilin.studydemo.response.ResponseData;
import cn.hokilin.studydemo.utils.JacksonUtils;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author linhuankai
 * @date 2021/1/19 17:17
 */
@RestController
@RequestMapping("/mob")
@RequiredArgsConstructor
@Slf4j
public class MobTest {

    private static final String URL = "https://dmp.appgo.cn/querydataV6";
    /**
     * 加密方式：{"iv_parameter":"149NayJVJ4W2UFG9","cipher_algorithm":"AES/CBC/PKCS5Padding","key_algorithm":"AES","key":"KouzGlZbKeXD0ZNe"}
     */
    private static final String AES_KEY = "KouzGlZbKeXD0ZNe";
    private static final String AES_IV = "149NayJVJ4W2UFG9";

    private static final AES AES = new AES(Mode.CBC, Padding.PKCS5Padding, AES_KEY.getBytes(), AES_IV.getBytes());

    private final RestTemplate restTemplate;

    private static String dataRange = "1351445730962714626";
    private static String userId = "shuomiao";
    private static String idType = "010220";

    @PostMapping("/queryData")
    public ResponseData queryData(@RequestParam String phone) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String phoneMd5 = DigestUtils.md5DigestAsHex(phone.getBytes());
        ReqParam param = ReqParam.builder()
                .busiSerialNo(timestamp)
                .dataRange(dataRange)
                .idType(idType)
                .userId(userId)
                .timeStamp(timestamp)
                .exid(AES.encryptBase64(phoneMd5))
                .build();
        ResponseResult responseResult = restTemplate.postForObject(URL, param, ResponseResult.class);
        log.info("responseResult:" + responseResult);
        String result = "";
        if (responseResult != null && StringUtils.isNotBlank(responseResult.getDataRange())) {
            result = AES.decryptStr(responseResult.getDataRange());
        }
        log.info("result:" + result);
        return ResponseData.success(result);
    }

    public static void main(String[] args) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String phoneMd5 = DigestUtils.md5DigestAsHex("15222892752".getBytes());
        ReqParam param = ReqParam.builder()
                .busiSerialNo(timestamp)
                .dataRange(dataRange)
                .idType(idType)
                .userId(userId)
                .timeStamp(timestamp)
                .exid(AES.encryptBase64(phoneMd5))
                .build();
        String curl = "curl -H \"Accept: application/json\" -H \"Content-type: application/json\" -X POST -d ";
        curl = curl + '\'' + JacksonUtils.obj2String(param) + "\'" + " " + URL;
        System.out.println(curl);

        System.out.println(AES.decryptStr("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"));
    }
}
