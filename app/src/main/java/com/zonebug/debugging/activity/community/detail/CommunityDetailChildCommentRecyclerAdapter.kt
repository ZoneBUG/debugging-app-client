package com.zonebug.debugging.activity.community.detail

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zonebug.debugging.DTO.community.CommunityDetailDTO
import com.zonebug.debugging.DTO.community.CommunityListDTO
import com.zonebug.debugging.R
import com.zonebug.debugging.activity.community.detail.CommunityDetailActivity

class CommunityDetailChildCommentRecyclerAdapter(private val childCommentList : List<CommunityDetailDTO.Comment>)
    : RecyclerView.Adapter<CommunityDetailChildCommentRecyclerAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_community_comment_child, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val commentItem : CommunityDetailDTO.Comment = childCommentList[position]

//        if(commentItem.isMine) {
//            holder.editBtn.visibility = View.VISIBLE
//        } else {
//            holder.reportBtn.visibility = View.VISIBLE
//        }

        holder.contents.text = commentItem.contents
        holder.nickname.text = commentItem.nickname
    }

    override fun getItemCount(): Int {
        return childCommentList.size
    }


    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contents : TextView = itemView.findViewById(R.id.Item_Community_Detail_Child_Comment_Contents)
        var date : TextView = itemView.findViewById(R.id.Item_Community_Detail_Child_Comment_Date)
        var nickname : TextView = itemView.findViewById(R.id.Item_Community_Detail_Child_Nickname)

        var reportBtn : TextView = itemView.findViewById(R.id.Item_Community_Detail_Child_TextBtn_Report)
        var editBtn : TextView = itemView.findViewById(R.id.Item_Community_Detail_Child_TextBtn_Edit)
    }

}