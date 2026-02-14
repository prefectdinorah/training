package fitness.component.month_year_picker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import fitness.component.month_year_picker.atom.day_list.DaysList
import fitness.component.month_year_picker.atom.day_list.IDayModel
import fitness.component.month_year_picker.utils.MonthYearPickerUtils.generateDaysForMonth
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import java.time.LocalDate
import java.time.YearMonth

/**
 * Полный компонент календаря с выбором месяца/года и списком дней
 *
 * @param modifier Модификатор для кастомизации компонента
 * @param initialYearMonth Начальный месяц и год для отображения
 * @param selectedDate Текущая выбранная дата (по умолчанию - текущая дата)
 * @param onDateSelected Обработчик выбора даты
 */
@Composable
fun MonthYearCalendar(
    modifier: Modifier = Modifier,
    initialYearMonth: YearMonth = YearMonth.now(),
    selectedDate: LocalDate? = LocalDate.now(),
    onDateSelected: (LocalDate) -> Unit = {}
) {
    var currentYearMonth by remember { mutableStateOf(initialYearMonth) }
    // Храним дату, которую пользователь действительно выбрал
    var userSelectedDate by remember(selectedDate) { mutableStateOf(selectedDate ?: LocalDate.now()) }
    var scrollToEnd by remember { mutableStateOf<Boolean?>(null) }

    // Для отображения выбираем дату только если она в текущем месяце
    val displaySelectedDate = if (userSelectedDate != null && YearMonth.from(userSelectedDate) == currentYearMonth) {
        userSelectedDate
    } else {
        null
    }

    val days by remember(currentYearMonth, displaySelectedDate) {
        derivedStateOf {
            generateDaysForMonth(
                yearMonth = currentYearMonth,
                selectedDate = displaySelectedDate
            )
        }
    }

    Column(modifier = modifier) {
        MonthYearPicker(
            yearMonth = currentYearMonth,
            onPreviousMonth = {
                val newYearMonth = currentYearMonth.minusMonths(1)
                currentYearMonth = newYearMonth

                // Определяем направление скролла
                scrollToEnd = when {
                    userSelectedDate != null && YearMonth.from(userSelectedDate) == newYearMonth -> {
                        null // Скролл к выбранной пользователем дате
                    }
                    YearMonth.from(LocalDate.now()) == newYearMonth -> {
                        null // Скролл к текущей дате
                    }
                    else -> {
                        true // Скролл в конец
                    }
                }
            },
            onNextMonth = {
                val newYearMonth = currentYearMonth.plusMonths(1)
                currentYearMonth = newYearMonth

                // Определяем направление скролла
                scrollToEnd = when {
                    userSelectedDate != null && YearMonth.from(userSelectedDate) == newYearMonth -> {
                        null // Скролл к выбранной пользователем дате
                    }
                    YearMonth.from(LocalDate.now()) == newYearMonth -> {
                        null // Скролл к текущей дате
                    }
                    else -> {
                        false // Скролл в начало
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        DaysList(
            items = days,
            scrollToEnd = scrollToEnd,
            onDayClick = { date ->
                userSelectedDate = date // Сохраняем выбранную пользователем дату
                scrollToEnd = null // Сбрасываем флаг после выбора даты вручную
                onDateSelected(date)
            }
        )
    }
}

data class DayModel(
    override val date: LocalDate,
    override val isSelected: Boolean
) : IDayModel

@PreviewPhone
@Composable
private fun MonthYearCalendarPreview() {
    PreviewAppTheme {
        MonthYearCalendar(
            modifier = Modifier,
            initialYearMonth = YearMonth.of(2025, 2),
            selectedDate = LocalDate.of(2025, 2, 14)
        )
    }
}

@PreviewPhone
@Composable
private fun MonthYearCalendarCurrentMonthPreview() {
    PreviewAppTheme {
        MonthYearCalendar(
            modifier = Modifier,
            initialYearMonth = YearMonth.now(),
            selectedDate = LocalDate.now()
        )
    }
}
