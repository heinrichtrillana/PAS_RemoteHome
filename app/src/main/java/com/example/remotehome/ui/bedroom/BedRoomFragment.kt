package com.example.remotehome.ui.bedroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.remotehome.R

class BedRoomFragment : Fragment() {

    private lateinit var bedRoomViewModel: BedRoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bedRoomViewModel =
            ViewModelProviders.of(this).get(BedRoomViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_bedroom, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        bedRoomViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
