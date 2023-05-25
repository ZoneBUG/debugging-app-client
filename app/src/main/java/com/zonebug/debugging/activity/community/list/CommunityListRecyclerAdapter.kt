package com.zonebug.debugging.activity.community.list

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zonebug.debugging.DTO.community.CommunityListDTO
import com.zonebug.debugging.R
import com.zonebug.debugging.activity.community.detail.CommunityDetailActivity
import java.text.SimpleDateFormat
import java.util.*

class CommunityListRecyclerAdapter(private val postList : List<CommunityListDTO.ListPost>)
    : RecyclerView.Adapter<CommunityListRecyclerAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_community_list, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val postItem : CommunityListDTO.ListPost = postList[position]

        if(postItem.title.length > 20) holder.title.text = postItem.title.substring(0, 20) + "..."
        else holder.title.text = postItem.title

        var createdAt = postItem.createdAt.time
        holder.date.text = formatTimeString(createdAt)

        holder.nickname.text = postItem.nickname

        Log.d("TAG", "............................. " + (postItem.createdAt).toString().substring(4, 10))

        holder.itemView.setOnClickListener {
            var intent = Intent(holder.itemView.context!!, CommunityDetailActivity::class.java)
            intent.putExtra("postId", postItem.postId)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView = itemView.findViewById(R.id.Item_Community_List_Title)
        var date : TextView = itemView.findViewById(R.id.Item_Community_List_Date)
        var nickname : TextView = itemView.findViewById(R.id.Item_Community_List_Nickname)
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