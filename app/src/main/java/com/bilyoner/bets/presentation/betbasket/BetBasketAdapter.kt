package com.bilyoner.bets.presentation.betbasket

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bilyoner.bets.databinding.ItemBetBinding
import com.bilyoner.bets.domain.model.Event
import com.bilyoner.bets.utils.extensions.format
import com.bilyoner.bets.utils.extensions.toFormattedLocalDateTime

class BetBasketAdapter(
    private var items: List<Event>,
    private val onRemoveClick: (Event) -> Unit
) : RecyclerView.Adapter<BetBasketAdapter.BetBasketViewHolder>() {

    inner class BetBasketViewHolder(private val binding: ItemBetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: Event) = with(binding) {
            tvMatchName.text = item.teams
            tvTime.text = item.date.toFormattedLocalDateTime()
            tvMarket.text = item.title
            tvOdd.text = item.selectedOdd.format(2)

            btnRemove.setOnClickListener {
                onRemoveClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetBasketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBetBinding.inflate(inflater, parent, false)
        return BetBasketViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: BetBasketViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<Event>) {
        items = newItems
        notifyDataSetChanged()
    }
}