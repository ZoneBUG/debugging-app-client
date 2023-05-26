package com.zonebug.debugging.activity.community.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zonebug.debugging.R
import java.text.SimpleDateFormat

class CommunityDetailRecyclerAdapter(private val commentList : MutableList<CommentDetail>, private val context : Context, private val view: View)
    : RecyclerView.Adapter<CommunityDetailRecyclerAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_community_comment, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val commentItem : CommentDetail = commentList[position]
        val dateFormat = "yyyy-MM-dd"

        holder.childCommentRV.adapter = CommunityDetailChildCommentRecyclerAdapter(commentItem.childCommentList)
        holder.childCommentRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

//        if(commentItem.comment.isMine) {
//            holder.editBtn.visibility = View.VISIBLE
//        } else {
//            holder.reportBtn.visibility = View.VISIBLE
//        }

        holder.date.text = SimpleDateFormat(dateFormat).format(commentItem.comment.createdAt)
        holder.contents.text = commentItem.comment.contents
        holder.nickname.text = commentItem.comment.nickname

        holder.writeChildBtn.setOnClickListener {
            view.findViewById<EditText>(R.id.Community_Detail_Comment_Input).hint= "답글을 입력해주세요"

            var button = view.findViewById<AppCompatButton>(R.id.Community_Detail_Comment_Btn)
            button.setOnClickListener {
                if(button.text.toString().trim().isBlank()) {
                    Toast.makeText(context, "답글을 입력해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    (context as CommunityDetailActivity).writeComment(commentItem.postId, commentItem.comment.commentId)
                }
            }

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