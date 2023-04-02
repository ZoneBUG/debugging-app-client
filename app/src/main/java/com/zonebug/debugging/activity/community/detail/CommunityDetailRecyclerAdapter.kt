package com.zonebug.debugging.activity.community.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zonebug.debugging.R

class CommunityDetailRecyclerAdapter(private val commentList : MutableList<CommentDetail>, private val context : Context)
    : RecyclerView.Adapter<CommunityDetailRecyclerAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_community_comment, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val commentItem : CommentDetail= commentList[position]
        holder.childCommentRV.adapter = CommunityDetailChildCommentRecyclerAdapter(commentItem.childCommentList)
        holder.childCommentRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        if(commentItem.comment.isMine) {
            holder.editBtn.visibility = View.VISIBLE
        } else {
            holder.reportBtn.visibility = View.VISIBLE
        }

        holder.contents.text = commentItem.comment.contents
        holder.nickname.text = commentItem.comment.nickname

        holder.writeChildBtn.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contents : TextView = itemView.findViewById(R.id.Item_Community_Detail_Comment_Contents)
        var date : TextView = itemView.findViewById(R.id.Item_Community_Detail_Comment_Date)
        var nickname : TextView = itemView.findViewById(R.id.Item_Community_Detail_Nickname)

        var writeChildBtn : TextView = itemView.findViewById(R.id.Item_Community_Detail_TextBtn_Child)
        var reportBtn : TextView = itemView.findViewById(R.id.Item_Community_Detail_TextBtn_Report)
        var editBtn : TextView = itemView.findViewById(R.id.Item_Community_Detail_TextBtn_Edit)

        var childCommentRV : RecyclerView = itemView.findViewById(R.id.Community_Detail_RV_ChildComment)
    }

}