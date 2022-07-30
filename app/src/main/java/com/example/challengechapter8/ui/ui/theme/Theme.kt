package com.example.challengechapter8.ui.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = com.example.challengechapter8.theme.Purple200,
    primaryVariant = com.example.challengechapter8.theme.Purple700,
    secondary = com.example.challengechapter8.theme.Teal200
)

private val LightColorPalette = lightColors(
    primary = com.example.challengechapter8.theme.Purple500,
    primaryVariant = com.example.challengechapter8.theme.Purple700,
    secondary = com.example.challengechapter8.theme.Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ChallengeChapter8Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = com.example.challengechapter8.theme.Typography,
        shapes = com.example.challengechapter8.theme.Shapes,
        content = content
    )
}