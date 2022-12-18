package com.example.reflecgame.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reflecgame.MyAdapter
import com.example.reflecgame.R
import com.example.reflecgame.ScoreListener
import com.example.reflecgame.database.ScoreBoard
import com.example.reflecgame.database.ScoreDatabase
import com.example.reflecgame.databinding.GameFragmentBinding
import com.example.reflecgame.result.ResultFragmentDirections


class GameFragment : Fragment() {
/*
    private lateinit var viewModel: GameViewModel

    private lateinit var binding: GameFragmentBinding
*/
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



    /*
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
        })*/
        //todo: ne lehessen hamarabb megnyomni a gombot
       /* binding.pressButton.setOnClickListener {
            findNavController().navigate(GameFragmentDirections.actionGameToResult())
        }*/


        binding.readyButton.setOnClickListener {
           // binding.readyButton.isEnabled=false
            //binding.pressButton.isEnabled=true
            gameViewModel.onReady()
        }

    binding.scoreButton.setOnClickListener {
        findNavController().navigate(GameFragmentDirections.actionGameToResult())
    }
   /* binding.pressButton.setOnClickListener {
        binding.readyButton.isEnabled = true
        binding.pressButton.isEnabled=false
    }*/

       /* val adapter = MyAdapter(ScoreListener { scoreId ->
            Toast.makeText(context,"${scoreId}", Toast.LENGTH_SHORT).show()
            gameViewModel.onClickedScore(scoreId)
        })
    val manager = LinearLayoutManager(activity)
    binding.leaderboard.layoutManager = manager*/
        
        return binding.root


    }


    /*private fun gameEnded(){
        val action = GameFragmentDirections.actionGameToResult()
        //find out why it doesnt find the result
        // action.result = viewModel.result.value ?:0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onGameEndComp()
    }*/
}



