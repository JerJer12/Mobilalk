package com.example.reflecgame.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.reflecgame.R
import com.example.reflecgame.databinding.GameFragmentBinding



class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)
        viewModel= ViewModelProvider(this).get(GameViewModel::class.java)
        binding.gameViewModel = viewModel
        binding.lifecycleOwner=viewLifecycleOwner
        viewModel._readygo.value ="Ready"
        viewModel.eventGameEnd.observe(viewLifecycleOwner, Observer {hasEnded ->
            if (hasEnded) {//gameEnded()
               // findNavController().navigate(GameFragmentDirections.actionGameToResult())
                viewModel.onGameEndComp()
            }
        })
        //todo: ne lehessen hamarabb megnyomni a gombot
       /* binding.pressButton.setOnClickListener {
            findNavController().navigate(GameFragmentDirections.actionGameToResult())
        }*/
        binding.readyButton.setOnClickListener {
            viewModel.onReady()
        }
        return binding.root


    }


    private fun gameEnded(){
        val action = GameFragmentDirections.actionGameToResult()
        //find out why it doesnt find the result
        // action.result = viewModel.result.value ?:0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onGameEndComp()
    }
}