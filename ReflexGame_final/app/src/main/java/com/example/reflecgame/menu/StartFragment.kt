package com.example.reflecgame.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.reflecgame.R
import com.example.reflecgame.databinding.StartFragmentBinding




class StartFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: StartFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.start_fragment, container, false)


        binding.playButton.setOnClickListener {
            findNavController().navigate(StartFragmentDirections.actionStartToGame())
        }
        return binding.root
    }


}