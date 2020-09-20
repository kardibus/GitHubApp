package com.example.githubapp.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.githubapp.R
import com.example.githubapp.adapters.SearchAdapter
import com.example.githubapp.adapters.SearchAdapterClicks
import com.example.githubapp.di.interfaces.DaggerSearchComponent
import com.example.githubapp.di.interfaces.SearchComponent
import com.example.githubapp.di.module.ContextModule
import com.example.githubapp.models.UsersApiResponse
import com.example.githubapp.presenters.SearchPresenter
import com.example.githubapp.views.SearchView
import com.github.rahatarmanahmed.cpv.CircularProgressView
import kotlinx.android.synthetic.main.activity_main.*

class SearchActivity : MvpActivity(),SearchView {

    @InjectPresenter
    lateinit var searchPresenter: SearchPresenter

    lateinit var searchComponent: SearchComponent



    private lateinit var mRvFriends: RecyclerView
    private lateinit var mTxtNoItems: TextView
    private lateinit var mCpvWait: CircularProgressView
    private lateinit var mAdapter: SearchAdapter
    private lateinit var mBtmSearch:Button
    private lateinit var mTxtSearch:EditText
    var page:Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRvFriends = findViewById(R.id.recycler_user)
        mTxtNoItems = findViewById(R.id.txt_user_no_item)
        mCpvWait = findViewById(R.id.cpv_users)
        mBtmSearch = findViewById(R.id.serarch_btm_user)
        mTxtSearch = findViewById(R.id.txt_user_search)

        searchComponent = DaggerSearchComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()

            mBtmSearch.setOnClickListener {
                searchPresenter.loadSearch(
                    mTxtSearch.text.toString(),
                    page = page,
                    context = this,
                    boolean = searchComponent.check().isOnline())
        }



        mAdapter = SearchAdapter()

        mAdapter.searchAdapterClicks = object: SearchAdapterClicks{
            override fun onItemClick(model: UsersApiResponse) {
                     onUserClick(userApiResponse = model)
            }
        }

        recycler_user.adapter = mAdapter
        mRvFriends.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        mRvFriends.setHasFixedSize(true)
    }

    override fun showError(textResource: Int) {
        mTxtNoItems.text = getString(textResource)
    }

    override fun setupEmptyList() {
        mRvFriends.visibility=View.GONE
        mTxtNoItems.visibility = View.VISIBLE
    }

    override fun setupFriendsList(userList: ArrayList<UsersApiResponse>) {
        mRvFriends.visibility = View.VISIBLE
        mTxtNoItems.visibility = View.GONE

        mAdapter.setupUsers(userList = userList)
    }


    override fun startLoading() {
        mRvFriends.visibility = View.GONE
        mTxtNoItems.visibility = View.GONE
        mCpvWait.visibility = View.VISIBLE
    }

    override fun endLoading() {
        mCpvWait.visibility = View.GONE
    }

    private fun onUserClick(userApiResponse: UsersApiResponse) {
        val showActivity = Intent(this,UserActivity::class.java)
        showActivity.putExtra("name", userApiResponse.login)
        startActivity(showActivity)
    }

    fun more(view: View){
        if(!mTxtSearch.text.isEmpty()){
            ++page
            searchPresenter.loadSearch(
                mTxtSearch.text.toString(),
                page = page,
                context = this,
                boolean = searchComponent.check().isOnline()
            )
            mAdapter.notifyDataSetChanged()
        }
    }

    fun back(view: View){
        if(!mTxtSearch.text.isEmpty()){
            --page
            searchPresenter.loadSearch(
                mTxtSearch.text.toString(),
                page = page,
                context = this,
                boolean = searchComponent.check().isOnline())
        }
            mAdapter.notifyDataSetChanged()
        }
    }

