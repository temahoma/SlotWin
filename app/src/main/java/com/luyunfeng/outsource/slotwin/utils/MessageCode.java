package com.luyunfeng.outsource.slotwin.utils;

/**
 * 更改时注意有无与服务器相关的响应码
 */
public interface MessageCode {
    int MESSAGE_HTML = 100;
    int RESULT_HTTP_SUCCESS = 10000;
    int RESULT_HTTP_FAILED = 10001;
    int RESULT_HTTP_TIMEOUT = 10002;
    int RESULT_HTTP_SIGN_ERROR = 10003;
    int RESULT_DATA_FORMAT_ERROR = 10004;
    int MESSAGE_NO_NETWORK = 10005;
}
