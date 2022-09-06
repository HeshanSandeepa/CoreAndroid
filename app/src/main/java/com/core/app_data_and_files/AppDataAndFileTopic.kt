package com.core.app_data_and_files


enum class FileTopic {
    APP_SPECIFIC_FOLDER_CREATE,
    APP_SPECIFIC_FILE_CREATE_AND_WRITE,
    APP_SPECIFIC_FILE_AND_READ,
    APP_SPECIFIC_FILE_CREATE_IN_FOLDER,
    APP_SPECIFIC_FILE_WRITE_IN_FOLDER,
    APP_SPECIFIC_FILE_READ_IN_FOLDER,
    APP_SPECIFIC_FILE_LIST,

}
data class AppDataAndFileTopic( val topic: FileTopic, val title: String)
