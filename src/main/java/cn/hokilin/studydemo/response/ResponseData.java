package cn.hokilin.studydemo.response;

import lombok.Data;

@Data
public class ResponseData {
    private int code;
    private String msg;
    private Object data;

    public ResponseData() {
    	
    }
    
    public ResponseData(Object data) {
        this.data = data;
    }

    public ResponseData(ResponseCode responseCode, Object data) {
        this.code = responseCode.getCode();
        this.data = data;
    }

    public ResponseData(ResponseCode responseCode, String msg) {
        this.code = responseCode.getCode();
        this.msg = msg;
    }

    public ResponseData(ResponseCode responseCode, String msg, Object data) {
        this.code = responseCode.getCode();
        this.msg = msg;
        this.data = data;
    }

    public static ResponseData success(String msg, Object data) {
        return new ResponseData(ResponseCode.SUCCESS, msg, data);
    }

    public static ResponseData success(Object data) {
        return new ResponseData(ResponseCode.SUCCESS, data);
    }

    public static ResponseData success(String  msg) {
        return new ResponseData(ResponseCode.SUCCESS, msg);
    }

    public static ResponseData fail(String msg) {
        return new ResponseData(ResponseCode.FAIL, msg);
    }

    public static ResponseData fail(ResponseCode responseCode, String msg) {
        return new ResponseData(responseCode, msg);
    }

    public static ResponseData notFound() {
        return new ResponseData(ResponseCode.NOT_FOUND, "找不到资源");
    }
}
