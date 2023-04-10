package com.zonebug.debugging.activity.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
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
        val uploadBtn : ImageButton = view.findViewById(R.id.Search_Before_Btn_Upload)
        val replaceBtn : LinearLayout = view.findViewById(R.id.Search_Before_Container_Replace)
        val imageView : ImageView = view.findViewById(R.id.Search_Before_Image)

        uploadBtn.setOnClickListener {
            replaceBtn.visibility = View.VISIBLE
        }





        return view
    }

}