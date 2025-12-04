package fitness.component.infografics.donut

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fitness.component.infografics.donut.utils.drawDonut

/**
 * Круговой индикатор прогресса в виде пончика
 *
 * @param progress Значение прогресса от 0.0 до 1.0 (0% до 100%)
 * @param size Общий размер компонента
 * @param strokeWidth Толщина линии окружности
 * @param progressColor Цвет линии прогресса
 * @param backgroundColor Цвет фоновой части
 * @param textStyle Стиль текста процентов
 * @param showPercentage Показывать процент в центре
 * @param roundedEnd Скруглять конец линии прогресса
 * @param modifier Modifier для настройки расположения
 */
@Composable
fun DonutChart(
    progress: Float,
    size: Dp = 60.dp,
    strokeWidth: Dp = 6.dp,
    progressColor: Color = Color.Black,
    backgroundColor: Color = Color.LightGray.copy(alpha = 0.3f),
    textStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    ),
    showPercentage: Boolean = false,
    roundedEnd: Boolean = true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawDonut(
                progress = 1f,
                strokeWidth = strokeWidth,
                color = backgroundColor,
                roundedEnd = false
            )
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawDonut(
                progress = progress,
                strokeWidth = strokeWidth,
                color = progressColor,
                roundedEnd = roundedEnd
            )
        }

        if (showPercentage) {
            Text(
                text = "${(progress * 100).toInt()}%",
                style = textStyle
            )
        }
    }
}

@Preview
@Composable
private fun DonutChartPreview() {
    DonutChart(progress = 0.5f, showPercentage = true)
}
