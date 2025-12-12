package org.deban.cpvault

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


val LightBeige = Color(0xFFF5F5DC)
val MirageApprox40 = Color(41,41,63,150)
val EbonyClayApprox45 = Color(28,28,46,150)


val homeBackgroundBrush = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF222834),  // top color
        Color(0xFF11161F)   // bottom color
    ),
    startY = 0f,
    endY = Float.POSITIVE_INFINITY
)

val gradientBrush = Brush.verticalGradient(
    colors = listOf(
        Color(53,53,53,255),
        Color(120,120,120,200)
    )
)


val leetcodeRatingCardGradient = Brush.verticalGradient(
    colors = listOf(
        MirageApprox40,
        EbonyClayApprox45
    ),
    endY = Float.POSITIVE_INFINITY
)

val leetcodeRatingTextGradient = Brush.verticalGradient(
    colors = listOf(
        Color(255,81,47),
        Color(240,152,25)
    )
)

val hardLeetcodeQuestionBoxGradient = Brush.verticalGradient(
    colors = listOf(
        Color(255,81,47,210),
        Color(221,36,118,180)
    )
)

val hardLeetcodeQuestionCircleGradient = Brush.verticalGradient(
    colors = listOf(
        Color(255,81,47),
        Color(221,36,118)
    )
)

val hardLeetcodeQuestionTextGradient = Brush.verticalGradient(
    colors = listOf(
        Color(255,81,47),
        Color(221,36,118)
    )
)

val mediumLeetcodeQuestionBoxGradient = Brush.verticalGradient(
    colors = listOf(
        Color(255,215,0,210),
        Color(221,179,71,180)
    )
)

val mediumLeetcodeQuestionCircleGradient = Brush.verticalGradient(
    colors = listOf(
        Color(255,215,0),
        Color(221,179,71)
    )
)

val mediumLeetcodeQuestionTextGradient = Brush.verticalGradient(
    colors = listOf(
        Color(255,215,0),
        Color(221,179,71)
    )
)

val easyLeetcodeQuestionBoxGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0,200,83,210),
        Color(178,255,89,180)
    )
)

val easyLeetcodeQuestionCircleGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0,200,83),
        Color(178,255,89)
    )
)

val easyLeetcodeQuestionTextGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0,200,83),
        Color(178,255,89)
    )
)

val leetcodeContestStatsBackgroundGradient = Brush.verticalGradient(
    colors = listOf(
        Color(26,188,156),
        Color(94,234,212)
    )
)

val languageStatsBackgroundGradient = Brush.verticalGradient(
    colors = listOf(
        Color(137,194,217),
        Color(1,79,134)
    )
)

val rankOneLanguageStatGradient = Brush.verticalGradient(
    colors = listOf(
        Color(255,195,0),
        Color(255,170,0)
    )
)

val rankTwoLanguageStatGradient = Brush.verticalGradient(
    colors = listOf(
        Color(108,117,125),
        Color(73,80,87)
    )
)

val rankThreeLanguageStatGradient = Brush.verticalGradient(
    colors = listOf(
        Color(213,137,54),
        Color(164,66,0)
    )
)

val otherRankLanguageStatGradient = Brush.verticalGradient(
    colors = listOf(
        Color(164,195,178),
        Color(107,144,128)
    )
)