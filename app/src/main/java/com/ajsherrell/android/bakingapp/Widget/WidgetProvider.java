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
package com.ajsherrell.android.bakingapp.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.ajsherrell.android.bakingapp.Constants;
import com.ajsherrell.android.bakingapp.MainActivity;
import com.ajsherrell.android.bakingapp.Models.Bakery;
import com.ajsherrell.android.bakingapp.R;

public class WidgetProvider extends AppWidgetProvider {

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId) {
        Bakery bakery = (Bakery) Constants.WidgetPref.loadBake(context);
        if (bakery != null) {
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, MainActivity.class), 0);
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.bakery_widget);
            rv.setTextViewText(R.id.widget_name_tv, bakery.getName());

            // click item
            rv.setOnClickPendingIntent(R.id.widget_name_tv, pendingIntent);

            // use listview
            Intent intent = new Intent(context, WidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            rv.setRemoteAdapter(R.id.widget_listview, intent);
            appWidgetManager.updateAppWidget(appWidgetId, rv);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    public static void updateAppWidgets(Context context, AppWidgetManager appWidgetManager,
                                        int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }
}
