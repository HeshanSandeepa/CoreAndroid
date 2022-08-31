package com.core.app_data_and_files

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heshan.androidcore.databinding.AppDataTopicViewHolderBinding

class AppDataAndFileTopicAdapter(private val coreTopics: List<AppDataAndFileTopic>, private val onClickListener: AppDataAndFileClickListener):
    RecyclerView.Adapter<AppDataAndFileTopicViewHolder>() {

    private lateinit var binding: AppDataTopicViewHolderBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppDataAndFileTopicViewHolder {
        binding = AppDataTopicViewHolderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AppDataAndFileTopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppDataAndFileTopicViewHolder, position: Int) {
        val coreTopic = coreTopics[position]
        holder.binding(coreTopic, onClickListener)

    }

    override fun getItemCount(): Int {
        return coreTopics.size
    }
}