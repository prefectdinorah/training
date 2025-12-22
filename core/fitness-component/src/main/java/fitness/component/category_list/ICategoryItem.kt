package fitness.component.category_list

import androidx.compose.ui.graphics.vector.ImageVector

interface ICategoryItem {
    val icon: ImageVector
    val contentDescription: String
    val categoryName: String
}