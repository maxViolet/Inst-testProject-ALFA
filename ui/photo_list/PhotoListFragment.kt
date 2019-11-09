package com.android.insta_testproject_alfa.ui.photo_list

import com.android.insta_testproject_alfa.room.models.PhotoItem
import com.arellomobile.mvp.MvpAppCompatFragment

class PhotoListFragment : MvpAppCompatFragment(),
    PhotoListView {

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPhotos(photos: List<PhotoItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val TAG: String = "PhotoListFragment"
    }

}