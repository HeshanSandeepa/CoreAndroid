package com.heshan.androidcore

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.heshan.androidcore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CoreTopicClickListener  {

    private lateinit var binding: ActivityMainBinding

    private val  coreTopics: List<CoreTopic> = listOf(
        CoreTopic(Topic.APP_DATA_FILES, "AppData & Files"),
        CoreTopic(Topic.BACKGROUND_TASKS, "Background Tasks"),
        CoreTopic(Topic.SERVICES, "Services"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val recyclerView = binding.mainRecycleView
        val coreTopics = coreTopics
        val coreTopicAdapter = CoreTopicAdapter(coreTopics, this)

        recyclerView.adapter = coreTopicAdapter
        recyclerView.layoutManager  = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onTopicClicked(topic: CoreTopic?) {
        if (topic != null) {
            Log.e("onTopicClicked ", topic.title)
        }
    }


}