package fitness.component.card.image

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fitness.component.R
import fitness.component.infografics.donut.DonutChart
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme
import fitness.theme.PulseFitColors

/**
 * Карточка тренировки с изображением на фоне
 *
 * @param title Название тренировки
 * @param time Время тренировки (например "45 мин")
 * @param progress Прогресс выполнения от 0.0 до 1.0
 * @param status Статус тренировки
 * @param imageContent Слот для изображения (используй AsyncImage с Coil в app модуле)
 * @param onClick Обработчик нажатия на карточку
 * @param modifier Модификатор для кастомизации
 */
@Composable
fun ImageFitnessCard(
    title: String,
    time: String,
    progress: Float,
    status: FitnessCardStatus,
    modifier: Modifier = Modifier,
    imageContent: @Composable () -> Unit = {},
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(width = 300.dp)
            .height(height = 180.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Фоновое изображение с blur внизу
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
            ) {
                imageContent()

                // Blur эффект снизу
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .align(Alignment.BottomCenter)
                        .blur(
                            radius = 6.dp,
                            edgeTreatment = BlurredEdgeTreatment.Unbounded
                        )
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.3f)
                                )
                            )
                        )
                )
            }

            // Градиент для затемнения снизу и сверху
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.4f),  // Темнее сверху
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)   // Темнее снизу
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    StatusBadge(status = status)
                }

                Spacer(Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            maxLines = 2,
                            fontSize = 16.sp
                        )

                        Spacer(Modifier.height(2.dp))

                        Text(
                            text = time,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 13.sp
                        )
                    }

                    Spacer(Modifier.width(12.dp))

                    DonutChart(
                        progress = progress,
                        progressColor = MaterialTheme.colorScheme.primary,
                        showPercentage = true,
                        textStyle = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }
    }
}

/**
 * Бейдж статуса
 */
@Composable
private fun StatusBadge(
    status: FitnessCardStatus,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = status.color.copy(alpha = 0.9f),
        shadowElevation = 2.dp
    ) {
        Text(
            text = stringResource(status.titleRes),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}


/**
 * Статус карточки тренировки
 */
enum class FitnessCardStatus(
    @StringRes val titleRes: Int,
    val color: Color
) {
    NEW(R.string.core_status_new, PulseFitColors.Green),
    IN_PROGRESS(R.string.core_status_in_progress, PulseFitColors.Orange),
    COMPLETED(R.string.core_status_completed, PulseFitColors.Blue),
}

@PreviewPhone
@Composable
private fun ImageFitnessCardPreview() {
    PreviewAppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            ImageFitnessCard(
                title = "Йога для начинающих",
                time = "45 мин",
                progress = 0.65f,
                status = FitnessCardStatus.NEW,
                imageContent = {
                    // Заглушка вместо изображения
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF667EEA),
                                        Color(0xFF764BA2)
                                    )
                                )
                            )
                    )
                }
            )
        }
    }
}

@PreviewPhone
@Composable
private fun ImageFitnessCardInProgressPreview() {
    PreviewAppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            ImageFitnessCard(
                title = "Силовая тренировка верхней части тела",
                time = "60 мин",
                progress = 0.35f,
                status = FitnessCardStatus.IN_PROGRESS,
                imageContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFFFF6B6B),
                                        Color(0xFFFF8E53)
                                    )
                                )
                            )
                    )
                }
            )
        }
    }
}

@PreviewPhone
@Composable
private fun ImageFitnessCardCompletedPreview() {
    PreviewAppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            ImageFitnessCard(
                title = "Кардио",
                time = "30 мин",
                progress = 1.0f,
                status = FitnessCardStatus.COMPLETED,
                imageContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF4ECDC4),
                                        Color(0xFF44A08D)
                                    )
                                )
                            )
                    )
                }
            )
        }
    }
}

@PreviewPhone
@Composable
private fun ImageFitnessCardHorizontalListPreview() {
    PreviewAppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "Рекомендованные тренировки",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    ImageFitnessCard(
                        title = "Йога для начинающих",
                        time = "45 мин",
                        progress = 0.65f,
                        status = FitnessCardStatus.NEW,
                        imageContent = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.linearGradient(
                                            colors = listOf(
                                                Color(0xFF667EEA),
                                                Color(0xFF764BA2)
                                            )
                                        )
                                    )
                            )
                        }
                    )
                }

                item {
                    ImageFitnessCard(
                        title = "Силовая",
                        time = "60 мин",
                        progress = 0.35f,
                        status = FitnessCardStatus.IN_PROGRESS,
                        imageContent = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.linearGradient(
                                            colors = listOf(
                                                Color(0xFFFF6B6B),
                                                Color(0xFFFF8E53)
                                            )
                                        )
                                    )
                            )
                        }
                    )
                }

                item {
                    ImageFitnessCard(
                        title = "Кардио",
                        time = "30 мин",
                        progress = 1.0f,
                        status = FitnessCardStatus.COMPLETED,
                        imageContent = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.linearGradient(
                                            colors = listOf(
                                                Color(0xFF4ECDC4),
                                                Color(0xFF44A08D)
                                            )
                                        )
                                    )
                            )
                        }
                    )
                }
            }
        }
    }
}