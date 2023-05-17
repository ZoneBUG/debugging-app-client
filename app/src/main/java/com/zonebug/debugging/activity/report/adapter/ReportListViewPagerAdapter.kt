package com.zonebug.debugging.activity.report.adapter

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zonebug.debugging.DTO.report.ReportResponseDTO
import com.zonebug.debugging.R
import java.text.SimpleDateFormat
import java.util.*

class ReportListViewPagerAdapter(private val reportItemList : List<ReportResponseDTO.ReportItem>)
    : RecyclerView.Adapter<ReportListViewPagerAdapter.PagerViewHolder>() {

    private lateinit var view: View

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : PagerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_report, parent, false)
        view = itemView
        return PagerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val reportItem : ReportResponseDTO.ReportItem = reportItemList[position]

        Glide.with(holder.itemView)
            .load(reportItem.reportImageDTO.image)
            .into(holder.img)

        holder.bug.text = reportItem.reportImageDTO.bug
        holder.time.text = reportItem.reportImageDTO.date.dateToString("yyyy-MM-dd HH:mm")

        holder.rvChecklist.adapter = CheckListRecyclerAdapter(reportItem.checkListDTO.checklist)
        holder.rvChecklist.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        holder.rvChecklist.setHasFixedSize(false)

        holder.rvDruglist.adapter = DrugListRecyclerAdapter(reportItem.drugListDTO.druglist)
        holder.rvDruglist.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        holder.rvDruglist.setHasFixedSize(false)

    }

    override fun getItemCount(): Int {
        return reportItemList.size
    }

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img : ImageView = itemView.findViewById(R.id.Item_Report_Img)
        var bug : TextView = itemView.findViewById(R.id.Item_Report_Value_Bug)
        var time : TextView = itemView.findViewById(R.id.Item_Report_Value_Time)
        var rvChecklist : RecyclerView = itemView.findViewById(R.id.Item_Report_RV_Checklist)
        var rvDruglist : RecyclerView = itemView.findViewById(R.id.Item_Report_RV_Drug)
    }

    fun Date.dateToString(format: String, local : Locale = Locale.getDefault()): String{
        val formatter = SimpleDateFormat(format, local)
        return formatter.format(this)
    }

}