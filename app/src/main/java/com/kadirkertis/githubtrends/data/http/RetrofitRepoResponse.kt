package com.kadirkertis.githubtrends.data.http

import com.google.gson.annotations.SerializedName
import com.kadirkertis.githubtrends.data.model.RepoData

/**
 * Model for holding Retrofit Response
 */
data class RetrofitRepoResponse(
        @SerializedName("total_count") val total: Int = 0,
        @SerializedName("items") val items: List<RepoData> = emptyList(),
        val nextPage: Int? = null
)