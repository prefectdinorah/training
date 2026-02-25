package feature.auth.impl.presentation.launcher

import feature.auth.api.presentation.launcher.AuthEntryKey
import feature.auth.api.presentation.launcher.IAuthLauncher
import support.navigation.NavigationHolder
import javax.inject.Inject

internal class AuthLauncher @Inject constructor(
    private val navigationHolder: NavigationHolder,
) : IAuthLauncher {

    override fun openAuth() {
        navigationHolder.navigate(AuthEntryKey)
    }
}