package com.loftydev.aquariumcalculator.presentation.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.loftydev.aquariumcalculator.data.model.FilterResponseItem
import com.loftydev.aquariumcalculator.databinding.EquipmentListItemBinding

class EquipmentAdapter(private val context: Context) : RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<FilterResponseItem>() {
        override fun areItemsTheSame(oldItem: FilterResponseItem, newItem: FilterResponseItem): Boolean {
            return oldItem.associatesLink == newItem.associatesLink
        }

        override fun areContentsTheSame(oldItem: FilterResponseItem, newItem: FilterResponseItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        val binding = EquipmentListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return EquipmentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        val equipment = differ.currentList[position]
        holder.bind(equipment)
    }

    inner class EquipmentViewHolder(
        private val binding: EquipmentListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(equipment: FilterResponseItem) {
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

}