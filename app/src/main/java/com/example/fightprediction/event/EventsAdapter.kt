package com.example.fightprediction.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fightprediction.database.entities.Event
import com.example.fightprediction.databinding.EventItemBinding

class EventsAdapter(private val listener: OnItemClickListener) : ListAdapter<Event, EventsAdapter.EventsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val binding = EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class EventsViewHolder(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION){
                        val event = getItem(position)
                        listener.onItemClick(event)
                    }
                }
            }
        }

        fun bind(event: Event){
            binding.apply {
                textViewEventName.text = event.name
                textViewEventDate.text = event.date
                textViewEventCity.text = event.city
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(event: Event)
    }

    class DiffCallback : DiffUtil.ItemCallback<Event>(){
        override fun areItemsTheSame(oldItem: Event, newItem: Event) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Event, newItem: Event) =
            oldItem == newItem
    }
}