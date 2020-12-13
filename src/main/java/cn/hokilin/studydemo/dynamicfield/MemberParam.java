package cn.hokilin.studydemo.dynamicfield;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author linhuankai
 * @date 2020/12/11 14:32
 */
@DynamicField
@Getter
@Setter
@ToString(callSuper = true)
public class MemberParam extends BaseDynamicFieldParam {
    @NotBlank(message = "name不能为空")
    private String name;
    private Integer age;
}
