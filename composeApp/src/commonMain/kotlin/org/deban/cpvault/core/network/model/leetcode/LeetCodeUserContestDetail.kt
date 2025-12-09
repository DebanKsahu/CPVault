package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeetCodeUserContestDetail(
    val data: Data? = null,
    val error: String? = null
)