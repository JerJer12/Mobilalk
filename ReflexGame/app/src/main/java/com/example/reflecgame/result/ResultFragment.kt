package com.example.reflecgame.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.reflecgame.R
import com.example.reflecgame.databinding.ResultFragmentBinding
import com.example.reflecgame.game.GameFragmentDirections
import com.example.reflecgame.game.GameViewModel
import com.example.reflecgame.menu.StartFragmentDirections


class ResultFragment: Fragment() {

    private lateinit var viewModel: ResultViewModel
    private lateinit var gameviewModel: GameViewModel
    private lateinit var viewModelFactory: ResultViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class.
        val binding: ResultFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.result_fragment,
            container,
            false
        )
        viewModelFactory = ResultViewModelFactory(ResultFragmentArgs.fromBundle(requireArguments()).result.toLong())
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)
        gameviewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        viewModel.startObserving(gameviewModel)
        binding.resultViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.homeButton.setOnClickListener {
            //findNavController().navigate(StartFragmentDirections.actionStartToGame())
            findNavController().navigate(ResultFragmentDirections.actionResultToStart())
        }


        return binding.root
    }


}