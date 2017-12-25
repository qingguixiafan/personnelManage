package common;

/**
 * Created by Administrator on 2017/12/14.
 */
public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"DATA_BASE_ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;


    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc; //描述
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
