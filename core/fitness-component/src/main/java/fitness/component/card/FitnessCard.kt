package fitness.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.rounded.AutoAwesomeMotion
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fitness.component.R
import fitness.component.infografics.donut.DonutChart
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme

/**
 * Карточка тренировки с прогрессом
 *
 * @param title Название тренировки
 * @param countTutorial Общее количество упражнений
 * @param progress Прогресс выполнения от 0.0 до 1.0
 * @param onClick Обработчик нажатия на кнопку
 */
@Composable
fun FitnessCard(
    title: String,
    countTutorial: Int,
    progress: Float,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(size = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Row(
                modifier = Modifier.padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clip(CircleShape)
                        .background(color = Color.Black.copy(alpha = 0.1f))
                        .padding(all = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(size = 40.dp)
                            .rotate(degrees = -150f),
                        imageVector = Icons.Rounded.AutoAwesomeMotion,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondary
                    )

                    Text(
                        text = stringResource(id = R.string.core_exercises_remaining, countTutorial),
                        color = Color(0xFFBDBDBD),
                        fontSize = 14.sp
                    )
                }

                DonutChart(
                    progress = progress,
                    showPercentage = true
                )
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(size = 16.dp),
                onClick = onClick
            ) {
                Icon(
                    modifier = Modifier.size(size = 24.dp),
                    imageVector = Icons.Outlined.PlayArrow,
                    contentDescription = null
                )
                Text(
                    text = stringResource(id = R.string.core_watch_tutorial),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@PreviewPhone
@Composable
private fun FitnessCardPreview() {
    PreviewAppTheme {
        FitnessCard(
            countTutorial = 12,
            progress = 0.5f,
            title = "Силовая тренировка",
            onClick = {  }
        )
    }
}