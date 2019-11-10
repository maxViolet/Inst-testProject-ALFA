package com.android.insta_testproject_alfa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openPhotoListFragment()
    }

    private fun  openPhotoListFragment() {
        val fragment = com.android.insta_testproject_alfa.ui.photo_list.PhotoListFragment.instance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, fragment, com.android.insta_testproject_alfa.ui.photo_list.PhotoListFragment.TAG)
            .addToBackStack(com.android.insta_testproject_alfa.ui.photo_list.PhotoListFragment.TAG)
            .commit()
    }
}
