package com.core.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.heshan.androidcore.MainActivity
import com.heshan.androidcore.R

/**
 * Implementation of App Widget functionality.
 */
class CoffeeLoggerWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val intent = Intent(context.applicationContext, CoffeeQuotesService::class.java)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
        context.startService(intent)

    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object Test {
        private fun getPendingIntent(context: Context, value: Int): PendingIntent {
            //1
            val intent = Intent(context, WidgetActivity::class.java)
            //2
            intent.action = Constants.ADD_COFFEE_INTENT
            //3
            intent.putExtra(Constants.GRAMS_EXTRA, value)
            //4
            return PendingIntent.getActivity(context, value, intent, 0)
        }

        private fun getRandomQuote(context: Context): String {
            //1
            val quotes = context.resources.getStringArray(R.array.coffee_texts)
            //2
            val rand = Math.random() * quotes.size
            //3
            return quotes[rand.toInt()].toString()
        }

        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                            appWidgetId: Int) {

            val coffeeLoggerPersistence = CoffeeLoggerPersistence(context)
            val widgetText = coffeeLoggerPersistence.loadTitlePref().toString()


            //1

            //2
            val views = RemoteViews(context.packageName, R.layout.coffee_logger_widget)
            //3
            views.setTextViewText(R.id.appwidget_text, widgetText)
            //4
            views.setOnClickPendingIntent(R.id.ristretto_button,
                getPendingIntent(context, CoffeeTypes.RISTRETTO.grams))
            views.setOnClickPendingIntent(R.id.espresso_button,
                getPendingIntent(context, CoffeeTypes.ESPRESSO.grams))
            views.setOnClickPendingIntent(R.id.long_button,
                getPendingIntent(context, CoffeeTypes.LONG.grams))
            // 5

            views.setTextViewText(R.id.coffee_quote, getRandomQuote(context))

            // 1
            val limit = coffeeLoggerPersistence.getLimitPref(appWidgetId)
// 2
            val background = if (limit <= widgetText.toInt()) R.drawable.background_overlimit
            else R.drawable.background
// 3
            views.setInt(R.id.widget_layout, "setBackgroundResource", background)


            appWidgetManager.updateAppWidget(appWidgetId, views)


        }
    }

}


