package com.android.insta_testproject_alfa.ui.photo_list

import com.android.insta_testproject_alfa.room.models.PhotoItem
import com.arellomobile.mvp.MvpView

interface PhotoListView : MvpView{

    fun showLoading()

    fun hideLoading()

    fun showPhotos(photos: List<PhotoItem>)

    fun showError(error: String)
}