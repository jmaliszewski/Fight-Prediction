package com.example.fightprediction.event

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fightprediction.R
import com.example.fightprediction.database.entities.Event
import com.example.fightprediction.databinding.EventsFragmentBinding
import com.example.fightprediction.util.exhaustive
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : Fragment(R.layout.events_fragment), EventsAdapter.OnItemClickListener {
    private val viewModel: EventsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView(view)
        setEvents()
    }

    private fun bindView(view: View){
        val binding = EventsFragmentBinding.bind(view)
        val eventAdapter = EventsAdapter(this)

        binding.apply {
            recyclerViewEvents.apply {
                adapter = eventAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.events.observe(viewLifecycleOwner){
            eventAdapter.submitList(it)
        }
    }

    private fun setEvents(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.eventEvents.collect{
                when(it){
                    is EventsViewModel.EventEvents.NavigateToEventDetailsFragment -> {
                        val action = EventsFragmentDirections.actionEventsFragmentToEventDetailsFragment(it.eventId)
                        findNavController().navigate(action)
                    }
                }.exhaustive
            }
        }
    }

    override fun onItemClick(event: Event) {
        viewModel.onEventSelected(event)
    }
}