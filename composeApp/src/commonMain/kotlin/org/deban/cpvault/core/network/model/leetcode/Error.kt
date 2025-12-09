package org.deban.cpvault.core.network.model.leetcode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Error(
    val extensions: Extensions,
    val locations: List<Location>,
    val message: String,
    val path: List<String>
)