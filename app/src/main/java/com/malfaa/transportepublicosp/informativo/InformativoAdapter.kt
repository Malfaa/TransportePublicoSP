package com.malfaa.transportepublicosp.informativo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.malfaa.transportepublicosp.databinding.ItemInformativoBinding
import com.malfaa.transportepublicosp.local.entidade.Onibus

class InformativoAdapter(private val clickListener: OnibusListener) : ListAdapter<Onibus, InformativoAdapter.ViewHolder>(ItemDiffCallBack()){
    //onCreate & onBind
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }


    class ViewHolder private constructor(private val binding: ItemInformativoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Onibus, clickListener: OnibusListener) {
            binding.item = item
            binding.clickListener = clickListener

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemInformativoBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class ItemDiffCallBack: DiffUtil.ItemCallback<Onibus>(){
        override fun areItemsTheSame(oldItem: Onibus, newItem: Onibus): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Onibus, newItem: Onibus): Boolean {
            return oldItem === newItem
        }
    }

    class OnibusListener(val clickListener: (Onibus) -> Unit) {
        fun onClick(onibus: Onibus) = clickListener(onibus)
    }
}