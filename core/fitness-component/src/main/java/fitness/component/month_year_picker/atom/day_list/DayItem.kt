package fitness.component.month_year_picker.atom.day_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Элемент дня в календаре
 *
 * @param date Дата для отображения
 * @param isSelected Выбран ли этот день
 * @param onClick Обработчик нажатия на день
 */
@Composable
fun DayItem(
    date: LocalDate,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val ruLocale = remember { Locale("ru") }
    val dayOfWeekFormatter = remember { DateTimeFormatter.ofPattern("EEE", ruLocale) }
    val dayOfMonthFormatter = remember { DateTimeFormatter.ofPattern("d") }

    val dayOfWeek = remember(date) {
        date.format(dayOfWeekFormatter)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(ruLocale) else it.toString() }
    }
    val dayOfMonth = remember(date) { date.format(dayOfMonthFormatter) }

    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surface
    }

    val textColor = if (isSelected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Box(
        modifier = modifier
            .size(width = 64.dp, height = 80.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = dayOfWeek,
                fontSize = 12.sp,
                color = textColor.copy(alpha = 0.7f),
                fontWeight = FontWeight.Medium
            )

            Text(
                text = dayOfMonth,
                fontSize = 20.sp,
                color = textColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@PreviewPhone
@Composable
private fun DayItemPreview() {
    PreviewAppTheme {
        DayItem(
            date = LocalDate.of(2025, 2, 14),
            isSelected = false,
            onClick = {}
        )
    }
}

@PreviewPhone
@Composable
private fun DayItemSelectedPreview() {
    PreviewAppTheme {
        DayItem(
            date = LocalDate.of(2025, 2, 14),
            isSelected = true,
            onClick = {}
        )
    }
}
