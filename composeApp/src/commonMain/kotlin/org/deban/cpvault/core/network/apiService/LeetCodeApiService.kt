package org.deban.cpvault.core.network.apiService

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.deban.cpvault.core.network.model.leetcode.LanguageStats
import org.deban.cpvault.core.network.model.leetcode.LeetCodeUserContestDetail
import org.deban.cpvault.core.network.model.leetcode.LeetCodeUserFullProfile
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

    suspend fun getUserContestDetail(username: String): Result<LeetCodeUserContestDetail> {
        return try {
            val responseText = httpClient.get("/userContestRankingInfo/$username").bodyAsText()
            val jsonObj = this.json.parseToJsonElement(responseText).jsonObject

            if (jsonObj.containsKey("error")) {
                val error = jsonObj["error"]?.toString() ?: "Some Error Occured"
                Result.failure(Exception("Api Error : $error"))
            } else {
                val userContestDetail = this.json.decodeFromJsonElement<LeetCodeUserContestDetail>(jsonObj)
                Result.success(userContestDetail)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserFullProfile(username: String): Result<LeetCodeUserFullProfile> {
        return try {
            val responseText = httpClient.get("/userProfile/$username").bodyAsText()
            val jsonObj = this.json.parseToJsonElement(responseText).jsonObject
            if (jsonObj.isNullOrEmpty()) {
                Result.failure(Exception("Some error occured"))
            } else {
                val userFullProfileDetail = this.json.decodeFromJsonElement<LeetCodeUserFullProfile>(jsonObj)
                Result.success(userFullProfileDetail)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserLanguageStats(username: String): Result<LanguageStats> {
        return try {
            val responseText = httpClient.get("/languageStats") {
                parameter("username",username)
            }.bodyAsText()
            val jsonObj = this.json.parseToJsonElement(responseText).jsonObject
            if (jsonObj.containsKey("errors")) {
                Result.failure(Exception("Invalid Username"))
            } else {
                val userLanguageStats = this.json.decodeFromJsonElement<LanguageStats>(jsonObj)
                Result.success(userLanguageStats)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}