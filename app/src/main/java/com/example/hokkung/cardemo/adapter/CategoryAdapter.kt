package com.example.hokkung.cardemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hokkung.cardemo.R
import com.example.hokkung.cardemo.model.Category
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter(private val callback: OnCategoryCheckListener) : ListAdapter<Category, RecyclerView.ViewHolder>(PostDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val category = getItem(position)
        (holder as? ViewHolder)?.setData(category)
        (holder as? ViewHolder)?.setOnClickCheckbox(category, callback)
    }

    class PostDiffCallBack : DiffUtil.ItemCallback<Category>() {

        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean = oldItem == newItem

    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun setData(category: Category) {
            view.checkBoxCategory.text = category.name
            view.checkBoxCategory.isChecked = category.isChecked
        }

        fun setOnClickCheckbox(category: Category, callback: OnCategoryCheckListener) {
            view.checkBoxCategory.setOnCheckedChangeListener { compoundButton, b ->
                category.isChecked = b
                callback.onClickCheckbox(category)
            }
        }
    }

    interface OnCategoryCheckListener {
        fun onClickCheckbox(item: Category)
    }
}