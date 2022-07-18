package com.core.images_graphics

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.View
import com.heshan.androidcore.R

class CustomShapeDrawable(context: Context): View(context) {

    private val drawable: ShapeDrawable = run {
        val x = 10
        val y = 10
        val width = 300
        val height = 300
        contentDescription = context.resources.getString(R.string.my_view_desc)

        ShapeDrawable(OvalShape()).apply {
            // If the color isn't set, the shape uses black as the default.
            paint.color = 0xff74AC23.toInt()
            // If the bounds aren't set, the shape can't be drawn.
            setBounds(x, y, x + width, y + height)
        }
    }
    override fun onDraw(canvas: Canvas?) {
        if (canvas != null) {
            drawable.draw(canvas)
        }
    }
}