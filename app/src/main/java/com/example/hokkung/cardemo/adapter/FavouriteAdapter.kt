package com.example.hokkung.cardemo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.model.Shop
import kotlinx.android.synthetic.main.car_shop_item.view.*

class FavouriteAdapter :  ListAdapter<Shop, RecyclerView.ViewHolder>(PostDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favourite_shop_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ViewHolder).setData(item)
    }

    class PostDiffCallBack : DiffUtil.ItemCallback<Shop>() {

        override fun areItemsTheSame(oldItem: Shop, newItem: Shop): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Shop, newItem: Shop): Boolean = oldItem == newItem

    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun setData(item: Shop) {
            val image: String? = "https://via.placeholder.com/150/92c952"
            val ranking: String? = "083-0057442"
            val description: String? = "laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium"
            val color: String? = "#ABEBC6"
            val is_status = true

            view.name.text = item.name
            view.description.text = description
            view.phone.text = ranking
            if (is_status) {
                view.view_status.setBackgroundResource(R.drawable.circle)
            } else {
                view.view_status.setBackgroundResource(R.drawable.circle)
            }
            Glide.with(view.context).load(image).into(view.image)
        }
    }
}