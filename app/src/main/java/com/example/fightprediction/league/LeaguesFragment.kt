package com.example.fightprediction.league

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fightprediction.R
import com.example.fightprediction.databinding.LeaguesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaguesFragment : Fragment(R.layout.leagues_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind(view)
    }

    private fun bind(view: View){
        val binding = LeaguesFragmentBinding.bind(view)
    }
}