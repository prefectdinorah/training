package fitness.component.month_year_picker.atom.day_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import java.time.LocalDate

/**
 * Горизонтальный скролящийся список дней месяца
 *
 *
 * @param items Список дней для отображения. Каждый элемент должен реализовывать интерфейс [IDayModel]
 * @param scrollToEnd Направление скролла: true - в конец, false - в начало, null - к выбранному элементу
 * @param onDayClick Обработчик нажатия на день, принимает дату выбранного дня
 */
@Composable
fun DaysList(
    items: PersistentList<IDayModel>,
    scrollToEnd: Boolean? = null,
    onDayClick: (LocalDate) -> Unit
) {
    val listState = rememberLazyListState()

    // Находим индекс выбранного элемента
    val selectedIndex = items.indexOfFirst { it.isSelected }

    // Автоматически скроллим к нужной позиции при изменении списка или выбора
    LaunchedEffect(items, selectedIndex, scrollToEnd) {
        when (scrollToEnd) {
            true -> {
                // Скролл в конец списка (для предыдущего месяца)
                if (items.isNotEmpty()) {
                    listState.animateScrollToItem(items.size - 1)
                }
            }
            false -> {
                // Скролл в начало списка (для следующего месяца)
                listState.animateScrollToItem(0)
            }
            null -> {
                // Скролл к выбранному элементу (по умолчанию)
                if (selectedIndex != -1) {
                    // Скроллим так, чтобы выбранный элемент был ближе к центру экрана
                    // Отнимаем 2, чтобы элемент не был у самого края
                    val offset = maxOf(0, selectedIndex - 2)
                    listState.animateScrollToItem(offset)
                }
            }
        }
    }

    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = items,
            key = { it.date.toEpochDay() }
        ) { day ->
            DayItem(
                date = day.date,
                isSelected = day.isSelected,
                onClick = { onDayClick(day.date) }
            )
        }
    }
}

private data class DayItemModel(
    override val date: LocalDate,
    override val isSelected: Boolean
) : IDayModel

private fun createDaysListPreview(): PersistentList<DayItemModel> {
    val startDate = LocalDate.of(2025, 2, 1)
    val daysInMonth = startDate.lengthOfMonth()

    return (1..daysInMonth).map { day ->
        val date = startDate.withDayOfMonth(day)
        DayItemModel(
            date = date,
            isSelected = day == 14
        )
    }.toPersistentList()
}

@PreviewPhone
@Composable
private fun DaysListPreview() {
    PreviewAppTheme {
        DaysList(
            items = createDaysListPreview(),
            scrollToEnd = null,
            onDayClick = {}
        )
    }
}
