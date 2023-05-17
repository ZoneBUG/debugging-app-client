package com.zonebug.debugging.activity.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


        viewModel.species.observe(viewLifecycleOwner, Observer {
            speciesText.text = it
        })

        viewModel.description.observe(viewLifecycleOwner, Observer {
            descriptionText.text = it
        })
    }

}