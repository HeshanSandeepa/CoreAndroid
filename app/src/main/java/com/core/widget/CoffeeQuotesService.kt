package com.core.widget

import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.IBinder

class CoffeeQuotesService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val allWidgetIds = intent?.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS)
        //1
        if (allWidgetIds != null) {
            //2
            for (appWidgetId in allWidgetIds) {
                //3
                CoffeeLoggerWidget.updateAppWidget(this, appWidgetManager, appWidgetId)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onBind(intent: Intent): IBinder? {
        return null;

    }
}