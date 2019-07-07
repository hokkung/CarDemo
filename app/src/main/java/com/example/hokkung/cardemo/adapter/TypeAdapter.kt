package com.example.hokkung.cardemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.model.Type
import kotlinx.android.synthetic.main.type_item.view.*

class TypeAdapter(private val callback: OnTypeClickListener) : ListAdapter<Type, RecyclerView.ViewHolder>(PostDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TypeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.type_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is TypeViewHolder) {
            val type = getItem(position)

            holder.view.name.text = type.name
            Glide.with(holder.view.context).load(type.image).into(holder.view.image)

            if (type.isCheck) {
                holder.view.cardView.setBackgroundResource(R.drawable.card_view_flat_clicked)
            } else {
                holder.view.cardView.setBackgroundResource(R.drawable.card_view_flat)
            }

            holder.view.cardView.setOnClickListener {
                type.isCheck = !type.isCheck
                callback.onItemClick(type)
            }

        }

        //(holder as? ViewHolder)?.setData(type)
        //(holder as? ViewHolder)?.setOnClickItem(type, callback)
    }

    class PostDiffCallBack : DiffUtil.ItemCallback<Type>() {

        override fun areItemsTheSame(oldItem: Type, newItem: Type): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Type, newItem: Type): Boolean {
            return oldItem.name == newItem.name && oldItem.isCheck == newItem.isCheck
        }

    }

    class TypeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun setData(type: Type) {

            view.name.text = type.name
            Glide.with(view.context).load(type.image).into(view.image)

            if (type.isCheck) {
                view.cardView.setBackgroundResource(R.drawable.card_view_flat_clicked)
            } else {
                view.cardView.setBackgroundResource(R.drawable.card_view_flat)
            }
        }

        fun setOnClickItem(type: Type, callback: OnTypeClickListener) {
            view.cardView.setOnClickListener {
                type.isCheck = !type.isCheck
                callback.onItemClick(type)
            }
        }
    }

    interface OnTypeClickListener {
        fun onItemClick(item: Type)
    }
}