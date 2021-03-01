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

        System.out.println(AES.decryptStr("tPN9CyzxEtRYXeBt5aW2IGALW9iRIv88FyDZhf1TK8GnzHEmpZyifd16S1+mXcmyrYC4LVRZKFwlVEwD1S40uCfL6I/ez3khJrfr5t5SL277898712uisdyE8DPPc1OXSicF8iYWUzENPyjNO7pVhB94eAw5yPeIcWCmajlnO/DY174g3hyDNqjcep1Mryd7uP7FsVPxxY2AUyY7jLiA8eUsGo8EViE0POetKtEeiTUBxbS4vqvTl/w1Ol9btWqwXPEdsvbKNRx8Fm1yDdop4P9xg33vTAIVykvIH5qls/fFXsFp0UvF4QeCnJq5cAoq3ldupUUFWldcZ/aM5X4UlxPednHEuw2OLUW5KhAC02r870eNsx8inpt22h4FwIJjXZr8eauS6q0m3P4slOB1B8A70R86d4I/HMN5Qif7Wgjkr7HrYZTYtPQpmx1IRvF5k2O3iZzt8T67hrEv54HxxQkHlw01tlmmlvxm0/dcpYQbHqMVDn/W3VUuBT79XhELgAdsWWha4D4mDmKCghRSRazUinOX9IbXHHEC9GiZsiVnkqBEnXRPUeJEeYP8LayTJZ5IFKRQ4Asv54E2/9+u48T9MsNklE8HM77tqYg0CutIVB5Hlo+ix18yyi8bndZnijBJaZszB5S1Mod8wl675c6A9XZVcoRNkYbwDfk68JDw5+I81vG6LqVMggFdnglLShFE1rJpGXAiCHewNmjTfuEilYaUpLtk+tKz4kqdnjogURr08eCgohU5KBbb4Le4izayqkZVxoNMum/SeUTH6g7NNKVKmzsFotRhy/eKoUqDDfUxS09TVf0KVYYXq5WZGTlLsqz/Ovg1RRAYgYm1Rti/1cH6n0cC+4tGYY+PiI3hIB7zV+Yf+Y+IlKlxweH30jliwchTgTByRDAcJxc1H18lMhQB8+NLGrMsDgFcFPZCTupeoEA3q15/lREr1k9XdW4p9vBsZsEunKdU8r4ogYnvKJMhs9n/qXxbU8Ukj/pja42kYxQzEn8V/MukTIE37tozTpggxYsUSkDOrNxgemro6jzYzVkUMpgoa55kYFlk0SY5EevlKGrNem/nOYi1OMkXKh+5di8obbcj30O7cwa51KEpwJ6IKgrxVdQzOKIuRkQGnCPbwf7wbRqKng1aPV0ZF96YZfwOmMQ/hBGDRFaFgQd6wk65EovnpRaskciEiPq7huvCIWbKXaPFTB+/zkjR4tC2IdVXm74vnVpK8dwYqX+SIJ2/uWwigaOuBWM9hJFLepe/DZin7Uk1TGdC4WjIw26+s5x2vpNnjn08jd7XaQUy1HmYx9LZoWnUnYSDdNd5i/QcQJAhEBlwufjA+ldNQ3qAdgfPKFBGyPHD6Gik9zCjO/WWJYFLpxegDbARniZ+so+7QIDw5pa/A4zx/+u4zHsIQCAoBKb3RCNmx+07PSk6bamvcuHk3FDG99HnyWJdcAbcgw+jNQezEa9Znc2wGvDTeJ1lFLuYH53RQqggr/MkxvqDOIkqQeAGDqvefKhIKjGLRWmu7A5tKXqlgG3Qfw2RLl88DfC1WATVv2BvitKzZoArBkZ8SR4XyS/Mrf4+piZfknDXqmV5MUcNQow5BP3svwczoZURVUNoZV2cRKTySna9JMWaUNiU4Dtr9ljr0qWArGgUw3L84TG0oLsht9iOt6DEN6ri8+gb8SiWYmiXEA6twuz6AbhPiLr7f3BZ2h5piyah82C/421Muti2CzfefUdxdojc1hH1yXgICCDf62ntsrdKs9AO17PscB3qRK91+xJ/SAX/JPaL1NA8ffMk4zHTBjHn+Q4iEDwjweChThdwk+Dyd+rPxwFcDsuKJp8UcZlzLmDiz8bY2TsBMCj4sSCW+ycI4aNNTBquysfyAwtbjmObaFkdEiU4EDFbyrtWYKRxZA7LohrUVZ4WNsOoNfF7xc0/EQ1JV1t+9l1ra7ow6EUMYHbzuT+HRAto6PA2dZbZVpmLcDr8tuyphvvxbbdjqd5eyEqOZ677S1fG7+DRbiSGsARJaUZycg2oklRAalbGS/Mi2mZQ/lMpmjjjNamKuYESuA88RrbA/qLJYqMmrUB/9LCMvgLm8zuqimhmpgbVrDuVjafsp71kSBF/pXajFLGUJtMDdvSGg2a4p9tiKjhtdq+1/zwnF63TTBWbWO0+6Al0wKa/ilxEQR34SvP9XqMlfp3Ekya+IKLLTBc9rGw5uvV/0OrIkfIcuijkpjre/bvnQh5bKhSohM2JlPZb8ZuitcKJ//sH61Pi2Ke3yUrbhU8WCLbufijsv+kfOsn3hoAe092XOC4NOmesHBVd5BDdjz2kMey77memc7DOygaCfJgoDHecWJqJ4k/O4fhnX/qOTjYDJ8nIRH2Y7ArGchEx6x46Fo+05kIXU1eo03gN3PZO04rJn6BuS9EOrK7xKP31AeBy/3DOa1SjL8NRtIuQ+/u8qDspof07jH9fAsNrPavhvIaDrS9WyYqSTcnn8LrrZOVMJdZ6xSgOMKSrOVnfw7zeai/w41JXEHMau3e4UbOTA+lqdAQ6d9QtMCRu9sev3eAv57/qNzWEK1P/8YepZsr7XntGYJytzyp87TQs3kBgv8rf5pjnBVc5gjqCWzyV8dxRIwYERs1EZHB/fYqgWEBD0K0GIZIEH7SNEmAm2Hu4mQSfEFgC6PvF6oDjfdPaNo74gZqt634YMOqbkfahwHT6lN9PljvxFVzlFsHppYm4hPyi0s9L1nh+6av+BkNx6Rb+v4bFVr0wis9XXl+RlOZss8y5G5VntcTgBa/sraIYxE0Y+g7aC+uhyHwYZR6T+LkrdmxhinuoTiI5CPaFVmJ/T1l+Vg9POGZSEoSsrFIak8AswUynbqfnOl27vYpCa2+WodZMAbaXrgG9/48C/4NNyKHk6ST7xqvbGngzC+94JgJHKD9KsWHAJw+ykhcXVKiRMzLUdv944bkEmaIStx5ZyIaF/Z4vVS8hySxG681SzF4S55m2gX62iSyaykm9DEBuEhOGl8IaVTWnAj26piKs8AYBjrkzDvDDsrfyGAYfYSKWirIlRvpa8bCVPcprqCCKth/BipCLt9qCNKLkMCkjry8bNxwBSJ7XSgYbzkQBub4fQhJWA0L+9HKdkVxAe6bWzTHLcVEIHHBKL1lQ0820A6F1yU4TS60cHDHnf82vaFXmqcC6iqNpq72TcObscvhjK3+H0Ur6y85J2Sck3E3OoXiOm9dEeSvr2sX/ocfstcU+CPTmHj+wTpMv0IdpeWyKORRNb0RkyuqKxCR5Fatzw1NY4FsYW/ojsHlC2X/PqpG0xpluzBUQTHzrdDJdPB5a88s0b0tLke6ELWNuF7/QVNSiZ3zOWhNrI0oVPxcRahOpT3zly2nRQMsYKxSKf67OX4Iv5wkjY0JdnamItq4K2mn6io932UQtfBT0DyS9h23+K46Mr8eRA7vcAL8z4WwL2knHqakzWLvrTo+h+H6k+9BdxHDBMMSpQyTieFAVWnBm3UMLWphSAtZAkL4AjkafXyHkZFUfsTHtRlVF30i+lllpdQScRFXlwltRvZiaoWOG2Uym5B/esUbH0GbGRSTlmEdkX5uxqnJk0qkCAnoyw8o0ijk7r8FTpP7/ud+JYTk/p0SLVxfVvQfGSb1nTuqsWC2MHgBo2unUYh5h12XaZD9lpJlmIpW82Id0W3gRTddCp+M0dFaruqadksmy4F1+jP4JNfZMA/2iVU5/1mFuq8TQrPEG/Qwm/UZugQtrG6eHI8gtqfMbtUT8cLngoNoqMG+E73kn7IFAu55saXH2exv0iyoIPTnxuRLq2vTW691ijBCAx+tXen4kkrk7cjzUm37f2MZtd2o2TuLznBi/rxjQ5Gr+sgXNiUUnQD3NrD11lQBxqlJcxFlO4r9Iya94iWAcfNVDa3ZBML4hPHMjfo9IeBMkUYZDsIhtq1NYBllZ/UtJHjfwtdwiU4IHfM2zsgLv4pvHgspVYJmebvfPPvZOZvEecCeJtYICFYvTdFDQq5yRyX/sEyv44GmCdqrz14ZYMZgIQ2f8oqsAcUtXVWoxGZHG81Q6ke1gmRQ0+KZiA8VV1TFQYFzOyo13iz3jRa/abrXvaWpk9udMHs9iFQWh35w9pqT70x2DGloVPT0Tl5sq7LAIFWxk/gqViobhg+GBHCrSUlZ4e/taQJH/B/mKuO/lm1ZQOoPJpHuOpHFzs+H2oPZgx99iFo2iOEKJJhBG5u5Vp2L8OpMbIkOva71llh+WaDlf0W0BR9GZlOQa6qh1YnygYNUNUTuYxhXRsLIHYu3F0YCdHXAGLm/o0UK9xACSQwCqR0Oqa6Xj5DoFPlKoQOrr67se81G+/Ce4Sy95dmseZnCOfiSEUhFpOFXbgpYw2RXMQH3MPtTkXUSiReFVg1yepKujfqOl1F9+hXrGjxOBEXIQ8lLlTXn5b1aV6x3yGYjOT0pTXeP7Oopir3Rm5CSnjooKvfoLmK+QD69IVmkJ0DBSrQA2UnfxniepQ1d19EWmpMwfoaZOXcPDW7OiEeME/CNpr+5bVU3Jr1ObBXKp51JHjnIDOvCMRynUpI7vaJ/LKmn+s4hpRW1m/KF0s8LVsv9S2O4+F03WGpAqTukQPZAY/97HCKic4JFHb2Zc6Y4A3rwzdOCyb6q/ZlHZfdi3YdetdzKXFEcP5hXKO5zMc9u34mMJW4qEZFtPK4fptkMxvGK0SDkltnlIRklilAf1yKdw91xOkwzZ7tJdTkTYUMnDxEfZdxfHuw=="));
    }
}
