package com.bilyoner.bets.presentation.league

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bilyoner.bets.databinding.LayoutBottomSheetLeagueBinding
import com.bilyoner.bets.enums.League
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LeagueBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: LayoutBottomSheetLeagueBinding? = null
    private val binding get() = _binding!!

    private var listener: OnLeagueSelectedListener? = null
    private var selectedLeague: League? = null

    companion object {
        private const val KEY_SELECTED_LEAGUE = "selected_league"

        fun newInstance(selectedLeague: League?): LeagueBottomSheetFragment {
            val fragment = LeagueBottomSheetFragment()
            val args = Bundle()
            args.putString(KEY_SELECTED_LEAGUE, selectedLeague?.name)
            fragment.arguments = args
            return fragment
        }
    }

    interface OnLeagueSelectedListener {
        fun onLeagueSelected(league: League)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = when {
            context is OnLeagueSelectedListener -> context
            parentFragment is OnLeagueSelectedListener -> parentFragment as OnLeagueSelectedListener
            else -> null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(KEY_SELECTED_LEAGUE)?.let {
            selectedLeague = League.valueOf(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutBottomSheetLeagueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leagues = League.entries.toList()

        val adapter = LeagueAdapter(
            leagues = leagues,
            selectedLeague = selectedLeague
        ) { league ->
            listener?.onLeagueSelected(league)
            dismiss()
        }

        binding.rvLeagues.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLeagues.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}