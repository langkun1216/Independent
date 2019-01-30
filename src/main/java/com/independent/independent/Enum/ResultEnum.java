package com.independent.independent.Enum;

public enum ResultEnum {
    SUCCESS("200", "成功"),
    EXPORT_SUCCESS("201", "导出成功，文件位于"),
    UNKONW_ERROR("10001", "系统内部异常"),
    FILE_FORMAT_ERROR("10002", "所选文件格式不正确!"),
    EXPORT_ERROR("10003", "导出失败，请重试!");

    private String code;

    private String message;

    ResultEnum( String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return  this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
