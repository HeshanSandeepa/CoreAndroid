package com.heshan.androidcore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heshan.androidcore.databinding.CoreTopicViewHolderViewBinding

class CoreTopicAdapter(private val coreTopics: List<CoreTopic>, private val onClickListener: CoreTopicClickListener):
    RecyclerView.Adapter<CoreTopicViewHolder>() {

    private lateinit var binding: CoreTopicViewHolderViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoreTopicViewHolder {
        binding = CoreTopicViewHolderViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CoreTopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoreTopicViewHolder, position: Int) {
        val coreTopic = coreTopics[position]
        holder.binding(coreTopic, onClickListener)

    }

    override fun getItemCount(): Int {
        return coreTopics.size
    }
}