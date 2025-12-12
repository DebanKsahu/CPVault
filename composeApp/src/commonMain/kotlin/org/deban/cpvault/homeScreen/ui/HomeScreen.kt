package org.deban.cpvault.homeScreen.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import cpvault.composeapp.generated.resources.BebasNeue_Regular
import cpvault.composeapp.generated.resources.Boogaloo_Regular
import cpvault.composeapp.generated.resources.Res
import cpvault.composeapp.generated.resources.RoadRage_Regular
import cpvault.composeapp.generated.resources.compose_multiplatform
import org.deban.cpvault.easyLeetcodeQuestionBoxGradient
import org.deban.cpvault.easyLeetcodeQuestionCircleGradient
import org.deban.cpvault.easyLeetcodeQuestionTextGradient
import org.deban.cpvault.gradientBrush
import org.deban.cpvault.hardLeetcodeQuestionBoxGradient
import org.deban.cpvault.hardLeetcodeQuestionCircleGradient
import org.deban.cpvault.hardLeetcodeQuestionTextGradient
import org.deban.cpvault.homeBackgroundBrush
import org.deban.cpvault.homeScreen.domain.model.ContestStats
import org.deban.cpvault.homeScreen.domain.model.DomainLanguageStats
import org.deban.cpvault.languageStatsBackgroundGradient
import org.deban.cpvault.leetcodeContestStatsBackgroundGradient
import org.deban.cpvault.leetcodeRatingCardGradient
import org.deban.cpvault.leetcodeRatingTextGradient
import org.deban.cpvault.mediumLeetcodeQuestionBoxGradient
import org.deban.cpvault.mediumLeetcodeQuestionCircleGradient
import org.deban.cpvault.mediumLeetcodeQuestionTextGradient
import org.deban.cpvault.otherRankLanguageStatGradient
import org.deban.cpvault.rankOneLanguageStatGradient
import org.deban.cpvault.rankThreeLanguageStatGradient
import org.deban.cpvault.rankTwoLanguageStatGradient
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<HomeScreenViewModel>()
    val leetcodeProfileUiState = viewModel.leetcodeProfileUiState.collectAsStateWithLifecycle()
    val leetcodeContestHistoryUiState = viewModel.leetcodeContestHistoryUiState.collectAsStateWithLifecycle()
    val leetcodeContestDetailUiState = viewModel.leetcodeContestDetailUiState.collectAsStateWithLifecycle()
    val leetcodeFullProfileUiState = viewModel.leetcodeFullProfileUiState.collectAsStateWithLifecycle()
    val leetcodeLanguageStatsUiState = viewModel.leetcodeLanguageStatsUiState.collectAsStateWithLifecycle()
    HomeScreenContent(
        leetcodeProfileUiState = leetcodeProfileUiState.value,
        leetcodeContestDetailUiState = leetcodeContestDetailUiState.value,
        leetcodeContestHistoryUiState = leetcodeContestHistoryUiState.value,
        leetcodeFullProfileUiState = leetcodeFullProfileUiState.value,
        leetcodeLanguageStatsUiState = leetcodeLanguageStatsUiState.value
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    leetcodeProfileUiState: HomeScreen.LeetcodeProfileUiState,
    leetcodeContestHistoryUiState: HomeScreen.LeetcodeContestHistoryUiState,
    leetcodeContestDetailUiState: HomeScreen.LeetcodeContestDetailUiState,
    leetcodeFullProfileUiState: HomeScreen.LeetcodeFullProfileUiState,
    leetcodeLanguageStatsUiState: HomeScreen.LeetcodeLanguageStatsUiState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(homeBackgroundBrush)
            .padding(horizontal = 20.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            item {
                Spacer(modifier = Modifier.height(15.dp))
                if (leetcodeProfileUiState.isLoading || leetcodeContestDetailUiState.isLoading) {
                    CircularProgressIndicator()
                } else if (leetcodeProfileUiState.data !=null && leetcodeProfileUiState.error.isBlank() && leetcodeContestDetailUiState.data !=null && leetcodeContestDetailUiState.error.isBlank()) {
                    HomeScreenProfileUi(
                        avatarUrl = leetcodeProfileUiState.data.avatar,
                        fullName = leetcodeProfileUiState.data.name,
                        username = leetcodeProfileUiState.data.username,
                        userRank = leetcodeProfileUiState.data.ranking.toString(),
                        contestRating = leetcodeContestDetailUiState.data.data?.userContestRanking?.rating?.toString() ?: "0"
                    )
                } else {
                    Text(text = leetcodeProfileUiState.error)
                }
            }

            item {
                Spacer(modifier = Modifier.height(2.dp))
                if (leetcodeFullProfileUiState.isLoading) {
                    CircularProgressIndicator()
                } else if (leetcodeFullProfileUiState.data !=null && leetcodeFullProfileUiState.error.isBlank()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        HomeScreenQuestionStatsUi(
                            numberOfQuestion = leetcodeFullProfileUiState.data.hardSolved.toString(),
                            boxGradientColor = hardLeetcodeQuestionBoxGradient,
                            circleGradientColor = hardLeetcodeQuestionCircleGradient,
                            textGradientColor = hardLeetcodeQuestionTextGradient
                        )
                        HomeScreenQuestionStatsUi(
                            numberOfQuestion = leetcodeFullProfileUiState.data.mediumSolved.toString(),
                            boxGradientColor = mediumLeetcodeQuestionBoxGradient,
                            circleGradientColor = mediumLeetcodeQuestionCircleGradient,
                            textGradientColor = mediumLeetcodeQuestionTextGradient
                        )
                        HomeScreenQuestionStatsUi(
                            numberOfQuestion = leetcodeFullProfileUiState.data.easySolved.toString(),
                            boxGradientColor = easyLeetcodeQuestionBoxGradient,
                            circleGradientColor = easyLeetcodeQuestionCircleGradient,
                            textGradientColor = easyLeetcodeQuestionTextGradient
                        )
                    }
                } else {
                    Text(text = leetcodeFullProfileUiState.error)
                }
            }

            item {
                Spacer(modifier = Modifier.height(2.dp))
                if (leetcodeContestHistoryUiState.isLoading) {
                    CircularProgressIndicator()
                } else if (leetcodeContestHistoryUiState.data !=null && leetcodeContestHistoryUiState.error.isBlank()) {
                    HomeScreenContestHistoryUi(leetcodeContestHistoryUiState.data)
                } else {
                    Text(text = leetcodeContestHistoryUiState.error)
                }

            }
        }
    }
}

@Composable
fun HomeScreenProfileUi(
    avatarUrl: String? = null,
    fullName: String,
    username: String,
    contestRating: String,
    userRank: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier
            .width(364.dp)
            .height(348.dp)
            .padding(horizontal = 4.dp, vertical = 8.dp),
        shape = RoundedCornerShape(50.dp),
        color = Color.Transparent,
        tonalElevation = 10.dp,
        shadowElevation = 10.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradientBrush),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .size(120.dp)
                        .offset(y = 10.dp),
                    shape = CircleShape,
                    color = Color.Transparent,
                    tonalElevation = 8.dp,
                    shadowElevation = 10.dp,
                ) {
                    if (avatarUrl != null) {
                        AsyncImage(
                            model = avatarUrl,
                            contentDescription = "LeetcodeProfilePic",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painter = painterResource(Res.drawable.compose_multiplatform),
                            contentDescription = "DefaultLeetcodeProfilePic",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = fullName,
                    fontSize = 24.sp,
                    color = Color.White,
                    fontFamily = FontFamily(Font(Res.font.Boogaloo_Regular))

                )

                Text(
                    text = "@$username",
                    fontSize = 23.sp,
                    color = Color.White,
                    fontFamily = FontFamily(Font(Res.font.Boogaloo_Regular))
                )

                Surface(
                    modifier = Modifier
                        .offset(y = 8.dp)
                        .width(336.dp)
                        .height(99.dp)
                        .padding(10.dp),
                    shape = RoundedCornerShape(25.dp),
                    color = Color.Transparent,
                    tonalElevation = 10.dp,
                    shadowElevation = 4.dp
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(brush = leetcodeRatingCardGradient)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(90.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 50.dp, vertical = 8.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "Rating",
                                    color = Color.White,
                                    fontSize = 23.sp,
                                    fontFamily = FontFamily(Font(Res.font.Boogaloo_Regular))
                                )

                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(brush = leetcodeRatingTextGradient)
                                        ) {
                                            append(contestRating)
                                        }
                                    },
                                    fontSize = 33.sp,
                                    fontFamily = FontFamily(Font(Res.font.BebasNeue_Regular))
                                )

                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "Rank",
                                    color = Color.White,
                                    fontSize = 23.sp,
                                    fontFamily = FontFamily(Font(Res.font.Boogaloo_Regular))
                                )
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(brush = leetcodeRatingTextGradient)
                                        ) {
                                            append(userRank)
                                        }
                                    },
                                    fontSize = 33.sp,
                                    fontFamily = FontFamily(Font(Res.font.BebasNeue_Regular))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreenQuestionStatsUi(
    numberOfQuestion: String,
    boxGradientColor: Brush,
    circleGradientColor: Brush,
    textGradientColor: Brush
) {
    Surface(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        shape = RoundedCornerShape(25.dp),
        color = Color.Transparent,
        tonalElevation = 8.dp,
        shadowElevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(boxGradientColor),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier.size(80.dp)
            ) {
                val stroke = Stroke(width = 3.dp.toPx())
                drawCircle(
                    brush = circleGradientColor,
                    radius = (size.minDimension / 2f) - (stroke.width / 2f),
                    center = center,
                    style = stroke
                )
            }
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(brush = textGradientColor)
                    ) {
                        append(numberOfQuestion)
                    }
                },
                fontSize = 38.sp,
                fontFamily = FontFamily(Font(Res.font.RoadRage_Regular))
            )
        }
    }
}

@Composable
fun HomeScreenContestHistoryUi(
    contestStatsList: List<ContestStats>
) {
    Box(
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Context History",
                modifier = Modifier.offset(x = (-100).dp),
                color = Color.White,
                fontSize = 33.sp,
                fontFamily = FontFamily(Font(Res.font.RoadRage_Regular))
            )
            LazyRow(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                itemsIndexed(contestStatsList) {index, contestStats ->
                    var prevContestRating = 0
                    if (index != 0) {
                        prevContestRating = contestStatsList[index-1].newRating
                    }
                    ContestStatsUi(
                        contestName = contestStats.contestName,
                        questionSolved = contestStats.problemSolved.toString(),
                        rank = contestStats.rank.toString(),
                        rating = contestStats.newRating.toString(),
                        previousRating = prevContestRating.toString(),
                        trendDirection = contestStats.treadDirection
                    )
                }
            }
        }
    }
}

@Composable
fun ContestStatsUi(
    contestName: String,
    questionSolved: String,
    rank: String,
    rating: String,
    previousRating: String,
    trendDirection: String
) {
    Surface(
        modifier = Modifier
            .width(250.dp)
            .height(130.dp),
        shape = RoundedCornerShape(25.dp),
        color = Color.Transparent,
        shadowElevation = 8.dp,
        tonalElevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = leetcodeContestStatsBackgroundGradient)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = contestName,
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(Res.font.BebasNeue_Regular)),
                    modifier = Modifier.offset(x = 16.dp, y = 8.dp),
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "${questionSolved}/4",
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(Res.font.BebasNeue_Regular)),
                        modifier = Modifier.offset(x = 16.dp, y = 6.dp),
                    )
                    Text(
                        text = rank,
                        fontSize = 30.sp,
                        fontFamily = FontFamily(Font(Res.font.BebasNeue_Regular)),
                        modifier = Modifier.offset(x = 132.dp, y = 6.dp),
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = rating,
                        fontSize = 33.sp,
                        fontFamily = FontFamily(Font(Res.font.BebasNeue_Regular)),
                        modifier = Modifier.offset(x = 16.dp, y = 4.dp),
                        color = Color(220,85,238)
                    )
                    if (trendDirection == "UP") {
                        Box(
                            modifier = Modifier
                                .offset(x = 20.dp, y = 8.dp)
                                .width(55.dp)
                                .height(30.dp)
                                .background(Color(223,255,226), shape = RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "+${rating.toInt()-previousRating.toInt()}",
                                fontSize = 25.sp,
                                fontFamily = FontFamily(Font(Res.font.BebasNeue_Regular)),
                                color = Color(17,255,0)
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .offset(x = 20.dp, y = 8.dp)
                                .width(55.dp)
                                .height(30.dp)
                                .background(Color(255,224,224), shape = RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "-${previousRating.toInt()-rating.toInt()}",
                                fontSize = 25.sp,
                                fontFamily = FontFamily(Font(Res.font.BebasNeue_Regular)),
                                color = Color(213,0,0)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LanguagesStatsUi(
    languageStats: List<DomainLanguageStats> = emptyList()
) {
    Surface(
        modifier = Modifier
            .width(364.dp)
            .height(348.dp)
            .padding(horizontal = 4.dp, vertical = 8.dp),
        shape = RoundedCornerShape(50.dp),
        color = Color.Transparent,
        tonalElevation = 10.dp,
        shadowElevation = 10.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = languageStatsBackgroundGradient),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(languageStats) { index, languageDetail->
                    if (index==0) {
                        LanguageItem(
                            languageDetail = languageDetail,
                            colorGradient = rankOneLanguageStatGradient
                        )
                    } else if (index==1) {
                        LanguageItem(
                            languageDetail = languageDetail,
                            colorGradient = rankTwoLanguageStatGradient
                        )
                    } else if (index==2) {
                        LanguageItem(
                            languageDetail = languageDetail,
                            colorGradient = rankThreeLanguageStatGradient
                        )
                    } else {
                        LanguageItem(
                            languageDetail = languageDetail,
                            colorGradient = otherRankLanguageStatGradient
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun CustomLinearIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 4.dp,
    trackColor: Color = Color.LightGray,
    progressColor: Color = Color.Blue,
    strokeCap: StrokeCap = StrokeCap.Round
) {
    Canvas(
        modifier
            .fillMaxWidth()
            .height(strokeWidth)
    ) {
        val centerY = size.height / 2

        drawLine(
            color = trackColor,
            start = Offset(0f, centerY),
            end = Offset(size.width, centerY),
            strokeWidth = strokeWidth.toPx(),
            cap = strokeCap
        )

        drawLine(
            color = progressColor,
            start = Offset(0f, centerY),
            end = Offset(size.width * progress, centerY),
            strokeWidth = strokeWidth.toPx(),
            cap = strokeCap
        )
    }
}

@Composable
fun LanguageItem(
    languageDetail: DomainLanguageStats,
    colorGradient: Brush
) {
    Surface(
        modifier = Modifier
            .offset(y = 8.dp)
            .width(336.dp)
            .height(99.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(25.dp),
        color = Color.Transparent,
        tonalElevation = 10.dp,
        shadowElevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = colorGradient,)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(90.dp),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = languageDetail.name,
                        color = Color.Black,
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(Res.font.BebasNeue_Regular))
                    )

                    Text(
                        text = languageDetail.solvedQuestion.toString(),
                        color = Color.Black,
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(Res.font.BebasNeue_Regular))
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                CustomLinearIndicator(progress = languageDetail.percentage.toFloat())
            }
        }
    }
}


//@Preview
//@Composable
//fun HomeScreenPreview() {
//    val previewLeetcodeUserProfile = LeetCodeUserProfile(
//        about = "Hi, it'ss me Deban",
//        avatar = "",
//        birthday = "",
//        company = "",
//        country = "India",
//        gitHub = "",
//        linkedIN = "",
//        name = "Deban Kumar Sahu",
//        ranking = 42110,
//        reputation = 25,
//        school = "IIIT Bhubaneswar",
//        skillTags = emptyList(),
//        twitter = "",
//        username = "debankumarsahu",
//        website = emptyList(),
//        errors = null,
//    )
//    val previewLeetcodeProfileUiState = HomeScreen.LeetcodeProfileUiState(data = previewLeetcodeUserProfile)
//    val previewContestStatsList = listOf<ContestStats>(
//        ContestStats(
//            contestName = "Weekly Contest 470",
//            problemSolved = 3,
//            totalProblems = 4,
//            rank = 2183,
//            newRating = 1980,
//            treadDirection = "UP",
//            startTime = 1759631400
//        ),
//        ContestStats(
//            contestName = "Weekly Contest 469",
//            problemSolved = 2,
//            totalProblems = 4,
//            rank = 6769,
//            newRating = 1976,
//            treadDirection = "DOWN",
//            startTime = 1759026600
//        )
//    )
//    val previewLeetcodeContestHistoryUiState = HomeScreen.LeetcodeContestHistoryUiState(data = previewContestStatsList)
//    val previewContestDetail = LeetCodeUserContestDetail(
//        data = Data(
//            userContestRanking = UserContestRanking(
//                attendedContestsCount = 93,
//                badge = BadgeX(name = "Knight"),
//                globalRanking = 20398,
//                rating = 1980.toDouble(),
//                topPercentage = 2.76,
//                totalParticipants = 771462
//            ),
//            userContestRankingHistory = listOf(
//                UserContestRankingHistory(
//                    attended = true,
//                    contest = Contest(
//                        startTime = 1759026600,
//                        title = "Weekly Contest 469",
//                    ),
//                    finishTimeInSeconds = 60,
//                    problemsSolved = 2,
//                    ranking = 6769,
//                    rating = 1976.toDouble(),
//                    totalProblems = 4,
//                    trendDirection = "DOWN"
//                ),
//                UserContestRankingHistory(
//                    attended = true,
//                    contest = Contest(
//                        startTime = 1759631400,
//                        title = "Weekly Contest 470",
//                    ),
//                    finishTimeInSeconds = 60,
//                    problemsSolved = 3,
//                    ranking = 2183,
//                    rating = 1980.toDouble(),
//                    totalProblems = 4,
//                    trendDirection = "UP"
//                )
//            )
//        )
//    )
//    val previewContestDetailUiState = HomeScreen.LeetcodeContestDetailUiState(data = previewContestDetail)
//    val previewFullProfile = LeetCodeUserFullProfile(
//        contributionPoint = 1475,
//        easySolved = 253,
//        hardSolved = 103,
//        matchedUserStats = null,
//        mediumSolved = 490,
//        ranking = 42110,
//        recentSubmissions = emptyList(),
//        reputation = 25,
//        submissionCalendar = null,
//        totalEasy = 907,
//        totalHard = 876,
//        totalMedium = 1932,
//        totalQuestions = 3715,
//        totalSolved = 846,
//        totalSubmissions = emptyList()
//    )
//    val previewLeetcodeFullProfileUiState = HomeScreen.LeetcodeFullProfileUiState(data = previewFullProfile)
//    HomeScreenContent(
//        leetcodeProfileUiState = previewLeetcodeProfileUiState,
//        leetcodeContestHistoryUiState = previewLeetcodeContestHistoryUiState,
//        leetcodeContestDetailUiState = previewContestDetailUiState,
//        leetcodeFullProfileUiState = previewLeetcodeFullProfileUiState
//    )
//}



@Preview
@Composable
fun TopLanguagesUiPreview() {
    LanguagesStatsUi(
        languageStats = listOf(
            DomainLanguageStats(
                name = "Kotlin",
                solvedQuestion = 1000,
                percentage = 0.67
            ),
            DomainLanguageStats(
                name = "Python",
                solvedQuestion = 500,
                percentage = 0.54
            ),
            DomainLanguageStats(
                name = "Go",
                solvedQuestion = 400,
                percentage = 0.24
            ),
            DomainLanguageStats(
                name = "Java",
                solvedQuestion = 300,
                percentage = 0.17
            ),
        )
    )
}
