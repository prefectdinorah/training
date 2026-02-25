package feature.auth.api.presentation.feature

import androidx.compose.runtime.Composable

interface IAuthContentFeature {

    @Composable
    fun Content(onAuthComplete: () -> Unit)
}