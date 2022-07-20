package com.core.images_graphics

import android.R.attr.animation
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.heshan.androidcore.R


class CustomShapeActivity : AppCompatActivity() {

    private lateinit var customDrawableView: CustomShapeDrawable
    private lateinit var anyDrawable: CustomAnyDrawable

    lateinit var imageView: ImageView
    lateinit var animation: AnimatedVectorDrawable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        anyDrawable = CustomAnyDrawable()
//        val imageView = ImageView(applicationContext)
//        imageView.setImageDrawable(anyDrawable)


        // animated vector
        imageView  = ImageView(applicationContext)
        imageView.setImageDrawable(getDrawable(R.drawable.animated_vector))


        setContentView(imageView)


//        customDrawableView = CustomShapeDrawable(this)
//        setContentView(customDrawableView)
    }

    override fun onStart() {
        super.onStart()

        val d: Drawable = imageView.getDrawable()
        if (d is AnimatedVectorDrawable) {
            animation = d
            animation.start()
        }
    }

}