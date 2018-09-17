package com.kadirkertis.githubtrends.data.model

import com.google.gson.annotations.SerializedName

/**
 * Owner data model
 */
data class Owner (
        @field:SerializedName("id") val id: Long,
        @field:SerializedName("avatar_url") val avatarUrl: String
)