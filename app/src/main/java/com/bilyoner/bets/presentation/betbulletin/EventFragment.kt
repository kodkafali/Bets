package com.bilyoner.bets.presentation.betbulletin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bilyoner.bets.databinding.FragmentEventBinding
import com.bilyoner.bets.enums.League
import com.bilyoner.bets.presentation.base.BaseFragment
import com.bilyoner.bets.presentation.betbasket.BetBasketBottomSheet
import com.bilyoner.bets.presentation.league.LeagueBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventFragment : BaseFragment<FragmentEventBinding>(FragmentEventBinding::inflate), LeagueBottomSheetFragment.OnLeagueSelectedListener {

    private val viewModel: EventViewModel by activityViewModels()
    private lateinit var eventAdapter: EventAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        eventAdapter = EventAdapter{event, odd ->
            event.selectedOdd = odd
            if (event.isSelected) {
                viewModel.addToBasket(event)
            }
            else{
                viewModel.removeFromBasket(event)
            }
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.updateSearchQuery(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnFilter.setOnClickListener {
            val sheet = LeagueBottomSheetFragment.newInstance(viewModel.selectedLeague.value)
            sheet.show(childFragmentManager, "LeagueBottomSheet")
        }

        binding.rvEvents.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventAdapter
        }

        binding.fabOpenBasket.setOnClickListener {
            val bottomSheet = BetBasketBottomSheet()
            bottomSheet.show(parentFragmentManager, "BetBasketBottomSheet")
        }

        binding.tvBasketCount.animate()
            .scaleX(1.2f).scaleY(1.2f)
            .setDuration(100)
            .withEndAction {
                binding.tvBasketCount.animate().scaleX(1f).scaleY(1f).duration = 100
            }
            .start()

        viewModel.basketCount.observe(viewLifecycleOwner) { count ->
            if (count > 0) {
                binding.fabOpenBasket.visibility = View.VISIBLE
                binding.tvBasketCount.visibility = View.VISIBLE
                binding.tvBasketCount.text = if (count > 9) "9+" else count.toString()
            } else {
                binding.tvBasketCount.visibility = View.GONE
                binding.fabOpenBasket.visibility = View.GONE
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filteredEvents.collect { data ->
                    eventAdapter.submitList(data)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventsState.collect { resource ->
                    observeResource(
                        resource,
                        onSuccess = { events ->
                            eventAdapter.submitList(events)
                        },
                        onError = { message ->
                            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                        },
                        onLoading = {
                            binding.progressBar.isVisible = it
                        }
                    )
                }
            }
        }
    }

    override fun onLeagueSelected(league: League) {
        viewModel.selectLeague(league)
    }
}