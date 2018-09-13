package com.kadirkertis.domain.interactor.trending.model


class Repo(var id: Long,
           var name: String?,
           val fullName: String,
           val htmlUrl: String,
           val description: String?,
           val language:String?,
           val forksCount: Int,
           val imgUrl:String?)
