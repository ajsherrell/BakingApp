/*
 * Copyright 2019 AJ Sherrell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ajsherrell.android.bakingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import com.ajsherrell.android.bakingapp.Models.Bakery;

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


    // use shared preferences for widget
    public static class WidgetPref {

        public static final String PREFERENCE_NAME = "preference_name";

        public static void saveBake(Context context, Bakery bakery) {
            SharedPreferences.Editor widgetPrefs = context.getSharedPreferences(PREFERENCE_NAME,
                    Context.MODE_PRIVATE).edit();
            widgetPrefs.putString(context.getString(R.string.widget_key), String.valueOf(bakery.getIngredients())); // was toString()
            widgetPrefs.apply();
        }

        public static Bakery loadBake(Context context) {
            Bakery bakery = new Bakery();
            SharedPreferences widgetPrefs = context.getSharedPreferences(PREFERENCE_NAME,
                    Context.MODE_PRIVATE);
            String key = widgetPrefs.getString(context.getString(R.string.widget_key), "");
            if (key != null) {
                bakery.toString();
            }
            return bakery;
        }

    }
}
