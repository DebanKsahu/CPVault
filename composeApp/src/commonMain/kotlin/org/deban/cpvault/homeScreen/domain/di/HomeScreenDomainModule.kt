package org.deban.cpvault.homeScreen.domain.di

import org.deban.cpvault.homeScreen.domain.usecases.GetLeetcodeContestDetailUseCase
import org.deban.cpvault.homeScreen.domain.usecases.GetLeetcodeContestHistoryUseCase
import org.deban.cpvault.homeScreen.domain.usecases.GetLeetcodeFullProfileUseCase
import org.deban.cpvault.homeScreen.domain.usecases.GetLeetcodeProfileUseCase
import org.deban.cpvault.homeScreen.domain.usecases.GetLeetcodeUserLanguageStatsUseCase
import org.koin.dsl.module

fun getHomeScreenDomainModule() = module {
    factory {
        GetLeetcodeProfileUseCase(homeScreenRepository = get())
    }
    factory {
        GetLeetcodeContestHistoryUseCase(homeScreenRepository = get())
    }
    factory {
        GetLeetcodeContestDetailUseCase(homeScreenRepository = get())
    }
    factory {
        GetLeetcodeFullProfileUseCase(homeScreenRepository = get())
    }
    factory {
        GetLeetcodeUserLanguageStatsUseCase(homeScreenRepository = get())
    }
}