package com.bilyoner.bets.presentation.league

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bilyoner.bets.databinding.ItemLeagueBinding
import com.bilyoner.bets.enums.League

class LeagueAdapter(
    private val leagues: List<League>,
    private var selectedLeague: League?,
    private val onLeagueSelected: (League) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {

    inner class LeagueViewHolder(val binding: ItemLeagueBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLeagueBinding.inflate(inflater, parent, false)
        return LeagueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val league = leagues[position]

        holder.binding.tvLeagueName.text = league.displayName
        holder.binding.ivCheck.visibility = if (league == selectedLeague) View.VISIBLE else View.GONE

        holder.binding.root.setOnClickListener {
            val oldSelected = selectedLeague
            selectedLeague = league

            notifyItemChanged(leagues.indexOf(oldSelected))
            notifyItemChanged(position)

            onLeagueSelected(league)
        }
    }

    override fun getItemCount(): Int = leagues.size
}