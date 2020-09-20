package com.example.githubapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.databinding.CallUserBinding
import com.example.githubapp.di.interfaces.DaggerSearchComponent
import com.example.githubapp.di.interfaces.SearchComponent
import com.example.githubapp.models.UsersApiResponse
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class SearchAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mUsersList: ArrayList<UsersApiResponse> = ArrayList()

    lateinit var binding:CallUserBinding

    var searchAdapterClicks:SearchAdapterClicks? = null

    lateinit var searchComponent: SearchComponent

    fun setupUsers(userList: ArrayList<UsersApiResponse>){
        mUsersList.addAll(userList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.call_user, parent, false)
        return UsersViewHolder(binding)
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

    class UsersViewHolder(val binding: CallUserBinding): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(usersModel: UsersApiResponse) {

            Picasso.get().load(usersModel.avatar_url)
                .into(binding.userCivAvatar)

            binding.setVariable(BR.searchUser,usersModel)
            binding.executePendingBindings()
        }
    }
}

