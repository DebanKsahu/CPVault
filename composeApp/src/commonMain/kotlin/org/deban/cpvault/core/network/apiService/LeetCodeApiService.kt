package org.deban.cpvault.core.network.apiService

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.deban.cpvault.core.network.model.leetcode.LeetCodeUserProfile

class LeetCodeApiService(
    private val httpClient: HttpClient
) {
    private val json = Json {
        ignoreUnknownKeys = true
    }
    suspend fun getUserProfile(username: String): Result<LeetCodeUserProfile> {
        return try {
            val responseText = httpClient.get("/$username").bodyAsText()
            val jsonObj = this.json.parseToJsonElement(responseText).jsonObject

            if (jsonObj.containsKey("errors")) {
                val errors = jsonObj["errors"]?.jsonArray?.joinToString { it.jsonObject["message"]!!.jsonPrimitive.content }
                Result.failure(Exception("Api Error : $errors"))
            } else {
                val userProfileInfo = this.json.decodeFromJsonElement<LeetCodeUserProfile>(jsonObj)
                Result.success(userProfileInfo)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}