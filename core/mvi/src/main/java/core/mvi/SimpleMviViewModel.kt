package core.mvi

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class EmptySimpleViewModel<Intent, State, Effect>(
    init: State,
) : CoroutineViewModel(), ISimpleMviViewModel<Intent, State, Effect> {
    final override val viewState: StateFlow<State> = MutableStateFlow(init)
    final override val effect: Flow<Effect> = emptyFlow()
    final override fun sendIntent(event: Intent) = Unit
}

/**
 * Простая реализация модели для MVI
 * @param Intent события от визуальной части
 * @param State состояние модели для визуальной части
 * @param Effect эффекты, которые надо выполнить визуальной части без сохранения в состоянии модели,
 * навигация, диалоги, пуллеры и так далее
 */
abstract class SimpleMviViewModel<Intent, State, Effect>
    : CoroutineViewModel(), ISimpleMviViewModel<Intent, State, Effect> {

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()

    private var intentCollectorJob: Job? = null

    private val _viewState: MutableStateFlow<State> by lazy { MutableStateFlow(setInitialState()) }

    /** Состояние модели для визуальной части */
    override val viewState: StateFlow<State> by lazy { _viewState.asStateFlow() }

    private val _effect: MutableSharedFlow<Effect> = MutableSharedFlow()
    private val _effectBuffered: MutableSharedFlow<Effect> = MutableSharedFlow(replay = 1)

    /** Эффекты, которые надо выполнить визуальной части */
    override val effect: Flow<Effect> = merge(_effect, _effectBuffered.resetReplayAfterCollect())

    /** Начальное состояние модели для визуальной части */
    protected abstract fun setInitialState(): State

    /**
     * Обработчик событий от визуальной части. События обрабатываются последовательно.
     */
    protected abstract fun handleIntent(intent: Intent)

    /**
     * Отправка сообщений модели, как от визуальной части, так и от других моделей
     * @param event сообщение для модели
     */
    override fun sendIntent(event: Intent) {
        // Инициализируем слушатель сообщений при первой отправке сообщения, а не в блоке init
        // чтобы было легче мокировать экземпляр SimpleMviViewModel через spy или spyk
        initIntentCollector()

        launch {
            _intent.emit(event)
        }
    }

    /**
     * Получение текущего состояние модели приведя к необходимому типу, null если это не возможно
     */
    inline fun <reified T : State> getStateAs(): T? = viewState.value as? T

    /**
     * Установка нового состояния модели на основе текущего
     * @param reducer лямбда, для преобразования модели
     */
    protected fun setState(reducer: State.() -> State) {
        _viewState.update(reducer)
    }

    /**
     * Установка нового состояния модели на основе текущего приведя состояние к необходимому типу
     * @param reducer лямбда, для преобразования модели
     */
    protected inline fun <reified T : State> setStateAs(crossinline reducer: T.() -> State) {
        getStateAs<T>()?.let {
            setState { reducer(this as T) }
        }
    }

    /**
     * Установка эффекта, который должна выполнить визуальная часть, но не обязательно.
     * Пример: Отображение уведомления, но если экран сейчас скрыт, то и уведомлять необязательно
     * @param builder лямбда, для создания эффекта
     */
    protected fun setEffect(builder: () -> Effect) {
        launch {
            _effect.emit(builder())
        }
    }

    /**
     * Установка эффекта, который должна выполнить визуальная часть обязательно.
     * Пример: Отображение уведомления, при возврате на экран
     * @param builder лямбда, для создания эффекта
     */
    protected fun setBufferedEffect(builder: () -> Effect) {
        launch {
            _effectBuffered.emit(builder())
        }
    }

    private fun initIntentCollector() {
        if (intentCollectorJob?.isActive == true) return
        intentCollectorJob = launch {
            _intent.collect(::handleIntent)
        }
    }

}