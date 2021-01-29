package cn.hokilin.studydemo.mob;

import lombok.Builder;
import lombok.Data;

/**
 * @author linhuankai
 * @date 2021/1/19 17:42
 */
@Data
@Builder
public class ReqParam {
    private String busiSerialNo;
    private String dataRange;
    private String exid;
    private String idType;
    private String timeStamp;
    private String userId;
}
