package com.bilyoner.bets.presentation.betbulletin

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bilyoner.bets.databinding.ItemEventBinding
import com.bilyoner.bets.domain.model.Event
import com.bilyoner.bets.enums.League
import com.bilyoner.bets.utils.extensions.toFormattedLocalDateTime

class EventAdapter(
    private val onBetOptionSelected: (event: Event, odd: Double) -> Unit) : ListAdapter<Event, EventAdapter.EventViewHolder>(DiffCallback()) {

    inner class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(event: Event) {
            binding.txtLeagueName.text = event.title

            binding.txtHomeTeam.text = event.homeTeam
            binding.txtAwayTeam.text = event.awayTeam
            binding.txtMatchTime.text = event.date.toFormattedLocalDateTime()

            binding.txtOdd1.text = event.outComes.get(0).price.toString()
            binding.txtOddDraw.text = event.outComes.get(1).price.toString()
            binding.txtOdd2.text = event.outComes.get(2).price.toString()

            //Tüm oranlar listelenecekse ayrı bir item tanımlanır ve click işlemi tek bir noktadan yapılabilir.
            binding.txtOdd1.setOnClickListener {
                binding.txtOdd1.isSelected = !binding.txtOdd1.isSelected
                binding.txtOddDraw.isSelected = false
                binding.txtOdd2.isSelected = false

                event.isSelected = binding.txtOdd1.isSelected

                onBetOptionSelected(event, binding.txtOdd1.text.toString().toDouble())
            }
            binding.txtOddDraw.setOnClickListener {
                binding.txtOdd1.isSelected = false
                binding.txtOddDraw.isSelected = !binding.txtOddDraw.isSelected
                binding.txtOdd2.isSelected = false

                event.isSelected = binding.txtOddDraw.isSelected

                onBetOptionSelected(event, binding.txtOddDraw.text.toString().toDouble())
            }
            binding.txtOdd2.setOnClickListener {
                binding.txtOdd1.isSelected = false
                binding.txtOddDraw.isSelected = false
                binding.txtOdd2.isSelected = !binding.txtOdd2.isSelected

                event.isSelected = binding.txtOdd2.isSelected

                onBetOptionSelected(event, binding.txtOdd2.text.toString().toDouble())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean = oldItem == newItem
    }
}