package com.example.reflecgame.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reflecgame.MyAdapter
import com.example.reflecgame.R
import com.example.reflecgame.ScoreListener
import com.example.reflecgame.database.ScoreDatabase
import com.example.reflecgame.databinding.ResultFragmentBinding
import com.example.reflecgame.game.GameFragmentDirections
import com.example.reflecgame.game.GameViewModel
import com.example.reflecgame.menu.StartFragmentDirections


class ResultFragment: Fragment() {
/*
    private lateinit var viewModel: ResultViewModel
    private lateinit var gameviewModel: GameViewModel
    private lateinit var viewModelFactory: ResultViewModelFactory
*/
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
        val application = requireNotNull(this.activity).application
        val arguments = ResultFragmentArgs.fromBundle(requireArguments())

        val dataSource= ScoreDatabase.getInstance(application).scoreDatabaseDao
        val viewModelFactory=ResultViewModelFactory(arguments.scoreKey,dataSource)

        val resultViewModel = ViewModelProvider(
            this,viewModelFactory).get(ResultViewModel::class.java)

    binding.resultViewModel=resultViewModel

    binding.setLifecycleOwner(this)

        /*
        viewModelFactory = ResultViewModelFactory(ResultFragmentArgs.fromBundle(requireArguments()).result.toLong())
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)
        gameviewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        viewModel.startObserving(gameviewModel)
        binding.resultViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
*/

        binding.homeButton.setOnClickListener {
            //findNavController().navigate(StartFragmentDirections.actionStartToGame())
            findNavController().navigate(ResultFragmentDirections.actionResultToStart())
        }

    val adapter = MyAdapter(ScoreListener { scoreId ->
        Toast.makeText(context,"${scoreId}", Toast.LENGTH_SHORT).show()
        resultViewModel.onClickedScore(scoreId)
    })
    val manager = LinearLayoutManager(activity)
    binding.leaderboard.layoutManager = manager
    binding.leaderboard.adapter = adapter
    resultViewModel.scores2.observe(viewLifecycleOwner, Observer{
        it?.let {
            adapter.addHeaderAndSummitList(it)
        }
    })


        return binding.root
    }

}