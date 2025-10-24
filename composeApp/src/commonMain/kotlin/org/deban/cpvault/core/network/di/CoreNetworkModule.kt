package org.deban.cpvault.core.network.di

import org.deban.cpvault.core.network.apiService.LeetCodeApiService
import org.deban.cpvault.core.network.client.LeetCodeClient
import org.koin.dsl.module

fun getCoreNetworkModule() = module {
    single { LeetCodeApiService(httpClient = LeetCodeClient.getInstance()) }
}