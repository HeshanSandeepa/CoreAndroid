package com.core.app_data_and_files

import androidx.recyclerview.widget.RecyclerView
import com.heshan.androidcore.databinding.AppDataTopicViewHolderBinding

class AppDataAndFileTopicViewHolder(private val binding: AppDataTopicViewHolderBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun  binding(coreTopic: AppDataAndFileTopic, onClickListener: AppDataAndFileClickListener) {
        binding.appDataTopic = coreTopic
        binding.appDataTopicClickListener = onClickListener
    }
}