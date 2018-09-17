package com.kadirkertis.domain.interactor.model

/**
 * User Repository model
 */
data class Repo(var id: Long,
           var name: String?,
           val fullName: String,
           val htmlUrl: String,
           val language:String?,
           val description: String?,
           val starCount: Int,
           val forksCount: Int,
           val owner:Owner
)
