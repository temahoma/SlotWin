package com.luyunfeng.outsource.slotwin.network;

/**
 * Created by luyunfeng on 17/9/13.
 */

public interface ErrorCode {
    int USER_EMAIL_EXIST = 200001;
    int USER_VERIFY_FAILED = 200002;
    int USER_SMS_LIMIT = 200003;
    int USER_SEND_SMS_FAILED = 200004;
    int USER_GEN_VER_CODE_FAILED = 200005;
    int USER_CODE_VER_FAILED = 200006;
    int USER_PHONE_EXIST = 200007;
    int USER_CONNECT_PHONE_FAILED = 200008;
}
