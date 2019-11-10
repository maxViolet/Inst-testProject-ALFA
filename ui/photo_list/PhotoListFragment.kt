package com.android.insta_testproject_alfa.ui.photo_list

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.insta_testproject_alfa.R
import com.android.insta_testproject_alfa.room.models.PhotoItem
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import androidx.core.app.ActivityCompat.startActivityForResult
import android.provider.MediaStore
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PhotoListFragment : MvpAppCompatFragment(), PhotoListView {

    private val CAMERA_REQUEST_CODE = 12345

    companion object {
        const val TAG: String = "PhotoListFragment"

        @JvmStatic
        fun instance() = PhotoListFragment()
    }

    private lateinit var root: ViewGroup
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabButton: FloatingActionButton
    private lateinit var adapter: PhotoListAdapter

    @InjectPresenter
    lateinit var presenter: PhotoListPresenter

    @ProvidePresenter
    fun providePresenter(): PhotoListPresenter {
        return PhotoListPresenter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.apply {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_photo_list, null) as ViewGroup
        bindViews(root)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init()
        setupUi()
    }

    private fun bindViews(root: View) {
        recyclerView = root.findViewById(R.id.recyclerview_photo_list)
        fabButton = root.findViewById(R.id.fab_button_menu)
    }

    private fun setupUi() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = PhotoListAdapter()
        recyclerView.adapter = adapter
//        val decoration = ItemDecorator(DensityPixelMath.dpToPx(15), 1)
//        recyclerView.addItemDecoration(decoration)

        fabButton.setOnClickListener {
            //            presenter.onTakePhotoClicked()
            takePhoto()
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showPhotos(photos: List<PhotoItem>) {
        adapter.refreshPhotoList(photos)
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun takePhoto() {
        val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (callCameraIntent.resolveActivity(activity!!.packageManager) != null) {
            startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
        }
    }

    override fun addNewPhoto(photo: PhotoItem) {
        adapter.addNewPhoto(photo)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (resultCode == RESULT_OK && data != null) {
                    presenter.addNewPhoto(data.extras.get("data") as Bitmap)
                }
            }
            else -> {
                Toast.makeText(activity, "Unrecognized request code", Toast.LENGTH_SHORT).show()
            }
        }
    }
}