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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.android.drawableanimations.demo.Demo
import com.heshan.androidcore.R
import com.heshan.androidcore.databinding.DrawableHomeFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var binding: DrawableHomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DrawableHomeFragmentBinding.inflate(inflater, container, false)
        val adapter = DemoListAdapter { demo ->
            activity?.let { activity ->
                activity.supportFragmentManager.commit {
                    replace(R.id.main, demo.createFragment())
                    addToBackStack(null)
                }
                activity.title = demo.title
            }
        }
        binding.list.adapter = adapter
        adapter.submitList(listOf(
            Demo("AnimatedVectorDrawableCompat") { AnimatedFragment() },
            Demo("SeekableAnimatedVectorDrawable") { SeekableFragment() }
        ))

        return binding.root

    }

    override fun onResume() {
        super.onResume()
        activity?.setTitle(R.string.app_name)
    }
}
