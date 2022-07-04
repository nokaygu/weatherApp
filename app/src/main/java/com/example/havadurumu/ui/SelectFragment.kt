package com.example.havadurumu.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.content.ContextCompat

import androidx.fragment.app.Fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope

import androidx.navigation.fragment.findNavController
import com.example.havadurumu.databinding.FragmentSelectBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.*


class SelectFragment : Fragment() {

    private val viewModel: SelectViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        val binding = FragmentSelectBinding.inflate(inflater)

        binding.button.setOnClickListener {
            if (ContextCompat.checkSelfPermission( requireContext(),android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED )
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->if (location != null) {
                    viewModel.latitude.value=location?.latitude.toString()
                    viewModel.longitude.value=location?.longitude.toString()

                }
            }

            val action = SelectFragmentDirections.actionSelectFragmentToHavaFragment(lat = viewModel.latitude.value!!, lon=viewModel.longitude.value!!)
            findNavController().navigate(action)
        }
        binding.buttonCity.setOnClickListener {
            viewModel.cityName.value=binding.textCity.text.toString()
            var kaykil: String =viewModel.cityName.value!!
            Log.d("kaykil", kaykil)
            viewModel.cityCall(binding.textCity.text.toString())
            //viewModel.viewModelScope.launch{viewLifecycleOwner.lifecycleScope.async(Dispatchers.Main) {viewModel.cityCall()}.await()}
          //  viewModel.viewModelScope.launch { withContext(Dispatchers.IO) { viewModel.cityCall() }
            //delay(1000L)}
           // viewModel.viewModelScope.launch{ withContext(Dispatchers.IO) {Log.d("kaykil",viewModel.latitude.value!!)}}
            Log.d("kaykil","launchi geçtim")
            Log.d("kaykil",viewModel.latitude.value!!)
            val action = SelectFragmentDirections.actionSelectFragmentToHavaFragment(lat = viewModel.latitude.value!!, lon=viewModel.longitude.value!!)
            findNavController().navigate(action)
        }

        return binding.root
    }

}