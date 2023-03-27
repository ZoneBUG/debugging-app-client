package com.zonebug.debugging.activity.community.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zonebug.debugging.DTO.community.CommunityMainDTO
import com.zonebug.debugging.R
import com.zonebug.debugging.activity.community.CommunityDetailActivity
import java.util.*

class CommunityRecyclerAdapter(private val mainPostList: List<CommunityMainDTO.MainPost>)
    : RecyclerView.Adapter<CommunityRecyclerAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_community_main, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val mainPostItem : CommunityMainDTO.MainPost = mainPostList[position]
        Log.d("tag", "=============================== " + mainPostItem.title.toString())
        if(mainPostItem.title.length > 20) holder.title.text = mainPostItem.title.substring(0, 20) + "..."
        else holder.title.text = mainPostItem.title

        // var now : Date = Date()
        holder.time.text = position.toString() + "분전"

        holder.itemView.setOnClickListener {
            var intent = Intent(holder.itemView.context!!, CommunityDetailActivity::class.java)
            intent.putExtra("postId", mainPostItem.postId)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return mainPostList.size
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var time : TextView = itemView.findViewById(R.id.Item_Community_Main_Time)
        var title : TextView = itemView.findViewById(R.id.Item_Community_Main_Title)

    }

}