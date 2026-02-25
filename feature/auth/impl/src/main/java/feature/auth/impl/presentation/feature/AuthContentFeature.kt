package feature.auth.impl.presentation.feature

import androidx.compose.runtime.Composable
import feature.auth.api.presentation.feature.IAuthContentFeature
import javax.inject.Inject

internal class AuthContentFeature @Inject constructor() : IAuthContentFeature {

    @Composable
    override fun Content(onAuthComplete: () -> Unit) {
        AuthFeature(onAuthComplete = onAuthComplete)
    }
}