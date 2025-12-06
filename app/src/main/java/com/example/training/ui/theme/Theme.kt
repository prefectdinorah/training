package com.example.training.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFEBFF57), // Lime yellow accent
    secondary = Color(0xFF1C1C1E), // Card background
    onPrimary = Color(0xFF2A2A2D), // Card text
    background = Color(0xFF0A0A0A), // Very dark background
    surface = Color(0xFF3A3A3C), // Light frame
    surfaceVariant = Color(0xFF343434), // Dark frame
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFEBFF57), // Lime yellow accent
    secondary = Color(0xFFFFFFFF), // Card background
    onPrimary = Color(0xFF2A2A2D), // Card text
    background = Color(0xFFFAFAFA), // Light gray background
    surface = Color(0xFFFFFFFF), // Light frame
    surfaceVariant = Color(0xFFFFFFFF) // Dark frame
)

@Composable
fun TrainingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}