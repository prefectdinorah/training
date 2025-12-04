package fitness.component.infografics.donut.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp

internal fun DrawScope.drawDonut(
    progress: Float,
    strokeWidth: Dp,
    color: Color,
    roundedEnd: Boolean
) {
    val strokeWidthPx = strokeWidth.toPx()
    val radius = (size.minDimension - strokeWidthPx) / 2
    val centerOffset = Offset(size.width / 2, size.height / 2)

    drawArc(
        color = color,
        startAngle = -90f,
        sweepAngle = 360f * progress,
        useCenter = false,
        topLeft = Offset(
            centerOffset.x - radius,
            centerOffset.y - radius
        ),
        size = Size(radius * 2, radius * 2),
        style = Stroke(
            width = strokeWidthPx,
            cap = if (roundedEnd) StrokeCap.Round else StrokeCap.Butt
        )
    )
}