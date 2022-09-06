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
        AppDataAndFileTopic(FileTopic.APP_SPECIFIC_FILE_CREATE_AND_WRITE, "Create & Write Internal File"),
        AppDataAndFileTopic(FileTopic.APP_SPECIFIC_FILE_AND_READ, "Read Internal Folder"),
        AppDataAndFileTopic(FileTopic.APP_SPECIFIC_FILE_CREATE_IN_FOLDER, "Create Internal Internal Folder"),
        AppDataAndFileTopic(FileTopic.APP_SPECIFIC_FILE_WRITE_IN_FOLDER, "Write Internal Internal File"),
        AppDataAndFileTopic(FileTopic.APP_SPECIFIC_FILE_READ_IN_FOLDER, "Read Internal Internal File"),
        AppDataAndFileTopic(FileTopic.APP_SPECIFIC_FILE_LIST, "Internal folder file count"))


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
                FileTopic.APP_SPECIFIC_FILE_CREATE_AND_WRITE -> createFileAndWriteInInternalFolder()
                FileTopic.APP_SPECIFIC_FILE_AND_READ, -> createFileAndReadInternalFolder()
                FileTopic.APP_SPECIFIC_FILE_CREATE_IN_FOLDER -> createFileInInternalFolder()
                FileTopic.APP_SPECIFIC_FILE_WRITE_IN_FOLDER -> writeInFileInFolderInInternalFolder()
                FileTopic.APP_SPECIFIC_FILE_READ_IN_FOLDER -> readInFileInFolderInInternalFolder()
                FileTopic.APP_SPECIFIC_FILE_LIST -> fileListInInternalFolder()
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

    private fun createFileAndWriteInInternalFolder() {
        val fileName = File(this.filesDir.path, "internalFile.txt")
        if (fileName.exists()) {
            this.openFileOutput("internalFile.txt", Context.MODE_PRIVATE).use {
                it.write("Hello File".toByteArray())
                Toast.makeText(this, "File Updated", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createFileAndReadInternalFolder() {
        val fileName = File( this.filesDir.path, "internalFile.txt")
        if (fileName.exists()) {
            this.openFileInput("internalFile.txt").use {
                var text = it.bufferedReader().use {
                    it.readText();
                }
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
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

    private fun writeInFileInFolderInInternalFolder() {
        val internalFolderPath = this.filesDir.path + "/INTERNAL_FOLDER"
        val fileName = File(internalFolderPath, "internalFile.txt")
        if (fileName.exists()) {
            this.openFileOutput("internalFile.txt", Context.MODE_PRIVATE).use {
                it.write("Hello File".toByteArray())
                Toast.makeText(this, "File Updated", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readInFileInFolderInInternalFolder() {
        val internalFolderPath = this.filesDir.path + "/INTERNAL_FOLDER"
        val fileName = File( internalFolderPath, "internalFile.txt")
        if (fileName.exists()) {
            this.openFileInput(fileName.name).use {
                var text = it.bufferedReader().use {
                    it.readText();
                }
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fileListInInternalFolder() {
        var names: String = ""
        var files: Array<String> = this.fileList()
        for (file in files) {
            names = names + " - " + file
        }
        Toast.makeText(this, names, Toast.LENGTH_SHORT).show()
    }


}