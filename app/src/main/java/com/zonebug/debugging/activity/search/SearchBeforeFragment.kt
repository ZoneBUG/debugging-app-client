package com.zonebug.debugging.activity.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import com.zonebug.debugging.R

class SearchBeforeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_search_before, container, false)
        val container : LinearLayout = view.findViewById(R.id.Search_Before_Container_Image)
        val selectBtn : ImageButton = view.findViewById(R.id.Search_Before_Btn_Select)
        val editBtn : AppCompatButton = view.findViewById(R.id.Search_Before_Btn_Edit)

        // 이미지 선택하기
        selectBtn.setOnClickListener {
            (activity as SearchActivity).selectImage()
            container.setBackgroundResource(R.drawable.custom_button_white)
        }

        // 이미지 다시 선택하기
        editBtn.setOnClickListener {
            (activity as SearchActivity).selectImage()
        }

        return view
    }



}