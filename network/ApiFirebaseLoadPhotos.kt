package com.android.insta_testproject_alfa.network

import com.android.insta_testproject_alfa.room.models.PhotoItem
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiFirebaseLoadPhotos {

    @GET("photos")
    fun getPhotoList(): Observable<List<PhotoItem>>

}