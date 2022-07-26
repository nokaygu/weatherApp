package com.example.havadurumu.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.havadurumu.databinding.HavaFragmentBinding


class HavaFragment : Fragment() {

    private val viewModel: HavaViewModel by viewModels()


    val adapter = ItemAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.latitude.value=arguments?.getString("lat")
        viewModel.longitude.value=arguments?.getString("lon")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = HavaFragmentBinding.inflate(inflater)
        binding.progressBar.setVisibility(View.VISIBLE)
        binding.recyclerView.adapter = adapter
        viewModel.getHavaBilgisi()

        viewModel.days.observe(viewLifecycleOwner) {
            refreshRecyclerView()
            binding.progressBar.setVisibility(View.INVISIBLE)
        }
        return binding.root
    }

    fun refreshRecyclerView() {
        adapter.days = viewModel.days.value!!
        adapter.notifyDataSetChanged()


    }
}