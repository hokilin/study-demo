package cn.hokilin.studydemo.mob;

import lombok.Data;

/**
 * @author linhuankai
 * @date 2021/1/19 17:45
 */
@Data
public class ResponseResult {
    private String timeStamp;
    private String dataRange;
    private String busiSerialNo;
    private String resCode;
    private String resMsg;
}
