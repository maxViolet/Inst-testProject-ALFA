package com.android.insta_testproject_alfa.room.models

import android.graphics.Bitmap

data class PhotoItem(
    var name: String? = null,
    var image: Bitmap? = null,
    var url: String? = null,
    var description: String? = null,
    var liked: Boolean? = null
//etc
)