package com.core.app_data_and_files

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.heshan.androidcore.R
import com.heshan.androidcore.databinding.ActivityMainBinding

class AppSpecificStorageActivity : AppCompatActivity(), AppDataAndFileClickListener  {

    private lateinit var binding: ActivityMainBinding

    private val  dataTopics: List<AppDataAndFileTopic> = listOf(
        AppDataAndFileTopic(FileTopic.APP_SPECIFIC_FOLDER_CREATE, "Create Internal Folder"),
        AppDataAndFileTopic(FileTopic.APP_SPECIFIC_FILE_CREATE, "Background Tasks"),
        AppDataAndFileTopic(FileTopic.APP_SPECIFIC_FILE_WRITE, "Services"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val recyclerView = binding.mainRecycleView
        val coreTopicAdapter = AppDataAndFileTopicAdapter(dataTopics, this)

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



    override fun onLowMemory() {
        super.onLowMemory()
        Log.e("onLowMemory ", " Memory Is Running Out")
    }

    override fun onAppDataTopicClicked(appDataAndFileTopic: AppDataAndFileTopic?) {
        if (appDataAndFileTopic != null) {
            Log.e("onAppDataTopicClicked ", appDataAndFileTopic.title)
        }
    }


}