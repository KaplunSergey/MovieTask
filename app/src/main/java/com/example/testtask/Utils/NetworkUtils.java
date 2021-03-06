package com.example.testtask.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    private Context context;

    public NetworkUtils(Context context) {
        this.context = context;
    }

    public NetworkInfo.State getNetworkState() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return NetworkInfo.State.UNKNOWN;
        }

        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info.getState();
    }
}
