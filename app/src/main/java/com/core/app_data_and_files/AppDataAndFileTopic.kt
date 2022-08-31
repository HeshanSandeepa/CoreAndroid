package com.core.app_data_and_files


enum class FileTopic {
    APP_SPECIFIC_FOLDER_CREATE,
    APP_SPECIFIC_FILE_CREATE,
    APP_SPECIFIC_FILE_WRITE

}
data class AppDataAndFileTopic( val topic: FileTopic, val title: String)
