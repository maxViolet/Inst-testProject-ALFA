package com.android.insta_testproject_alfa

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide

class InstaApplication : Application() {

    //todo initiate all libs (glide, firebase..etc)
    //todo initiate database

    fun getContext(): Context {
        return this
    }
}