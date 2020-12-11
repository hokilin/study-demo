package cn.hokilin.studydemo.dynamicfield;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linhuankai
 * @date 2020/12/11 14:32
 */
@Data
@DynamicField
public class MemberParam {
    @NotBlank(message = "name不能为空")
    private String name;
    private Integer age;

    private String extend1;
    private String extend2;
    private String extend3;

    private Map<String, Object> extend = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getExtend() {
        return extend;
    }

    @JsonAnySetter
    public void setExtend(String key, Object value) {
        this.extend.put(key, value);
    }
}
