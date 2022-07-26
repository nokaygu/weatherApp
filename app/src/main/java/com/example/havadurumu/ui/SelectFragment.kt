package com.example.havadurumu.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.havadurumu.R
import com.example.havadurumu.databinding.FragmentSelectBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener


class SelectFragment : Fragment() {

    private val viewModel: SelectViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openTaskEvent.observe(this, EventObserver {
            if(it=="problem"){
                Toast.makeText(requireContext(), "yanlış lokasyon", Toast.LENGTH_SHORT).show()
            }
            else{
            val bundle= bundleOf("lat" to viewModel.latitude.value!!,
                "lon" to viewModel.longitude.value!!)

                findNavController().navigate(R.id.action_selectFragment_to_havaFragment, bundle)}
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectBinding.inflate(inflater)


        binding.button.setOnClickListener {
            checkLocationPermission()
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
            fusedLocationClient.getCurrentLocation(100, object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token

                override fun isCancellationRequested() = false
            })
                .addOnSuccessListener { location: Location? ->
                    if (location == null)
                        Toast.makeText(requireContext(), "Cannot get location.", Toast.LENGTH_SHORT).show()
                    else {

                        val bundle= bundleOf("lat" to location.latitude.toString(),
                            "lon" to location.longitude.toString())
                        findNavController().navigate(R.id.action_selectFragment_to_havaFragment, bundle)

                    }

                }

        }
        val autotextView=binding.textCity
        val citynames
                = resources.getStringArray(R.array.city_names)
        val adapter
                = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1, citynames)
        autotextView.setAdapter(adapter)
        binding.buttonCity.setOnClickListener {
            viewModel.cityCall(binding.textCity.text.toString())

        }

        return binding.root
    }
    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder(requireContext())
                    .setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept to use location functionality")
                    .setPositiveButton(
                        "OK"
                    ) { _, _ ->
                        //Prompt the user once explanation has been shown
                        requestLocationPermission()
                    }
                    .create()
                    .show()
            } else {
                // No explanation needed, we can request the permission.
                requestLocationPermission()
            }
        } else {
            // checkBackgroundLocation()
        }
    }
    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            99
        )
    }
}