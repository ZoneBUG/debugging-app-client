package com.zonebug.debugging.activity.community.adapter

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
import com.zonebug.debugging.activity.community.CommunityDetailActivity

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

        // var now : Date = Date()
        holder.date.text = (postItem.createdAt).toString().substring(4, 10)

        holder.nickname.text = "닉네임"

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

}