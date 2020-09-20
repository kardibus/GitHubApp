package com.example.githubapp.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.githubapp.BR
import com.example.githubapp.R
import com.example.githubapp.databinding.ActivityUserBinding
import com.example.githubapp.di.interfaces.DaggerSearchComponent
import com.example.githubapp.di.interfaces.SearchComponent
import com.example.githubapp.di.module.ContextModule
import com.example.githubapp.models.UserApiResponse
import com.example.githubapp.presenters.UserPresenter
import com.example.githubapp.views.UserView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity: MvpActivity(),UserView {
    @InjectPresenter
    lateinit var userPresenter: UserPresenter

    lateinit var binding:ActivityUserBinding

    lateinit var searchComponent: SearchComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= DataBindingUtil.setContentView(this,R.layout.activity_user)

        var intentStartedThisActivity = intent

        var name = intentStartedThisActivity.getStringExtra("name")

        searchComponent = DaggerSearchComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()

           userPresenter.loadUser(name,this,searchComponent.check().isOnline())

        }


    override fun setupUser(userList: UserApiResponse) {

        binding.setVariable(BR.user,userList)
        binding.executePendingBindings()

        userList.avatar_url?.let { url ->
            Picasso.get().load(url)
                .into(user_iv_avatar)
        }
    }

}

