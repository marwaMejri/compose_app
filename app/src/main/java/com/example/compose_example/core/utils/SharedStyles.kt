package com.example.compose_example.core.utils
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.compose_example.R

val titleTextStyle: TextStyle = TextStyle(
    color = Color.White,
    fontWeight = FontWeight.Bold,
    fontFamily = FontFamily(Font(R.font.poppins_bold)),
    fontSize = 16.sp,
    textAlign = TextAlign.Center,

    )
val subTitleTextStyle: TextStyle = TextStyle(
    color = Color.White,
    fontWeight = FontWeight.Medium,
    fontFamily = FontFamily(Font(R.font.poppins_medium)),
    fontSize = 16.sp,
    textAlign = TextAlign.Center,
)
val regularTitleTextStyle: TextStyle = TextStyle(
    color = Color.White,
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily(Font(R.font.poppins_regular)),
    fontSize = 16.sp,
)