package fitness.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object PulseFitColors {
    val Background = Color(0xFF111111)
    val Surface = Color(0xFF1A1A1A)
    val SurfaceLight = Color(0xFF222222)
    val Accent = Color(0xFFE0FE10)         // neon-lime
    val AccentDark = Color(0xFFA8C40A)
    val AccentDimmed = Color(0x26E0FE10)   // 15 % alpha
    val Teal = Color(0xFF10E0D0)
    val Orange = Color(0xFFFE5A10)
    val Yellow = Color(0xFFFEC410)
    val TextPrimary = Color.White
    val TextSecondary = Color(0x66FFFFFF)  // 40 %
    val TextHint = Color(0x40FFFFFF)       // 25 %
    val Divider = Color(0x0FFFFFFF)        // 6 %
    val InputBg = Color(0x08FFFFFF)        // 3 %
    val InputBorder = Color(0x0FFFFFFF)
    val InputFocusBg = Color(0x0AE0FE10)
    val InputFocusBorder = Color(0x40E0FE10)
    val Green = Color(0xFF4CAF50)
    val Blue = Color(0xFF2196F3)
}

val ManropeFamily = FontFamily.Default    // replace: FontFamily(Font(R.font.manrope_...))
val DmSansFamily = FontFamily.Default     // replace: FontFamily(Font(R.font.dm_sans_...))

object PulseFitType {
    val DisplayLarge = TextStyle(
        fontFamily = ManropeFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp,
        letterSpacing = (-0.5).sp,
        color = PulseFitColors.TextPrimary,
    )
    val HeadlineMedium = TextStyle(
        fontFamily = ManropeFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp,
        letterSpacing = (-0.3).sp,
        color = PulseFitColors.TextPrimary,
    )
    val TitleLarge = TextStyle(
        fontFamily = ManropeFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = PulseFitColors.TextPrimary,
    )
    val BodyLarge = TextStyle(
        fontFamily = DmSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = PulseFitColors.TextSecondary,
        lineHeight = 22.sp,
    )
    val BodyMedium = TextStyle(
        fontFamily = DmSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = PulseFitColors.TextSecondary,
    )
    val LabelMedium = TextStyle(
        fontFamily = DmSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        letterSpacing = 0.3.sp,
    )
    val LabelSmall = TextStyle(
        fontFamily = DmSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = 1.2.sp,
        color = PulseFitColors.TextHint,
    )
    val Button = TextStyle(
        fontFamily = DmSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        letterSpacing = 0.3.sp,
    )
}