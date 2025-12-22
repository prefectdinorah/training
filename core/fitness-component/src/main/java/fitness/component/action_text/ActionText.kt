package fitness.component.action_text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fitness.component.R
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme

/**
 * Заголовок раздела с кнопкой "Смотреть все"
 *
 * @param title Отображаемый текст элемента
 * @param onClick Обработчик клика/тапа по элементу
 */
@Composable
fun ActionText(
    title: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(all = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(weight = 1f),
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            modifier = Modifier.clickable(onClick = onClick),
            text = stringResource(id = R.string.core_see_all),
            color = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

@PreviewPhone
@Composable
private fun ActionTextPreview() {
    PreviewAppTheme {
        ActionText(title = "Категории", onClick = {})
    }
}

@PreviewPhone
@Composable
private fun ActionTextPreview1() {
    PreviewAppTheme {
        ActionText(title = "Тренировочные наборы", onClick = {})
    }
}
