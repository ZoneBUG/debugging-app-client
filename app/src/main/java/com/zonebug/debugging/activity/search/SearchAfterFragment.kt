package com.zonebug.debugging.activity.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zonebug.debugging.R


class SearchAfterFragment : Fragment() {

    private lateinit var viewModel : SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_after, container, false)
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[SearchViewModel::class.java]
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var speciesText = view.findViewById<TextView>(R.id.Search_After_Text_Species)
        var descriptionText = view.findViewById<TextView>(R.id.Search_After_Text_Description)
        val resultImg = view.findViewById<ImageView>(R.id.Search_After_Image)



        viewModel.myResponse.observe(viewLifecycleOwner, Observer { res ->
            var species = res.body()!!.species
            speciesText.text = species

            if(species == "발견된 해충이 없음") {
                resultImg.setImageResource(R.drawable.img_bug_no)
                descriptionText.text = "해당 사진에서는 해충이 발견되지 않았습니다!"
            } else {
                when (species) {
                    "바퀴벌레" -> resultImg.setImageResource(R.drawable.img_bug_1)
                    "그리마" -> resultImg.setImageResource(R.drawable.img_bug_2)
                    "지네" -> resultImg.setImageResource(R.drawable.img_bug_4)
                    "좀벌레" -> resultImg.setImageResource(R.drawable.img_bug_6)
                    "집게벌레" -> resultImg.setImageResource(R.drawable.img_bug_7)
                }

                descriptionText.text = res.body()!!.description
            }

        })


    }

}