package fitness.theme

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFEBFF57), // Lime yellow accent
    secondary = Color(0xFF1C1C1E), // Card background
    onPrimary = Color(0xFF000000), // Text on primary (dark on bright)
    onSecondary = Color(0xFFFFFFFF), // Text on secondary (light on dark)
    background = Color(0xFF0A0A0A), // Very dark background
    onBackground = Color(0xFFFFFFFF), // Text on background (light on dark)
    surface = Color(0xFF3A3A3C), // Light frame
    onSurface = Color(0xFFFFFFFF), // Text on surface
    surfaceVariant = Color(0xFFEBFF57), // Dark frame
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFEBFF57), // Lime yellow accent
    secondary = Color(0xFFFFFFFF), // Card background
    onPrimary = Color(0xFF2A2A2D), // Card text
    onSecondary = Color(0xFF2A2A2D),
    background = Color(0xFFF3F3F4), // Light gray background
    onBackground = Color(0xFF2A2A2D), // Text on background (dark on light)
    surface = Color(0xFFFFFFFF), // Light frame
    onSurface = Color(0xFF2A2A2D), // Text on surface
    surfaceVariant = Color(0xFF212121) // Dark frame
)

@Composable
fun TrainingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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
        content = content
    )
}

@Composable
fun PreviewAppTheme(
    content: @Composable () -> Unit
) {
    TrainingTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            content()
        }
    }
}