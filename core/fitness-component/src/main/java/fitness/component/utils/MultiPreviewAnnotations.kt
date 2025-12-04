package fitness.component.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "1 Phone Light",
    group = "Phone",
    device = "spec:width=411dp,height=891dp",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    locale = "ru",
)
@Preview(
    name = "2 Phone Night",
    group = "Phone",
    device = "spec:width=411dp,height=891dp",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ru",
)
annotation class PreviewPhone
