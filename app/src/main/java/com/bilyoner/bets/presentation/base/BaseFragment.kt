package com.bilyoner.bets.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bilyoner.bets.core.Resource

abstract class BaseFragment<VB : ViewBinding> (
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
): Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun <T> Fragment.observeResource(
        resource: Resource<T>,
        onSuccess: (T) -> Unit,
        onError: ((String?) -> Unit)? = null,
        onLoading: ((Boolean) -> Unit)? = null
    ) {
        when (resource) {
            is Resource.Loading -> onLoading?.invoke(true)
            is Resource.Success -> {
                resource.data?.let { onSuccess(it) }
                onLoading?.invoke(false)
            }
            is Resource.Error -> {
                onError?.invoke(resource.message)
                onLoading?.invoke(false)
            }
        }
    }

    open fun showLoading(isLoading: Boolean) {
        // override if needed
    }

    open fun showError(message: String?) {
        Toast.makeText(requireContext(), message ?: "Bilinmeyen hata", Toast.LENGTH_SHORT).show()
    }
}