package com.zonebug.debugging.activity.community.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zonebug.debugging.DTO.community.CommunityMainDTO
import com.zonebug.debugging.R
import com.zonebug.debugging.activity.community.detail.CommunityDetailActivity

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

        if(mainPostItem.title.length > 20) holder.title.text = mainPostItem.title.substring(0, 20) + "..."
        else holder.title.text = mainPostItem.title

        var createdAt = mainPostItem.createdAt.time
        holder.time.text = formatTimeString(createdAt)

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

    // 몇분전, 방금 전
    private object DATE {
        const val SEC = 60
        const val MIN = 60
        const val HOUR = 24
        const val DAY = 30
        const val MONTH = 12
    }

    private fun formatTimeString(regTime: Long): String {
        val curTime = System.currentTimeMillis()
        var diffTime = (curTime - regTime) / 1000
        var msg: String = ""
        when {
            diffTime < DATE.SEC -> {
                msg = "방금 전"
            }
            DATE.SEC.let { diffTime /= it; diffTime } < DATE.MIN -> {
                msg = diffTime.toString() + "분 전"
            }
            DATE.MIN.let { diffTime /= it; diffTime } < DATE.HOUR -> {
                msg = diffTime.toString() + "시간 전"
            }
            DATE.HOUR.let { diffTime /= it; diffTime } < DATE.DAY -> {
                msg = diffTime.toString() + "일 전"
            }
            DATE.DAY.let { diffTime /= it; diffTime } < DATE.MONTH -> {
                msg = diffTime.toString() + "달 전"
            }
            else -> {
                msg = diffTime.toString() + "년 전"
            }
        }
        return msg
    }

}