package com.wizzstudio.substitute.constants;

public class Constants {
    public static final int SYSTEM_BUSY = -1;
    public static final int REQUEST_SUCCEED = 0;
    public static final int INVALID_TOKEN = 40001;
    public static final int INVALID_CERTIFICATE_TYPE = 40002;
    public static final int INVALID_USER_ID = 40003;
    public static final int INVALID_MSG_TYPE = 40008;

    public static final String QUERY_SUCCESSFULLY = "请求成功";
    public static final String QUERY_FAILED = "请求失败";
    public static final String TOKEN = "token";
    public static final String INVALID_MESSAGE = "信息有误";

    public static final Integer TOKEN_EXPIRED = 7200;

    // 864000 = 60*60*24*10 （10天）
    public static final Integer REMEMEMBER_ME = 864000;
}
