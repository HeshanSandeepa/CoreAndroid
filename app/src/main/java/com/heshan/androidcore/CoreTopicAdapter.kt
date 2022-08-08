package com.heshan.androidcore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heshan.androidcore.databinding.ActivityMainTopicBinding

class CoreTopicAdapter(private val coreTopics: List<CoreTopic>):
    RecyclerView.Adapter<CoreTopicViewHolder>() {

    private lateinit var binding: ActivityMainTopicBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoreTopicViewHolder {
        binding = ActivityMainTopicBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CoreTopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoreTopicViewHolder, position: Int) {
        val coreTopic = coreTopics[position]
        holder.binding(coreTopic)
    }

    override fun getItemCount(): Int {
        return coreTopics.size
    }
}