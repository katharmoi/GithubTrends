package com.kadirkertis.githubtrends.data.model

import com.google.gson.annotations.SerializedName


/**
 * Github repository model which holdds information about a repository on Github.
 *
 */
data class RepoData(
        @field:SerializedName("id") val id: Long,
        @field:SerializedName("name") val name: String,
        @field:SerializedName("full_name") val fullName: String,
        @field:SerializedName("html_url") val url: String,
        @field:SerializedName("language") val language: String?,
        @field:SerializedName("description") val description: String?,
        @field:SerializedName("stargazers_count") val stars: Int,
        @field:SerializedName("forks_count") val forks: Int,
        @field:SerializedName("owner") val owner: Owner

)