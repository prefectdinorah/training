package feature.main.impl.presentation.feature

import androidx.compose.runtime.Composable
import feature.main.api.presentation.IMainContentFeature
import feature.main.impl.presentation.MainScreen
import javax.inject.Inject

internal class MainContentFeature @Inject constructor() : IMainContentFeature {

    @Composable
    override fun Content() {
        MainScreen()
    }
}