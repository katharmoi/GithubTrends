package com.kadirkertis.githubtrends.ui.trendings

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kadirkertis.domain.interactor.model.Repo
import com.kadirkertis.githubtrends.R
import com.kadirkertis.githubtrends.di.activity.ActivityScope
import com.kadirkertis.githubtrends.ui.details.DetailsActivity
import com.kadirkertis.githubtrends.util.Constants
import kotlinx.android.synthetic.main.list_item_repo.view.*
import javax.inject.Inject

/**
 * Repositories list adapter
 */
@ActivityScope
class TrendingsAdapter @Inject constructor() : RecyclerView.Adapter<TrendingsAdapter.TrendingsViewHolder>() {

    private var reposList: List<Repo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_repo, parent, false)

        return TrendingsViewHolder(itemView, parent.context)
    }

    override fun onBindViewHolder(holder: TrendingsViewHolder, position: Int) {
        holder.setItem(reposList[position])
    }

    override fun getItemCount(): Int {
        return reposList.size
    }

    fun onReposUptade(viewRepos: List<Repo>) {
        reposList = viewRepos
        notifyDataSetChanged()
    }

    class TrendingsViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {
        private var repo: Repo? = null

        fun setItem(repo: Repo) {
            this.repo = repo
            itemView.apply {
                detailRepoName.text = repo.name
                repoDescription.text = repo.description
                detailRepoForks.text = repo.forksCount.toString()
                repoLanguage.text = repo.language
                detailRepoStars.text = repo.starCount.toString()

            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailsActivity::class.java).apply {
                    putExtra(Constants.REPO_ID, repo.id)
                }
                itemView.context.startActivity(intent)
            }
        }

    }

}
