package fitness.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fitness.component.infografics.donut.DonutChart
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme

// ── Domain Model ────────────────────────────────────────────────────────────────

data class WorkoutCardData(
    val title: String,
    val weekLabel: String,
    val taskCount: Int,
    val progress: Float,       // 0f..1f
)

// ── Workout Card ────────────────────────────────────────────────────────────────

/**
 * Карточка тренировки со слотом для изображения.
 *
 * @param data Данные карточки
 * @param imageContent Слот для изображения — передай AsyncImage из вызывающего модуля
 * @param onClick Обработчик нажатия
 *
 * ```kotlin
 * WorkoutCard(
 *     data = WorkoutCardData(...),
 *     imageContent = {
 *         AsyncImage(model = url, contentDescription = null, contentScale = ContentScale.Crop)
 *     }
 * )
 * ```
 */
@Composable
fun WorkoutCard(
    data: WorkoutCardData,
    modifier: Modifier = Modifier,
    imageContent: @Composable () -> Unit = {},
    onClick: () -> Unit = {},
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            WorkoutThumbnail(
                imageContent = imageContent,
                modifier = Modifier.size(56.dp),
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = data.title,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "${data.weekLabel}  •  ${data.taskCount} Tasks",
                    color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            DonutChart(
                progress = data.progress,
                size = 36.dp,
                strokeWidth = 4.dp,
                progressColor = MaterialTheme.colorScheme.primary,
                backgroundColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.15f),
            )
        }
    }
}

// ── Thumbnail ────────────────────────────────────────────────────────────────────

@Composable
private fun WorkoutThumbnail(
    imageContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surface),
    ) {
        imageContent()
    }
}

// ── Preview ─────────────────────────────────────────────────────────────────────

@PreviewPhone
@Composable
private fun WorkoutCardPreview() {
    PreviewAppTheme {
        WorkoutCard(
            data = WorkoutCardData(
                title = "Lower body workout",
                weekLabel = "Week 2",
                taskCount = 8,
                progress = 0.25f,
            ),
            modifier = Modifier.padding(16.dp),
        )
    }
}