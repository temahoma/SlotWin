package com.luyunfeng.outsource.slotwin.network;

import org.json.JSONObject;

/**
 * Created by luyunfeng on 17/11/13.
 */

public class StandardResponse {
    /**
     * 成功或失败状态
     */
    public boolean status;

    /**
     * 错误码
     */
    public int code;

    /**
     * 错误信息
     */
    public String message;

    /**
     * 返回数据
     */
    public JSONObject data;
}
