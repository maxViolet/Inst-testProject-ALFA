package com.android.insta_testproject_alfa

import android.app.Application
import android.content.Context

class InstaApplication : Application() {
    private val mApplication: InstaApplication? = null

    fun getContext(): Context {
        return mApplication as Context
    }

    //todo initiate all libs (glide, firebase..etc)
    //todo initiate database

}