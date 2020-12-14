package cn.hokilin.studydemo.dynamicfield;

import lombok.Data;

/**
 * @author linhuankai
 * @date 2020/12/11 18:19
 */
@Data
@DynamicField
public class MemberDTO {
    private String name;
    private Integer age;

    @DynamicField
    private String extend1;
    @DynamicField
    private String extend2;
    @DynamicField
    private String extend3;
}
