package com.kadirkertis.githubtrends.ui.trendings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import com.kadirkertis.domain.interactor.trending.model.Repo
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

        //Assign RecyclerView Adapter
        list.adapter = adapter

        //Observe Data
        viewModel.response.observe(this, Observer<Response<List<Repo>>> { this.parseResponse(it) })
        viewModel.loadTrendingRepos()

    }

    private fun parseResponse(response: Response<List<Repo>>?) {
        when (response?.status) { //TODO look this ?
            Status.LOADING -> displayLoadingState()
            Status.SUCCESS -> displaySuccessState(response.data)
            Status.ERROR -> displayErrorState(response.error)
        }
    }

    private fun displayErrorState(error: Throwable?) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
    }

    private fun displaySuccessState(repos: List<Repo>?) {
        if (repos != null) {
            adapter.onReposUptade(repos)
        }
    }

    private fun displayLoadingState() {
        Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show()

    }
}
