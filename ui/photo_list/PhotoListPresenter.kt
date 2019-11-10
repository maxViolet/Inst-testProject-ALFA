package com.android.insta_testproject_alfa.ui.photo_list

import android.graphics.Bitmap
import android.util.Log
import com.android.insta_testproject_alfa.room.models.PhotoItem
import com.android.insta_testproject_alfa.ui.photo_list.PhotoListFragment.Companion.TAG
import com.android.insta_testproject_alfa.utils.queryDocumentToPhotoItem
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.Callable

@InjectViewState
class PhotoListPresenter : MvpPresenter<PhotoListView>() {

    private var storage: FirebaseStorage = FirebaseStorage.getInstance()
    private var storageRef: StorageReference = storage.reference
    private var jpegFolderRef: StorageReference = storageRef.child(jpegPath)

    var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    var dbRef: DatabaseReference = FirebaseDatabase.getInstance().reference

    var urlList = mutableListOf<String>()
    var photoList = mutableListOf<Bitmap>()
    var photoItemList = mutableListOf<PhotoItem>()

    companion object Consts {
        const val pngPath: String = "gs://alfa-testproject.appspot.com/png"
        const val jpegPath: String = /*"gs://alfa-testproject.appspot.com/jpeg"*/ "jpeg"
    }

    fun init() {
//        downloadPhotosFromRepo((jpegFolderRef.parent?.bucket ?: String()) + jpegFolderRef.name)
//        bindFolderListener(dbRef.child("imageUrls"))
        viewState.showLoading()
        downloadPhotosFromRepo()

        Log.d("TAG", urlList.toString())

    }


    /* private fun bindFolderListener(ref: DatabaseReference) {
         val eventListener: ValueEventListener = object : ValueEventListener {
             override fun onDataChange(dataSnapshot: DataSnapshot) {
 //                val list = mutableListOf<String>()
                 for (ds in dataSnapshot.children) {
 //                    val imageName = ds.key
                     val imageUrl = ds.getValue(String::class.java)
                     if (imageUrl != null) {
 //                        list.add(imageUrl)
                         urlList.add(imageUrl)
                     }
                 }
                 Log.d("TAG", urlList.toString())
             }

             override fun onCancelled(databaseError: DatabaseError) {}
         }
         ref.addListenerForSingleValueEvent(eventListener)
     }
 */
    /*fun onTakePhotoClicked() {
        viewState.takePhoto()
    }
*/
    private fun downloadPhotosFromRepo(/*url: String*/) {
        db.collection("imageUrls")
            .get()
            .addOnSuccessListener { documents ->
                val photoItemList2 = mutableListOf<PhotoItem>()
                for (document in documents) {

                    photoItemList2.add(queryDocumentToPhotoItem(document))

                    Log.d(TAG, "${document.id} => ${document.data}")
                    Log.d("", "")
                }
                viewState.hideLoading()
                viewState.showPhotos(photoItemList2)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

    }

    fun addNewPhoto(photo: Bitmap) {
//        photoList.add(photo)
        val tempItem = PhotoItem("", photo, "", "", false)
        viewState.addNewPhoto(tempItem)
    }
}