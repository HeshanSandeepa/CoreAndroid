package com.heshan.androidcore


enum class Topic {
    APP_DATA_FILES,
    BACKGROUND_TASKS,
    SERVICES

}
data class CoreTopic( val topic: Topic, val title: String)
