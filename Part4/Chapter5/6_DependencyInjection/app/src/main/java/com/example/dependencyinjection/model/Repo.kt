package com.example.dependencyinjection.model

import javax.inject.Inject

data class Repo @Inject constructor(
    val id: Long,
    val name: String,
    val htmlUrl: String,
    val url: String,
    val gitUrl: String
)