package com.zonebug.debugging.activity.report.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zonebug.debugging.DTO.report.ReportResponseDTO
import com.zonebug.debugging.R

class DrugListRecyclerAdapter(private val drugItemList : List<ReportResponseDTO.ReportItem.DrugListDTO.DrugItem>)
    : RecyclerView.Adapter<DrugListRecyclerAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_report_drug, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val drugItem : ReportResponseDTO.ReportItem.DrugListDTO.DrugItem = drugItemList[position]

        holder.name.text = drugItem.name
        holder.description.text = drugItem.description
    }

    override fun getItemCount(): Int {
        return drugItemList.size
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name : TextView = itemView.findViewById(R.id.Item_Report_Drug_Name)
        var description : TextView = itemView.findViewById(R.id.Item_Report_Drug_Description)
    }

}