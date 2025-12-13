package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AcSubmissionNum(
    val count: Int,
    val difficulty: String,
    val submissions: Int
)