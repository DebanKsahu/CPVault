package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LanguageStats(
    val data: DataX? = null,
    val errors: List<Error>? = null,
    val matchedUser: MatchedUser
)