package com.zonebug.debugging.activity.search

import android.Manifest
import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.storage.FirebaseStorage
import com.zonebug.debugging.R
import com.zonebug.debugging.databinding.ActivitySearchBinding
import com.zonebug.debugging.retrofit.model.ModelRetrofitRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager

import android.app.Activity
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.zonebug.debugging.App
import com.zonebug.debugging.activity.community.detail.CommunityDetailActivity
import com.zonebug.debugging.activity.login.LoginActivity


class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var filePath : String
    val repository = ModelRetrofitRepository
    var isBefore : Boolean = true

    private lateinit var image : MultipartBody.Part
    private lateinit var imageURI : Uri

    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf<String>(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    fun verifyStoragePermissions(activity: Activity?) {
        val permission = ActivityCompat.checkSelfPermission(
            activity!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.Search_MainView, SearchBeforeFragment()).commit()

        verifyStoragePermissions(this@SearchActivity)

        // 하단 버튼 클릭
        binding.SearchBtn.setOnClickListener {
            if(isBefore) search(image) // uploadImage()

            isBefore = !isBefore
        }

    }


    private fun switchFragment() {
        val transaction = supportFragmentManager.beginTransaction()

        if(isBefore) {
            transaction.replace(R.id.Search_MainView, SearchBeforeFragment())
                .addToBackStack(null)
                .commit()
            binding.SearchBtn.text = "검색하기"
        } else {
            transaction.replace(R.id.Search_MainView, SearchAfterFragment())
                .addToBackStack(null)
                .commit()
            binding.SearchBtn.text = "다른 벌레 검색하기"
        }

    }


    fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK

        startActivityForResult(intent, 100)
    }


    override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val selectContainer : LinearLayout = this.findViewById(R.id.Search_Before_Container_Select)
        val editContainer : LinearLayout = this.findViewById(R.id.Search_Before_Container_Edit)
        val imageView : ImageView = this.findViewById(R.id.Search_Before_Image)

        if(requestCode == 100 && resultCode == RESULT_OK) {
            imageURI = data?.data!!
            imageView.setImageURI(imageURI)
            imageView.visibility = View.VISIBLE

            editContainer.visibility = View.VISIBLE
            selectContainer.visibility = View.GONE

            filePath = getRealPathFromURI(imageURI)

            val file = File(filePath)
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            image = MultipartBody.Part.createFormData("image", file.name, requestFile)
        }


    }

    private fun getRealPathFromURI(uri: Uri): String {
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor? = this.contentResolver.query(uri, proj, null, null, null)
        var index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result!!
    }


    private fun uploadImage() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("업로드중입니다")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("Search/$fileName")

        val imageView : ImageView = this.findViewById(R.id.Search_Before_Image)
        storageReference.putFile(imageURI).addOnSuccessListener {
            imageView.setImageURI(null)
            Toast.makeText(this@SearchActivity, "업로드 성공", Toast.LENGTH_SHORT).show()
            if(progressDialog.isShowing) progressDialog.dismiss()

        }.addOnFailureListener {
            if(progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this@SearchActivity, "업로드 실패", Toast.LENGTH_SHORT).show()
        }
    }

    private fun search(image : MultipartBody.Part) {
        val viewModelFactory = SearchViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        viewModel.search(image, "model_v4")
        viewModel.myResponse.observe(this, Observer {
            when {
                it.isSuccessful -> {
                    val searchResponseDTO = it.body()!!
                    viewModel.name = MutableLiveData<String>(searchResponseDTO.type)
                    Log.d("TAG", "========================================================================================================== " + searchResponseDTO.type)
                    switchFragment()

                }
                else -> {
                    Toast.makeText(this@SearchActivity, "검색에 실패했습니다.", Toast.LENGTH_SHORT).show()

                }
            }
        })
    }


    override fun onBackPressed() {
        finish()
    }
}