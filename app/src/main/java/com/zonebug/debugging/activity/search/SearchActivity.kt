package com.zonebug.debugging.activity.search

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.zonebug.debugging.R
import com.zonebug.debugging.databinding.ActivitySearchBinding
import java.text.SimpleDateFormat
import java.util.*

class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding
    var isBefore : Boolean = true
    lateinit var imageURI : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.Search_MainView, SearchBeforeFragment()).commit()


        // 하단 버튼 클릭
        binding.SearchBtn.setOnClickListener {
            if(isBefore) uploadImage()

            isBefore = !isBefore
            switchFragment()
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
        val intent = Intent()
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
        }
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


    override fun onBackPressed() {
        finish()
    }
}