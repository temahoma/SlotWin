package com.luyunfeng.outsource.slotwin.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import com.luyunfeng.outsource.slotwin.MyApplication;
import com.luyunfeng.outsource.slotwin.utils.DeviceUtils;


/**
 * Created by luyunfeng on 15/12/8.
 */
public class NetWorkUtil {

    public static int getNetworkState() {
        try {
            ConnectivityManager connManager = DeviceUtils.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Network[] networks = connManager.getAllNetworks();

                boolean isMobileAvailable = false;

                for (Network network : networks) {
                    NetworkInfo networkInfo = connManager.getNetworkInfo(network);
                    if (networkInfo.isConnectedOrConnecting()) {
                        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            return ConnectivityManager.TYPE_WIFI;
                        } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            isMobileAvailable = true;
                        }
                    }
                }

                if (isMobileAvailable) {
                    return ConnectivityManager.TYPE_MOBILE;
                }

            } else {
                NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (networkInfo.isConnectedOrConnecting()) {
                    return ConnectivityManager.TYPE_WIFI;
                }

                networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (networkInfo.isConnectedOrConnecting()) {
                    return ConnectivityManager.TYPE_MOBILE;
                }
            }
        } catch (Exception e) {

        }
        return -1;
    }

    public static boolean isNetworkAvailable(){
        return getNetworkState() >= 0;
    }
}
