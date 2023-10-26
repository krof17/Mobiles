package com.example.prctica1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.prctica1.R

val MiddleEarth = FontFamily(
    Font(R.font.middleearthao6m)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = MiddleEarth,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = MiddleEarth,
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp,
        lineHeight = 28.sp,
        letterSpacing = 1.sp
    ),
    labelSmall = TextStyle(
        fontFamily = MiddleEarth,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)