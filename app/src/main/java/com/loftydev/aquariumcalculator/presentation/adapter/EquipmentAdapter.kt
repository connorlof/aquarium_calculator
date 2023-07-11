package com.loftydev.aquariumcalculator.presentation.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.loftydev.aquariumcalculator.data.model.EquipmentResponseItem
import com.loftydev.aquariumcalculator.databinding.EquipmentListItemBinding

class EquipmentAdapter(private val context: Context) :
    RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder>(), Filterable {

    var equipmentList: ArrayList<EquipmentResponseItem> = ArrayList()
    var equipmentListFiltered: ArrayList<EquipmentResponseItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        val binding = EquipmentListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return EquipmentViewHolder(binding)
    }

    fun addData(list: List<EquipmentResponseItem>) {
        equipmentList = list as ArrayList<EquipmentResponseItem>
        equipmentListFiltered = equipmentList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return equipmentListFiltered.size
    }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        holder.bind(equipmentListFiltered[position])
    }

    inner class EquipmentViewHolder(
        private val binding: EquipmentListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(equipment: EquipmentResponseItem) {
            binding.tvTitle.text = equipment.productName
            binding.tvGallons.text = "Up to ${equipment.ratedGallons} gallons"
            binding.tvType.text = "Type: ${equipment.type}"
            binding.tvQuantity.text = "${equipment.quantity} is recommended"

            Glide.with(binding.ivImage.context)
                .load(equipment.imageLink)
                .into(binding.ivImage)

            binding.root.setOnClickListener {
                    val url = equipment.associatesLink
                    val urlIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url)
                    )
                    context.startActivity(urlIntent)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val maxGallons = constraint?.toString()?.toIntOrNull() ?: 0

                equipmentListFiltered = if (maxGallons < 1) equipmentList else {
                    val filteredList = ArrayList<EquipmentResponseItem>()
                    equipmentList
                        .filter {
                            it.ratedGallons <= maxGallons
                        }
                        .takeLast(3)
                        .forEach { filteredList.add(it) }
                    filteredList
                }

                return FilterResults().apply { values = equipmentListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                equipmentListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<EquipmentResponseItem>
                notifyDataSetChanged()
            }

        }
    }

}