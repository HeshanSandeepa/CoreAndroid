package com.core.app_data_and_files

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
        AppDataAndFileTopic(FileTopic.APP_SPECIFIC_FILE_LIST, "Internal folder file count"),
        AppDataAndFileTopic(FileTopic.CACHE_QUOTA, "Get Cache Quota"),
        AppDataAndFileTopic(FileTopic.CACHE_CREATE_FILE_WRITE, "Create a file on  Cache"),
        AppDataAndFileTopic(FileTopic.CACHE_DELETE_FILE, "Delete a file From Cache"),
        AppDataAndFileTopic(FileTopic.EXTERNAL_AVAILABLE, "Is External Available"),
        AppDataAndFileTopic(FileTopic.EXTERNAL_STORAGE_SPACE_COUNT, "Check Number of External Storages"),
        AppDataAndFileTopic(FileTopic.EXTERNAL_STORAGE_FILE_COUNT, "External Directory File Count"),
        AppDataAndFileTopic(FileTopic.EXTERNAL_STORAGE_CREATE_FOLDER, "External Directory Folder Create"),
        AppDataAndFileTopic(FileTopic.EXTERNAL_STORAGE_WRITE_FILE_IN_FOLDER, "External Directory File Write"),
        AppDataAndFileTopic(FileTopic.EXTERNAL_STORAGE_READ_FILE_IN_FOLDER, "External Directory File Read"),
        AppDataAndFileTopic(FileTopic.EXTERNAL_STORAGE_CACHE_FILE_CREATE, "External CACHE FILE WRITE"),
        AppDataAndFileTopic(FileTopic.EXTERNAL_STORAGE_READ_CACHE_FILE, "External CACHE FILE READ"),
        AppDataAndFileTopic(FileTopic.EXTERNAL_STORAGE_DELETE_CACHE_FILE, "External Directory CACHE DELETE"),
        )


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
                FileTopic.APP_SPECIFIC_FILE_AND_READ -> createFileAndReadInternalFolder()
                FileTopic.APP_SPECIFIC_FILE_CREATE_IN_FOLDER -> createFileInInternalFolder()
                FileTopic.APP_SPECIFIC_FILE_WRITE_IN_FOLDER -> writeInFileInFolderInInternalFolder()
                FileTopic.APP_SPECIFIC_FILE_READ_IN_FOLDER -> readInFileInFolderInInternalFolder()
                FileTopic.APP_SPECIFIC_FILE_LIST -> fileListInInternalFolder()
                FileTopic.CACHE_QUOTA -> readCacheQuota()
                FileTopic.CACHE_CREATE_FILE_WRITE -> createFileOnCache()
                FileTopic.CACHE_DELETE_FILE -> deleteCacheFile()
                FileTopic.EXTERNAL_AVAILABLE -> isExternalMounted()
                FileTopic.EXTERNAL_STORAGE_SPACE_COUNT -> checkNumberOfExternalStorages()
                FileTopic.EXTERNAL_STORAGE_FILE_COUNT -> externalDirectoryFileList()
                FileTopic.EXTERNAL_STORAGE_CREATE_FOLDER -> externalDirectoryCreateFolder()
                FileTopic.EXTERNAL_STORAGE_WRITE_FILE_IN_FOLDER -> externalDirectoryCreateAndWriteFile()
                FileTopic.EXTERNAL_STORAGE_READ_FILE_IN_FOLDER -> externalDirectoryReadFile()
                FileTopic.EXTERNAL_STORAGE_CACHE_FILE_CREATE-> externalDirectoryCreateCacheFile()
                FileTopic.EXTERNAL_STORAGE_READ_CACHE_FILE -> externalDirectoryReadCacheFile()
                FileTopic.EXTERNAL_STORAGE_DELETE_CACHE_FILE -> externalDirectoryDeleteCacheFile()
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
            this.openFileInput(fileName.name).use { it ->
                val text = it.bufferedReader().use {
                    it.readText();
                }
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fileListInInternalFolder() {
        var names = ""
        val files: Array<String> = this.fileList()
        for (file in files) {
            names = "$names - $file"
        }
        Toast.makeText(this, names, Toast.LENGTH_SHORT).show()
    }

    private fun readCacheQuota() {
        val capacity = applicationContext.cacheDir.usableSpace
        val megabytes = capacity / 1024
        Toast.makeText(this, "$megabytes", Toast.LENGTH_SHORT).show()
    }

    private fun createFileOnCache() {

//        File.createTempFile("TEMP_FILE.txt", null, this.cacheDir)
//        val cacheFile = File(this.cacheDir, "TEMP_FILE.txt").us
//        if (cacheFile.exists()). {
//
//
//
//            this.openFileOutput("TEMP_FILE.txt", Context.MODE_PRIVATE).use {
//                it.write("Hello Cache File".toByteArray())
//                Toast.makeText(this, "Cache File Updated", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

    private fun deleteCacheFile() {
        val cacheFile = File(this.cacheDir, "TEMP_FILE.txt")
        if (cacheFile.exists()) {
            if (cacheFile.delete()) {
                Toast.makeText(this, "Cache File Deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isExternalMounted() {
        // Almost every Android device has this.

        // since Android 10, scoped storage access is enforced. That means you can access other
        // apps external storage by any means.

        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            Toast.makeText(this, "External Directory mounted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkNumberOfExternalStorages() {
        val externalStorageVolumes: Array<out File> =
            ContextCompat.getExternalFilesDirs(applicationContext, null)

        for (volume in externalStorageVolumes) {
            Log.e("Volume  -    ", volume.absolutePath)
        }
        Toast.makeText(this, "Number of External Devices are :  ${externalStorageVolumes.size}", Toast.LENGTH_SHORT).show()
    }

    private fun externalDirectoryFileList() {
        val files: Array<out File> = ContextCompat.getExternalFilesDirs(applicationContext, null)
        for (file in files) {
            Log.e("EXTERNAL FILES & FOLDERS  ", file.absolutePath)
        }
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
    }

    private fun externalDirectoryCreateFolder() {
        val externalFolder = File(this.getExternalFilesDir(null), "EXTERNAL_FOLDER")
        if (!externalFolder.exists()) {
            if(externalFolder.mkdir()) {
                Toast.makeText(this, "EXTERNAL_FOLDER Created", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    // Checks if a volume containing external storage is available to at least read.
    private fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
                || Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED_READ_ONLY
    }

    private fun externalDirectoryCreateAndWriteFile() {
        try {
            if (isExternalStorageWritable()) {
                Log.e("", "  Writable")
            }

            if (isExternalStorageReadable()) {
                Log.e("", "  Readable")
            }

            val content = "this si external file  written on devbcoe"
            //val externalFolder = File(this.getExternalFilesDir(null), "EXTERNAL_FOLDER")
            val fileName = File(applicationContext.getExternalFilesDir("MYFI"), "externalFile.txt")
            // if (!fileName.exists()) {
            //if(fileName.createNewFile()) {
            if (fileName.exists()) {
                applicationContext.openFileOutput("externalFile.txt", Context.MODE_PRIVATE).use {
                    it.write(content.toByteArray())
                    it.close()
                    Toast.makeText(this, "External File Updated", Toast.LENGTH_SHORT).show()
                }
            }
            // }
            //   }

//        //val externalFolder = File(this.getExternalFilesDir(null), "EXTERNAL_FOLDER")
//        val fileName = File(this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "externalFile.txt")
//        if (!fileName.exists()) {
//            if(fileName.createNewFile()) {
//                if (fileName.exists()) {
//                    this.openFileOutput(fileName.name, Context.MODE_APPEND).use {
//                        it.write("Hello External File".toByteArray())
//                        Toast.makeText(this, "External File Updated", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun externalDirectoryReadFile() {
        val externalFolder = File(this.getExternalFilesDir(null), "EXTERNAL_FOLDER")
        val fileName = File(externalFolder, "externalFile.txt")
        if (fileName.exists()) {
            this.openFileInput(fileName.name).use { it ->
                val text = it.bufferedReader().use {
                    it.readText();
                }
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun externalDirectoryCreateCacheFile() {

        val fileName = File(this.externalCacheDir, "externalCacheFile.txt")
        if (fileName.exists()) {
           // if (fileName.createNewFile()) {
                this.openFileOutput(fileName.name, Context.MODE_PRIVATE).use {
                    it.write("My name is Heshan".toByteArray())
                    Toast.makeText(this, "External Cache File Created", Toast.LENGTH_SHORT).show()
                }
           // }
        }
    }

    private fun externalDirectoryReadCacheFile() {
        val fileName = File(this.externalCacheDir, "externalCacheFile.txt")
        if (fileName.exists()) {
            this.openFileInput(fileName.name).use { it ->
                val text = it.bufferedReader().use {
                    it.readText();
                }
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun externalDirectoryDeleteCacheFile() {
        val fileName = File(this.externalCacheDir, "externalCacheFile.txt")
        if (fileName.exists()) {
            if (fileName.delete()) {
                Toast.makeText(this, "Cache Deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

// 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