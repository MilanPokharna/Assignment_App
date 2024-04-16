package com.assignment.assignmentapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.assignmentapp.activities.DataDetailActivity
import com.assignment.assignmentapp.activities.DataListActivity
import com.assignment.assignmentapp.databinding.DataListItemBinding
import com.assignment.assignmentapp.models.Data

class DataListPagingAdapter(var context: Context) :
    PagingDataAdapter<Data, DataListPagingAdapter.DataViewHolder>(COMPARATOR) {

        class DataViewHolder(var binding: DataListItemBinding)
            : RecyclerView.ViewHolder(binding.root)

        override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
            val item = getItem(position)
            holder.binding.custUserId.text = item?.userId.toString()
            holder.binding.custId.text = item?.id.toString()
            holder.binding.custTitle.text = ""+item?.title

            holder.binding.root.setOnClickListener {
                val intent = Intent(context,DataDetailActivity::class.java)
                intent.putExtra("data",item)
                context.startActivity(intent)
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataListItemBinding.inflate(inflater, parent, false)
            return DataViewHolder(binding)
        }

        companion object {
            private val COMPARATOR = object : DiffUtil.ItemCallback<Data>() {
                override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                    return oldItem == newItem
                }
            }
        }
}