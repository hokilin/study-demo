package cn.hokilin.studydemo.dynamicfield;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linhuankai
 * @date 2020/12/13 18:35
 */
@Getter
@Setter
@ToString
public class BaseDynamicFieldParam {

    @DynamicField
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
