package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.Serializable

@Serializable
data class LeetCodeUserProfile(
    val about: String,
    val avatar: String,
    val birthday: String,
    val company: String?,
    val country: String,
    val gitHub: String?,
    val linkedIN: String?,
    val name: String,
    val ranking: Int,
    val reputation: Int,
    val school: String,
    val skillTags: List<String>,
    val twitter: String?,
    val username: String,
    val website: List<String>,
    val errors: List<ErrorDetail>? = null
)
@Serializable
data class ErrorDetail(
    val message: String,
    val locations: List<Location>,
    val path: List<String>,
    val extensions: Extensions
)