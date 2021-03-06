/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.core.animation_transitions.motion.ui.demolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.core.animation_transitions.motion.ui.EdgeToEdge
import com.heshan.androidcore.R

class DemoListFragment : Fragment() {

    private val viewModel: DemoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.motion_demo_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val demoList: RecyclerView = view.findViewById(R.id.demo_list)
        EdgeToEdge.setUpScrollingContent(demoList)

        val adapter = DemoListAdapter { demo ->
            requireActivity().startActivity(demo.toIntent())
        }
        demoList.adapter = adapter
        viewModel.demos.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }
}
