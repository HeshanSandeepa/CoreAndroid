package com.heshan.androidcore

import androidx.recyclerview.widget.RecyclerView
import com.heshan.androidcore.databinding.ActivityMainTopicBinding

class CoreTopicViewHolder(private val binding: ActivityMainTopicBinding)
    : RecyclerView.ViewHolder(binding.root) {



    fun  binding(coreTopic: CoreTopic, onClickListener: CoreTopicClickListener) {
        binding.coreTopic = coreTopic
        binding.topicClickListener = onClickListener
    }


}