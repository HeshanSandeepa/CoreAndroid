package com.core.images_graphics

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.heshan.androidcore.R
import com.heshan.androidcore.databinding.ActivityPaletteBinding

class PaletteActivity : AppCompatActivity() {


    private lateinit var binding: ActivityPaletteBinding
    private lateinit var pallet: Palette

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palette)

        binding = ActivityPaletteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = getImage()
        pallet = createPalletSync(image)
    }

    override fun onResume() {
        super.onResume()
        setViewColor()
    }

    private fun getImage(): Bitmap {
        return binding.imageView.drawable.toBitmap();
    }

    private fun createPalletSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

    private fun setViewColor() {
        binding.root.setBackgroundColor(pallet.getDarkMutedColor(Color.CYAN))
    }


}