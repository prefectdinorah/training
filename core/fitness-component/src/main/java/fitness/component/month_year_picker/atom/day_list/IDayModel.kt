package fitness.component.month_year_picker.atom.day_list

import java.time.LocalDate

/**
 * Интерфейс для модели данных дня в календаре
 */
interface IDayModel {
    /**
     * Дата
     */
    val date: LocalDate

    /**
     * Выбран ли этот день
     */
    val isSelected: Boolean
}