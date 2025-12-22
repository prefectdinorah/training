package fitness.component.category_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import fitness.component.utils.PreviewPhone
import fitness.theme.PreviewAppTheme
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

/**
 * Отображает горизонтальный список категорий.
 *
 * Список реализован с помощью [LazyRow] и поддерживает
 * отступы между элементами и паддинги по краям.
 *
 * @param items список элементов категорий, которые будут отображены.
 * Каждый элемент должен реализовывать интерфейс [ICategoryItem] и
 * содержать иконку, описание для accessibility и название категории.
 */
@Composable
fun CategoryList(
    items: PersistentList<ICategoryItem>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = items,
        ) { category ->
            CategoryItem(
                icon = category.icon,
                contentDescription = category.contentDescription,
                categoryName = category.categoryName
            )
        }
    }
}

private fun createCategoryListPreview(): PersistentList<CategoryListItem> =
    listOf(
        CategoryListItem(
            icon = Icons.Default.Home,
            contentDescription = "Главная",
            categoryName = "Главная"
        ),
        CategoryListItem(
            icon = Icons.Default.ShoppingCart,
            contentDescription = "Корзина",
            categoryName = "Корзина"
        ),
        CategoryListItem(
            icon = Icons.Default.Favorite,
            contentDescription = "Избранное",
            categoryName = "Избранное"
        ),
        CategoryListItem(
            icon = Icons.Default.Person,
            contentDescription = "Профиль",
            categoryName = "Профиль"
        ),
        CategoryListItem(
            icon = Icons.Default.Settings,
            contentDescription = "Настройки",
            categoryName = "Настройки"
        ),
        CategoryListItem(
            icon = Icons.Default.History,
            contentDescription = "История",
            categoryName = "История"
        ),
        CategoryListItem(
            icon = Icons.Default.Notifications,
            contentDescription = "Уведомления",
            categoryName = "Уведомления"
        ),
        CategoryListItem(
            icon = Icons.Default.Star,
            contentDescription = "Рейтинг",
            categoryName = "Рейтинг"
        ),
        CategoryListItem(
            icon = Icons.Default.Email,
            contentDescription = "Почта",
            categoryName = "Почта"
        )
    ).toPersistentList()

private data class CategoryListItem(
    override val icon: ImageVector,
    override val contentDescription: String,
    override val categoryName: String
): ICategoryItem

@PreviewPhone
@Composable
private fun CategoryListPreview() {
    PreviewAppTheme {
        CategoryList(items = createCategoryListPreview())
    }
}
