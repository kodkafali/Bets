package com.bilyoner.bets.presentation.betbasket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bilyoner.bets.databinding.FragmentBetBinding
import com.bilyoner.bets.presentation.betbulletin.EventViewModel
import com.bilyoner.bets.utils.extensions.format
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BetBasketBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentBetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BetBasketViewModel by viewModels()
    private val eventViewModel: EventViewModel by activityViewModels()

    private lateinit var adapter: BetBasketAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BetBasketAdapter(eventViewModel.betBasket.value ?: emptyList()) {
            eventViewModel.removeFromBasket(it)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        eventViewModel.betBasket.value?.let {
            adapter.updateData(it)

            binding.tvMatchCount.text = "Toplam Maç: ${it.size}"
            binding.tvTotalOdds.text = "Toplam Oran: ${viewModel.getTotalOdds(eventViewModel.betBasket.value).format(2)}"
        }

        binding.btnPlaceBet.setOnClickListener {
            Toast.makeText(requireContext(), "Bahis yapılıyor...", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()

        val dialog = dialog as? BottomSheetDialog
        val bottomSheet = dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

        bottomSheet?.let {
            val behavior = BottomSheetBehavior.from(it)

            it.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            it.requestLayout()

            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.skipCollapsed = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}