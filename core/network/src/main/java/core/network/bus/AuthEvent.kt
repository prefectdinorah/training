package core.network.bus

sealed interface AuthEvent {
    data object ForceLogout : AuthEvent
}