package fitness.component.trainer_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun TrainersList(
    items: PersistentList<ITrainerModel>,
    avatar: @Composable BoxScope.(String) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items) { item ->
            TrainerItem(
                item = item,
                avatar = { avatar(item.avatarUrl) }
            )
        }
    }
}

private data class TrainerItemList(
    override val avatarUrl: String,
    override val name: String,
    override val category: String
): ITrainerModel

private fun createTrainerListPreview(): PersistentList<TrainerItemList> =
    listOf(
        TrainerItemList(
            avatarUrl = "https://randomuser.me/api/portraits/men/32.jpg",
            name = "Парамонов Никита",
            category = "HIIT"
        ),
        TrainerItemList(
            avatarUrl = "https://randomuser.me/api/portraits/women/44.jpg",
            name = "Merik Son",
            category = "Strength"
        ),
        TrainerItemList(
            avatarUrl = "https://randomuser.me/api/portraits/men/76.jpg",
            name = "Hery Dip",
            category = "Yoga"
        ),
        TrainerItemList(
            avatarUrl = "https://randomuser.me/api/portraits/women/65.jpg",
            name = "Anna Lee",
            category = "Pilates"
        ),
        TrainerItemList(
            avatarUrl = "https://randomuser.me/api/portraits/men/12.jpg",
            name = "John Rock",
            category = "Crossfit"
        )
    ).toPersistentList()

@PreviewPhone
@Composable
private fun TrainerListPreview() {
    PreviewAppTheme {
        TrainersList(
            items = createTrainerListPreview(),
            avatar = {
                Image(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        )
    }
}
