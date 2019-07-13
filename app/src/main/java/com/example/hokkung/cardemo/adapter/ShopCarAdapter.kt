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

class ShopCarAdapter :  ListAdapter<Shop, RecyclerView.ViewHolder>(PostDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.car_shop_item, parent, false))
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
            var image: String? = "https://via.placeholder.com/150/92c952"
            var ranking: String? = "083-0057442"
            var description: String? = "laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium"
            var status: String? = "open now"
            var color: String? = "#ABEBC6"
            var is_status = true

            view.name.text = item.name
            view.description.text = description
            view.status.text = status
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