package com.example.fightprediction.event

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fightprediction.R
import com.example.fightprediction.databinding.EventDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailsFragment : Fragment(R.layout.event_details_fragment) {
    private val viewModel: EventDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView(view)
        setEvents()
    }

    private fun setEvents() {
        TODO("Not yet implemented")
    }

    private fun bindView(view: View){
        val binding = EventDetailsFragmentBinding.bind(view)
        val eventDetailsAdapter = EventDetailsAdapter()

        binding.apply {
            recyclerViewFights.apply {
                adapter = eventDetailsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.fightDetails.observe(viewLifecycleOwner){
            eventDetailsAdapter.submitList(it)
        }

//        viewModel.eventWithFights.observe(viewLifecycleOwner){
//            eventDetailsAdapter.submitList(it)
//        }
    }
}