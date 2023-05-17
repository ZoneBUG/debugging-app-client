package com.zonebug.debugging.activity.report.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zonebug.debugging.R

class CheckListRecyclerAdapter(private val checkList : List<String>)
    : RecyclerView.Adapter<CheckListRecyclerAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_report_checklist, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val postItem : String = checkList[position]

        holder.contents.text = postItem
    }

    override fun getItemCount(): Int {
        return checkList.size
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contents : TextView = itemView.findViewById(R.id.Item_Report_Checklist_Contents)
    }

}