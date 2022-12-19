package com.example.reflecgame.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.reflecgame.R
import com.example.reflecgame.ScoreListener
import com.example.reflecgame.database.ScoreBoard
import com.example.reflecgame.database.ScoreDatabase
import com.example.reflecgame.databinding.GameFragmentBinding



class GameFragment : Fragment() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

    val binding: GameFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.game_fragment,container,false
    )

    val application = requireNotNull(this.activity).application

    val dataSource=ScoreDatabase.getInstance(application).scoreDatabaseDao
    val viewModelFactory = GameViewModelFactory(dataSource,application)

    val gameViewModel = ViewModelProvider(
        this, viewModelFactory).get(GameViewModel::class.java)

    binding.gameViewModel = gameViewModel

    binding.lifecycleOwner =this

       /* binding.pressButton.setOnClickListener {
            findNavController().navigate(GameFragmentDirections.actionGameToResult())
        }*/


        binding.readyButton.setOnClickListener {
            gameViewModel.onReady()
        }

    binding.scoreButton.setOnClickListener {
        findNavController().navigate(GameFragmentDirections.actionGameToResult())
    }

        return binding.root


    }

}



