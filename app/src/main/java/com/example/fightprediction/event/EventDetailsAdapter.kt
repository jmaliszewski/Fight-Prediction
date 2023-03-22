package com.example.fightprediction.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fightprediction.database.models.EventWithFights
import com.example.fightprediction.databinding.EventWithFightsItemBinding
import com.example.fightprediction.models.FightDetails

class EventDetailsAdapter : ListAdapter<FightDetails, EventDetailsAdapter.EventDetailsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventDetailsViewHolder {
        val binding = EventWithFightsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventDetailsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class EventDetailsViewHolder(private val binding: EventWithFightsItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(fightDetails: FightDetails){
            binding.apply {

            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<FightDetails>(){
        override fun areItemsTheSame(oldItem: FightDetails, newItem: FightDetails) =
            oldItem.fight.id == newItem.fight.id

        override fun areContentsTheSame(oldItem: FightDetails, newItem: FightDetails) =
            oldItem == newItem
    }
}