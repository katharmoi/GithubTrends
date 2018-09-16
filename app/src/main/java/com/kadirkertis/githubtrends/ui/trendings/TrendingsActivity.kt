package com.kadirkertis.githubtrends.ui.trendings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.kadirkertis.domain.interactor.model.Repo
import com.kadirkertis.githubtrends.R
import com.kadirkertis.githubtrends.util.Response
import com.kadirkertis.githubtrends.util.Status
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class TrendingsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: TrendingsActivityViewModelFactory

    @Inject
    lateinit var adapter: TrendingsAdapter

    private lateinit var viewModel: TrendingsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initiate view model
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TrendingsActivityViewModel::class.java)

        // Add Dividers
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)
        //Listen to the recycler scroll
        startScrollListener()

        //Assign RecyclerView Adapter
        list.adapter = adapter



        //Observe Data
        viewModel.response.observe(this, Observer<Response<List<Repo>>> { this.parseResponse(it) })
        viewModel.loadTrendingRepos()


    }

    private fun parseResponse(response: Response<List<Repo>>?) {
        when (response?.status) {
            Status.LOADING -> Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show()
            Status.SUCCESS -> displaySuccessState(response.data)
            Status.ERROR -> Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displaySuccessState(repos: List<Repo>?) {
        if (repos != null) {
            adapter.onReposUptade(repos)
        }
    }

    private fun startScrollListener() {
        val layoutManager = list.layoutManager as LinearLayoutManager
        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                viewModel.scrollListener(visibleItemCount,lastVisibleItem,totalItemCount)
            }
        })
    }


}
