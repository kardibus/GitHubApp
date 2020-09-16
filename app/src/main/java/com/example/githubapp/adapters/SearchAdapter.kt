package com.example.githubapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.models.UsersApiResponse
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

interface SearchAdapterClicks{
   fun onItemClick(model:UsersApiResponse)
}

class SearchAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mSourceList: ArrayList<UsersApiResponse> = ArrayList()
    private var mUsersList: ArrayList<UsersApiResponse> = ArrayList()

    var searchAdapterClicks:SearchAdapterClicks? = null

    fun setupUsers(userList: ArrayList<UsersApiResponse>){
        mSourceList.clear()
        mSourceList.addAll(userList)
        filter(query = "")
    }

    fun filter(query: String) {
        mUsersList.clear()
        mSourceList.forEach {
            if (it.login.contains(query, ignoreCase = true) || it.login.contains(query, ignoreCase = true)) {
                mUsersList.add(it)
            } else {
                it.avatar_url?.let { city -> if (city.contains(query, ignoreCase = true)) {
                    mUsersList.add(it)
                }}
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val itemView = layoutInflater.inflate(R.layout.call_user, parent, false)
        return UsersViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {
        return mUsersList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is UsersViewHolder) {
            holder.bind(usersModel = mUsersList[position])
            holder.itemView.setOnClickListener{searchAdapterClicks?.onItemClick(mUsersList[position])}
        }
    }


    class UsersViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {

        private var mCivAvatar: CircleImageView = itemView.findViewById(R.id.user_civ_avatar)
        private var mTxtUsername: TextView = itemView.findViewById(R.id.user_txt_username)
        private var mTxtUrl: TextView = itemView.findViewById(R.id.user_txt_url)
        private var mImgOnline: View = itemView.findViewById(R.id.user_img_online)

        @SuppressLint("SetTextI18n")
        fun bind(usersModel: UsersApiResponse) {

            usersModel.avatar_url?.let { url ->
                Picasso.get().load(url)
                    .into(mCivAvatar)
            }

            mTxtUsername.text = "${usersModel.login}"
            mTxtUrl.text="${usersModel.html_url}"
        }
    }
}

