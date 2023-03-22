package com.example.fightprediction.league

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fightprediction.R
import com.example.fightprediction.databinding.LeagueDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeagueDetailsFragment : Fragment(R.layout.league_details_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind(view)
    }

    private fun bind(view: View){
        val binding = LeagueDetailsFragmentBinding.bind(view)
    }
}