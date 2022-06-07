package com.core.widget

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.heshan.androidcore.R

class CoffeeLoggerWidgetConfigureActivity : AppCompatActivity() {

    private lateinit var appWidgetText: EditText
    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID
    private val coffeeLoggerPersistence = CoffeeLoggerPersistence(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee_logger_widget_configure)

        findViewById<View>(R.id.add_button).setOnClickListener(onClickListener)

        //1
        appWidgetText = findViewById(R.id.appwidget_text)
//2
        val extras = intent.extras
//3
        if (extras != null) {
            appWidgetId = extras.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
        }
//4
        setResult(Activity.RESULT_CANCELED)

    }

    private var onClickListener: View.OnClickListener = View.OnClickListener {
        // 1
        val widgetText = appWidgetText.text.toString()
        // 2
        coffeeLoggerPersistence.saveLimitPref(widgetText.toInt(), appWidgetId)
        // 3

        updateWidget();
        val resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        // 4
        setResult(RESULT_OK, resultValue)
        // 5
        finish()
    }

    private fun updateWidget() {
        val appWidgetManager = AppWidgetManager.getInstance(this)
        CoffeeLoggerWidget.updateAppWidget(this, appWidgetManager, appWidgetId)
    }



}