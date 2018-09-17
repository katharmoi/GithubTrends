package com.kadirkertis.githubtrends.ui.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import com.kadirkertis.domain.interactor.model.Repo
import com.kadirkertis.githubtrends.R
import com.kadirkertis.githubtrends.util.Constants
import com.kadirkertis.githubtrends.util.Response
import com.kadirkertis.githubtrends.util.Status
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: DetailsActivityViewModelFactory

    private lateinit var viewModel: DetailsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //initiate view model
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DetailsActivityViewModel::class.java)

        //Observe Data
        viewModel.response.observe(this, Observer<Response<Repo>> { this.parseResponse(it) })
        val id: Long = intent.getLongExtra(Constants.REPO_ID, 0);
        viewModel.loadDetails(id.toInt())

    }

    private fun parseResponse(response: Response<Repo>?) {
        when (response?.status) {
            Status.LOADING -> Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show()
            Status.SUCCESS -> initializeScreen(response.data)
            Status.ERROR -> Toast.makeText(this, response.error.toString(), Toast.LENGTH_SHORT).show()
        }
    }


    internal fun initializeScreen(repo: Repo?) {
        detailRepoForks.text = repo?.forksCount.toString()
        detailRepoStars.text = repo?.starCount.toString()
        detailRepoName.text = repo?.name
        detailRepoDesc.text = repo?.description
        detailRepoUrl.text = repo?.htmlUrl
        val avatarUrl: String? = repo?.owner?.avatarUrl
        Picasso.get()
                .load(avatarUrl)
                .placeholder(R.drawable.no_img)
                .error(R.drawable.no_img)
                .into(detailsRepoImg)

    }
}
