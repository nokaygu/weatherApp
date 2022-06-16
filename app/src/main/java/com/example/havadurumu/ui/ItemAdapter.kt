package com.example.havadurumu.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.havadurumu.R
import com.example.havadurumu.databinding.ItemListesiBinding
import com.example.havadurumu.network.Daily
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


class ItemAdapter() :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>()  {
    var days: List<Daily> = listOf()

    inner class ItemViewHolder(val binding: ItemListesiBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding =
            ItemListesiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return days.size
    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        val day = days.get(position)
        val x:String = "en yüksek: "+day.temp.max.roundToInt().toString()+"°C\nen düşük  : "+day.temp.min.roundToInt().toString()+"°C"
        holder.binding.itemTitle.text=x
        holder.binding.weather.text=day.weather[0].description
        holder.binding.imageView.load("https://openweathermap.org/img/wn/${day.weather[0].icon}.png")
        holder.binding.dayName.text=convertToReadableDate(day.dt)

    }
    fun convertToReadableDate(timestamp: Long): String =
        SimpleDateFormat("dd/MM/yyyy").format(timestamp*1000)





}