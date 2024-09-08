package com.adisastrawan.jetpacksubmission.ui.theme

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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.adisastrawan.jetpacksubmission.R

val customHeaderFontFamily = FontFamily(
    Font(R.font.beaufort_regular),
    Font(R.font.beaufort_bold, FontWeight.Bold),
    Font(R.font.beaufort_medium, FontWeight.Medium),
    Font(R.font.beaufort_light, FontWeight.Light),
    Font(R.font.beaufort_heavy, FontWeight.ExtraBold)
)
val customFontFamily = FontFamily(
    Font(R.font.spiegel_regular),
    Font(R.font.spiegel_bold,FontWeight.Bold),
    Font(R.font.spiegel_semi_bold,FontWeight.SemiBold)
)
private val DarkColorScheme = darkColorScheme(
    primary = Blue3,
    secondary = Gold1,
    background = Blue6,
    surface = Grey2,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Blue3,
    secondary = Gold1,
    background = Blue6,
    surface = Grey2,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun JetpackSubmissionTheme(
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