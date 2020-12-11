package cn.hokilin.studydemo.response;

public enum ResponseCode {
    // 通用
    SUCCESS(0, "成功"),
    FAIL(-1, "失败"),
    NOT_FOUND(404, "找到不资源"),
    INTERNAL_SERVER_ERROR(500, "服务错误"),
    REQUEST_ERROR(400, "非法入参"),
    SERVER_DATA_ERROR(1000, "服务数据异常"),

    // 数据权限
    CROSS_TENANT_ERROR(1001, "跨租户数据非法访问"),

    // openapi
    ACCESSTOKEN_EXPIRED(2001, "accesstoken已过期"),
    ACCESSTOKEN_IS_EMPTY(2002, "accesstoken不能为空"),
    REQUEST_NOT_AUTHORIZE(2003, "请求未授权"),
    REQUEST_IP_NOT_EXIST_WHITELIST(2004, "请求IP不在白名单列表:"),
    OPENAPI_OVER_TIME(2005, "Openapi请求业务服务模块超时"),
    OPENAPI_AUTHORIZE_OVER_TIME(2006, "openapi请求授权校验超时"),
    API_AUTHORIZE_OVER_TIME(2007, "内部api请求授权校验超时"),

    // 用户
    SPRING_SECURITY_ERROR(3001, "Spring security 异常"),
    VERIFY_USERNAME_PASSWORD_NOT_PASS(3002, "用户名密码校验失败"),

    // 会员
    MEMBER_NOT_FOUND(4001, "找不到会员信息"),
    MEMBER_CHANNEL_ACCOUNT_NOT_FOUND(4002, "找不到当前渠道品牌已绑定的会员账号"),

    // 积分
    REPEATED_CHANGE_VALUE(5001, "当前流水号变更积分已处理"),
    ACCOUNT_BALANCE_NOT_ENOUGH(5002, "账户余额不足"),

    EVENT_NOT_FOUND(5003, "根据事件code查不到当前租户事件"),
    EVENT_RULES_NOT_FOUND(5004, "未找到事件对应的启用规则"),
    EVENT_RULE_AWARD_NOT_FOUND(5005, "当前事件配置的规则没有奖励方案"),
    LIMIT_VALUE(5006, "额度限制"),
    LIMIT_TIMES(5007, "次数限制"),

    INTEGRAL_WAY_ERROR(5008, "当前租户的积分获取方式与当前接口不符"),

    // 营销
    // 发送短信验证码
    SMS_VERIFY_SUCCESS(6001, "发送成功"),
    SMS_VERIFY_FAIL(6002, "发送失败"),
    SMS_VERIFY_ONE_MIN_LIMIT(6003, "一分钟不允许重复发送验证码"),
    SMS_VERIFY_MAX_LIMIT(6004, "同一个手机号码发送验证码的次数过于频繁"),
    SMS_VERIFY_NO_MATCH(6005, "验证码不正确或已失效"),


    // 微信开放接口相关
    WX_ILLEGAL_OAUTH_CODE(40029, "无效的oauth_code"),
    WX_USED_OAUTH_CODE(40163, "oauth_code已使用"),
    WX_REQUEST_ERROR(40000, "微信请求异常"),
    WX_OFFIACCOUNT_NOT_AUTH(40001, "找不到公众号或公众号未授权！"),
    ;

    private final int code;
    private final String description;

    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
