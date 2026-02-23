package fitness.component.bottom_nav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fitness.component.R
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme

@Composable
internal fun BottomNavItem(
    item: BottomNavigationItem,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val background by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
        animationSpec = tween(durationMillis = 300),
        label = "background"
    )
    val tint by animateColorAsState(
        targetValue = if (selected) Color(0xFF000000) else Color(0xFF888888),
        animationSpec = tween(durationMillis = 300),
        label = "tint"
    )

    Box(
        modifier = Modifier
            .height(56.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            )
            .clip(shape = RoundedCornerShape(size = 16.dp))
            .background(color = background)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 10.dp)
                .clipToBounds(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                contentDescription = stringResource(id = item.title),
                tint = tint,
                modifier = Modifier.size(26.dp)
            )
            AnimatedVisibility(
                visible = selected,
                enter = slideInVertically(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    ),
                    initialOffsetY = { it }
                ) + fadeIn(animationSpec = tween(durationMillis = 200)),
                exit = slideOutVertically(
                    animationSpec = tween(durationMillis = 180),
                    targetOffsetY = { it }
                ) + fadeOut(animationSpec = tween(durationMillis = 150))
            ) {
                Text(
                    text = stringResource(id = item.title),
                    color = Color.Black
                )
            }
        }
    }
}

private object PreviewWorkoutItem : BottomNavigationItem {
    override val title: Int = R.string.core_nav_workout
    override val selectedIcon: ImageVector = Icons.Filled.FitnessCenter
    override val unselectedIcon: ImageVector = Icons.Outlined.FitnessCenter
}

@PreviewPhone
@Composable
private fun BottomNavItemSelectedPreview() {
    PreviewAppTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            BottomNavItem(item = PreviewWorkoutItem, selected = true, onClick = {})
            BottomNavItem(item = PreviewWorkoutItem, selected = false, onClick = {})
        }
    }
}