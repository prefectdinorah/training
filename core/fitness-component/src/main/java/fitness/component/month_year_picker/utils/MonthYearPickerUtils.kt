package fitness.component.month_year_picker.utils

import fitness.component.month_year_picker.DayModel
import fitness.component.month_year_picker.atom.day_list.IDayModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import java.time.LocalDate
import java.time.YearMonth

internal object MonthYearPickerUtils {

    /**
     * Генерирует список дней для указанного месяца
     *
     * @param yearMonth Месяц и год для генерации дней
     * @param selectedDate Выбранная дата (если есть)
     * @return Список моделей дней
     */
    fun generateDaysForMonth(
        yearMonth: YearMonth,
        selectedDate: LocalDate? = null
    ): PersistentList<IDayModel> {
        val startDate = yearMonth.atDay(1)
        val daysInMonth = yearMonth.lengthOfMonth()

        return (1..daysInMonth).map { day ->
            val date = startDate.withDayOfMonth(day)
            DayModel(
                date = date,
                isSelected = date == selectedDate
            )
        }.toPersistentList()
    }
}