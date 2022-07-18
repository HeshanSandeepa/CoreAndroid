package com.core.images_graphics

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class CustomShapeActivity : AppCompatActivity() {

    private lateinit var customDrawableView: CustomShapeDrawable
    private lateinit var anyDrawable: CustomAnyDrawable



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        anyDrawable = CustomAnyDrawable()
        val imageView = ImageView(applicationContext)
        imageView.setImageDrawable(anyDrawable)
        setContentView(imageView)


//        customDrawableView = CustomShapeDrawable(this)
//        setContentView(customDrawableView)
    }

}