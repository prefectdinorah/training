package fitness.component.top_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme

/**
 * TopBar компонент с кнопкой назад, заголовком и произвольными действиями справа
 *
 * @param title Заголовок для отображения в центре
 * @param onBackClick Обработчик нажатия на кнопку "Назад"
 * @param modifier Модификатор для кастомизации компонента
 * @param actions Composable lambda для добавления кнопок справа от заголовка (опционально)
 */
@Composable
fun TopBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    actions: @Composable BoxScope.() -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 42.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        TopBarIcon(
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Назад"
                )
            },
            alignment = Alignment.CenterStart,
            onClick = onBackClick
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )

        actions()
    }
}

/**
 * Создает кнопку для TopBar с заданной иконкой (для использования в actions lambda)
 *
 * @param icon Composable lambda для отображения иконки
 * @param alignment Выравнивание кнопки (обычно Alignment.CenterEnd для правых кнопок)
 * @param onClick Обработчик нажатия на кнопку
 */
@Composable
fun BoxScope.TopBarAction(
    icon: @Composable () -> Unit,
    alignment: Alignment = Alignment.CenterEnd,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .align(alignment)
            .clip(shape = RoundedCornerShape(size = 12.dp))
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        IconButton(
            onClick = onClick,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            icon()
        }
    }
}

/**
 * Создает кнопку для TopBar (без привязки к BoxScope, для использования в Row)
 *
 * @param icon Composable lambda для отображения иконки
 * @param onClick Обработчик нажатия на кнопку
 * @param modifier Модификатор для кастомизации
 */
@Composable
fun TopBarActionButton(
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = 12.dp))
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        IconButton(
            onClick = onClick,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            icon()
        }
    }
}

@Composable
private fun BoxScope.TopBarIcon(
    icon: @Composable () -> Unit,
    alignment: Alignment,
    onClick: () -> Unit,
) {
    TopBarAction(
        icon = icon,
        alignment = alignment,
        onClick = onClick
    )
}

@PreviewPhone
@Composable
private fun TopBarPreview() {
    PreviewAppTheme {
        TopBar(
            title = "Потребление калорий",
            modifier = Modifier,
            onBackClick = { }
        )
    }
}

@PreviewPhone
@Composable
private fun TopBarWithOneActionPreview() {
    PreviewAppTheme {
        TopBar(
            title = "Потребление калорий",
            modifier = Modifier,
            onBackClick = { },
            actions = {
                TopBarAction(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Настройки"
                        )
                    },
                    onClick = { }
                )
            }
        )
    }
}

@PreviewPhone
@Composable
private fun TopBarWithMultipleActionsPreview() {
    PreviewAppTheme {
        TopBar(
            title = "Мои тренировки",
            modifier = Modifier,
            onBackClick = { },
            actions = {
                Row(
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    TopBarActionButton(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Настройки"
                            )
                        },
                        onClick = { }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    TopBarActionButton(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Ещё"
                            )
                        },
                        onClick = { }
                    )
                }
            }
        )
    }
}