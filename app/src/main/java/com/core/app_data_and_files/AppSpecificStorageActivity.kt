package com.core.app_data_and_files

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.heshan.androidcore.R
import com.heshan.androidcore.databinding.ActivityMainBinding
import java.io.File

class AppSpecificStorageActivity : AppCompatActivity(), AppDataAndFileClickListener  {

    private lateinit var binding: ActivityMainBinding

    private val  dataTopics: List<AppDataAndFileTopic> = listOf(
        AppDataAndFileTopic(FileTopic.APP_SPECIFIC_FOLDER_CREATE, "Create Internal Folder"),
        AppDataAndFileTopic(FileTopic.APP_SPECIFIC_FILE_CREATE, "Create File In Internal Folder"),
        AppDataAndFileTopic(FileTopic.APP_SPECIFIC_FILE_WRITE, "Write In to File"))

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
            when(appDataAndFileTopic.topic) {
                FileTopic.APP_SPECIFIC_FOLDER_CREATE -> createInternalFolder()
                FileTopic.APP_SPECIFIC_FILE_CREATE -> createFileInInternalFolder()
                FileTopic.APP_SPECIFIC_FILE_WRITE -> writeInToLocalFile()
                else -> {
                    Log.e(this.classLoader.toString(), "Some thing wrong")
                }
            }
        }
    }

    private fun createInternalFolder() {
        val internalFolder = File(this.filesDir, "INTERNAL_FOLDER")
        if (!internalFolder.exists()) {
            if(internalFolder.mkdir()) {
                Toast.makeText(this, "Folder Created", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createFileInInternalFolder() {
        val internalFolderPath = this.filesDir.path + "/INTERNAL_FOLDER"
        val fileName = File(internalFolderPath, "internalFile.txt")
        if (!fileName.exists()) {
            if(fileName.createNewFile()) {
                Toast.makeText(this, "File Created", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun writeInToLocalFile() {
        val internalFolderPath = this.filesDir.path + "/INTERNAL_FOLDER"
        val fileName = File(internalFolderPath, "internalFile.txt")
        if (fileName.exists()) {
            this.openFileOutput(fileName.canonicalPath, Context.MODE_PRIVATE).use {
                it.write("Hello File".toByteArray())
                Toast.makeText(this, "File Updated", Toast.LENGTH_SHORT).show()
            }
        }
    }
}