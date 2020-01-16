package com.cxyzy.demo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cxyzy.demo.R
import com.cxyzy.demo.network.response.RepoResp
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapter : PagedListAdapter<RepoResp, RepoAdapter.ViewHolder>(DiffCallback()) {
    private lateinit var onItemClick: (RepoResp: RepoResp) -> Unit
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.itemView.textView.text = data.fullName
        holder.itemView.setOnClickListener { onItemClick(data) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ViewHolder(view)
    }

    infix fun setOnItemClick(onClick: (RepoResp: RepoResp) -> Unit) {
        this.onItemClick = onClick
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}

private class DiffCallback : DiffUtil.ItemCallback<RepoResp>() {
    override fun areContentsTheSame(oldItem: RepoResp, newItem: RepoResp): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: RepoResp, newItem: RepoResp): Boolean {
        return oldItem == newItem
    }
}