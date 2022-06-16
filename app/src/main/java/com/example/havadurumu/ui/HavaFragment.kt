package com.example.havadurumu.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.havadurumu.databinding.HavaFragmentBinding



class HavaFragment : Fragment() {

    private val viewModel: HavaViewModel by viewModels()
    val adapter = ItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = HavaFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = adapter

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