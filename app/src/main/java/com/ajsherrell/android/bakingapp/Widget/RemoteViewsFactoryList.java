package com.ajsherrell.android.bakingapp.Widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.ajsherrell.android.bakingapp.Constants;
import com.ajsherrell.android.bakingapp.Models.Bakery;
import com.ajsherrell.android.bakingapp.R;

public class RemoteViewsFactoryList implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private Bakery bakery;

    public RemoteViewsFactoryList(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        bakery = Constants.WidgetPref.loadBake(context);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return bakery.getIngredients().size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.bakery_widget_list_item);
        rv.setTextViewText(R.id.ingredients_item_tv, bakery.getIngredients().get(position).getIngredient());
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
