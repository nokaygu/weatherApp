package com.example.havadurumu.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.havadurumu.databinding.HavaFragmentBinding
import kotlinx.coroutines.launch


class HavaFragment : Fragment() {

    private val viewModel: HavaViewModel by viewModels()

    val args: HavaFragmentArgs by navArgs()

    val adapter = ItemAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.latitude.value=args.lat
        viewModel.longitude.value=args.lon
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = HavaFragmentBinding.inflate(inflater)
       // binding.lifecycleOwner = requireActivity()
        binding.recyclerView.adapter = adapter
        viewModel.getHavaBilgisi()
        viewModel.days.observe(viewLifecycleOwner) {
            refreshRecyclerView()
        }
        return binding.root
    }

    fun refreshRecyclerView() {
        adapter.days = viewModel.days.value!!
        adapter.notifyDataSetChanged()


    }
}