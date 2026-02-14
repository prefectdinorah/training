package fitness.component.month_year_picker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
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
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Компонент для выбора месяца и года
 *
 * @param yearMonth Текущий выбранный месяц и год
 * @param onPreviousMonth Обработчик нажатия на кнопку предыдущего месяца
 * @param onNextMonth Обработчик нажатия на кнопку следующего месяца
 * @param modifier Модификатор для кастомизации компонента
 */
@Composable
fun MonthYearPicker(
    yearMonth: YearMonth,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    modifier: Modifier = Modifier
) {
    val ruLocale = remember { Locale("ru") }
    val monthYearFormatter = remember {
        DateTimeFormatter.ofPattern("LLLL yyyy", ruLocale)
    }

    val formattedMonthYear = remember(yearMonth) {
        yearMonth.format(monthYearFormatter)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(ruLocale) else it.toString() }
    }

    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Кнопка предыдущего месяца
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
                .clickable(onClick = onPreviousMonth),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Предыдущий месяц",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        // Отображение месяца и года
        Text(
            text = formattedMonthYear,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Кнопка следующего месяца
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
                .clickable(onClick = onNextMonth),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Следующий месяц",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@PreviewPhone
@Composable
private fun MonthYearPickerPreview() {
    PreviewAppTheme {
        MonthYearPicker(
            yearMonth = YearMonth.of(2025, 2),
            onPreviousMonth = {},
            onNextMonth = {}
        )
    }
}
