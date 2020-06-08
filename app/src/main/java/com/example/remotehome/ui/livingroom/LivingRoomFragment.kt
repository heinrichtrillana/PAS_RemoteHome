package com.example.remotehome.ui.livingroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.remotehome.R

class LivingRoomFragment : Fragment() {

    private lateinit var livingRoomViewModel: LivingRoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        livingRoomViewModel =
            ViewModelProviders.of(this).get(LivingRoomViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_livingroom, container, false)
        livingRoomViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }
}
