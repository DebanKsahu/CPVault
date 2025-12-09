package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LanguageProblemCount(
    val languageName: String,
    val problemsSolved: Int
)