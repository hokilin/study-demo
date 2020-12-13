package cn.hokilin.studydemo.dynamicfield;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author linhuankai
 * @date 2020/12/11 14:32
 */
@Data
@DynamicField
public class MemberParam extends BaseDynamicFieldParam {
    @NotBlank(message = "name不能为空")
    private String name;
    private Integer age;

    private String extend1;
    private String extend2;
    private String extend3;
}
