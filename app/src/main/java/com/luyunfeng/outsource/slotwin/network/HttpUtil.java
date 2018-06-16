package com.luyunfeng.outsource.slotwin.network;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Message;

import com.luyunfeng.outsource.slotwin.utils.MessageCode;

import junit.framework.Assert;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class HttpUtil {

    private static String apiPath = API.BASE_PATH_WWW;

    public static boolean ok(int status) {
        return status == MessageCode.RESULT_HTTP_SUCCESS;
    }

    public static String getApiPath() {
        return apiPath;
    }

    public static void setApiPath(String apiPath) {
        HttpUtil.apiPath = apiPath;
    }

    public static Message getResponseMessage(Message message, Object object) {
        if (object == null) {
            message.arg1 = MessageCode.RESULT_HTTP_FAILED;
        } else {
            message.arg1 = MessageCode.RESULT_HTTP_SUCCESS;
            message.obj = object;
        }
        return message;
    }

    public static void packMessageAndSend(Responder responder, Message message, Object object) {
        responder.response(getResponseMessage(message, object));
    }
}
