package cn.hokilin.studydemo.datahandle;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author linhuankai
 * @date 2021/12/17 21:18
 */
@Data
public class CntValueEntity {
    @ExcelProperty(value = "app")
    private String app;
    @ExcelProperty(value = "env")
    private String env;
    @ExcelProperty(value = "key")
    private String key;
    @ExcelProperty(value = "value")
    private String value;
    @ExcelProperty(value = "desc")
    private String desc;
}
