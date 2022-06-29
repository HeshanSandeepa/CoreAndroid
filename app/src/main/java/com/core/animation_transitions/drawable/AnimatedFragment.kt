/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.core.animation_transitions.drawable

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.heshan.androidcore.R
import com.heshan.androidcore.databinding.DrawableAnimatedFragmentBinding

class AnimatedFragment : Fragment() {


    private lateinit var binding: DrawableAnimatedFragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout {
        binding = DrawableAnimatedFragmentBinding.inflate(inflater, container, false)


        val icon = AnimatedVectorDrawableCompat.create(
            requireContext(),
            R.drawable.ic_hourglass_animated
        )!!

        icon.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationStart(drawable: Drawable?) {
                binding.start.isEnabled = false
                binding.stop.isEnabled = true
            }

            override fun onAnimationEnd(drawable: Drawable?) {
                binding.start.isEnabled = true
                binding.stop.isEnabled = false
            }
        })
        binding.icon.setImageDrawable(icon)
        binding.start.setOnClickListener { icon.start() }
        binding.stop.setOnClickListener { icon.stop() }

        return binding.root
    }
}
