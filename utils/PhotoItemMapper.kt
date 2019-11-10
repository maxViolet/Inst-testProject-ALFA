package com.android.insta_testproject_alfa.utils

import com.android.insta_testproject_alfa.room.models.PhotoItem
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import java.util.*

    fun queryDocumentToPhotoItem(query: QueryDocumentSnapshot): PhotoItem {
        val tempItem = PhotoItem()
        tempItem.url = query.data["url"].toString()
        tempItem.liked = (query.data["liked"].toString() == "true")

        return tempItem
    }
