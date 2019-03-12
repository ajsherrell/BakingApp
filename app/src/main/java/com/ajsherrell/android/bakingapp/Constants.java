package com.ajsherrell.android.bakingapp;

import android.content.Context;
import android.net.ConnectivityManager;

public class Constants {

    public static final String JSON_URL = "https://d17h27t6h515a5.cloudfront.net/";

    // check if there is a network
    public static boolean isNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    // need a clickListener interface
    public static final class ClickListener {
        public interface OnItemClickListener {
            void onItemClick(int position);
        }
    }

}
