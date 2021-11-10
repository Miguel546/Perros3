package com.android.perros.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.perros.MainActivityViewModel
import com.android.perros.R
import com.android.perros.beans.Perro
import com.android.perros.databinding.ItemPerrosBinding

class PerrosAdapter(val clickListener: ItemSelectedListener,
                    val clickPosicionListener: PosicionSelectedListener,
                    val viewModel2: MainActivityViewModel,
                    val lifecycleOwner: LifecycleOwner)
    : ListAdapter<Perro, PerrosAdapter.ViewHolder>(PerrosDiffCallback()){

    class ViewHolder private constructor(val binding: ItemPerrosBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Perro,
            position: Int,
            clickListener: ItemSelectedListener,
            clickPosicionListener: PosicionSelectedListener,
            viewModel2: MainActivityViewModel,
            lifecycleOwner: LifecycleOwner
        ) {
            binding.itemOrigen = item
            binding.clickListener = clickListener
            binding.position = position
            binding.clickPosicion = clickPosicionListener
            binding.viewModel = viewModel2
            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPerrosBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }


    }
    class PerrosDiffCallback: DiffUtil.ItemCallback<Perro>(){
        override fun areItemsTheSame(oldItem: Perro, newItem: Perro): Boolean {
            return oldItem.idSplaft == newItem.idSplaft
        }

        override fun areContentsTheSame(oldItem: Perro, newItem: Perro): Boolean {
            return oldItem == newItem
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerrosAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val put = this@PerrosAdapter.lifecycleOwner
        holder.bind(item, position, clickListener, clickPosicionListener, viewModel2, put)
    }

}

class ItemSelectedListener(val clickItemSelected: (perro: Perro) -> Unit){
    fun onClick(perro: Perro) = clickItemSelected(perro)
}

class PosicionSelectedListener(val clickPosicionSelected: (id: Int?, perro: Perro) ->Unit){
    fun onClickPosicion(position: Int, perro: Perro) = clickPosicionSelected(position, perro)
}