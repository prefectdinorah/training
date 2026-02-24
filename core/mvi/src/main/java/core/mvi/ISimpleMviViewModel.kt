package core.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ISimpleMviViewModel<Intent, State, Effect> {
    val viewState: StateFlow<State>
    val effect: Flow<Effect>
    fun sendIntent(event: Intent)
}