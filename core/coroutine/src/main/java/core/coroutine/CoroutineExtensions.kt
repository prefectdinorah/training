package core.coroutine

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Асинхронный вызов в [kotlinx.coroutines.Dispatchers.IO] потоке
 * Приостановка программы в точке вызова и ожидая результат
 * Используется для обработки файлов, интернет запросов
 *
 * @param errorHandler если нужен свой собственный обработчик ошибок,
 *                     но рекомендуется использовать [PresenterCoroutine.errorHandler]
 *
 * @return [T] результат
 */
suspend fun <T> withContextIO(
    errorHandler: CoroutineExceptionHandler? = null,
    block: suspend CoroutineScope.() -> T,
): T {
    return withContext(concatenateContexts(Dispatcher.IO, errorHandler), block)
}

/**
 * Асинхронный вызов в [kotlinx.coroutines.Dispatchers.Default] потоке
 * Приостановка программы в точке вызова и ожидая результат
 * Используется для долгой вычислительной работы в памяти
 *
 * @param errorHandler если нужен свой собственный обработчик ошибок,
 *                     но рекомендуется использовать [PresenterCoroutine.errorHandler]
 *
 * @return [T] результат
 */
suspend fun <T> withContextDefault(
    errorHandler: CoroutineExceptionHandler? = null,
    block: suspend CoroutineScope.() -> T,
): T =
    withContext(concatenateContexts(Dispatcher.Default, errorHandler), block)

/**
 * Отложенный асинхронный вызов в [kotlinx.coroutines.Dispatchers.IO] потоке
 * Нужен когда собирается запрос в одном месте,
 * а запускается в другом, путем вызова метода [Deferred.await]
 * После вызова [Deferred.await] программа приостаноавливается в точке вызова, ожидая результат
 * Используется для обработки файлов, интернет запросов
 *
 * errorHandler бесполезен, так как async.await() перехватывает ошибку
 *
 * @return [Deferred] отложенный вызов
 */
fun <T> CoroutineScope.asyncIO(block: suspend CoroutineScope.() -> T): Deferred<T> =
    async(concatenateContexts(Dispatcher.IO, null), block = block)

/**
 * Отложенный асинхронный вызов в [kotlinx.coroutines.Dispatchers.Default] потоке
 * Нужен когда собирается запрос в одном месте,
 * а запускается в другом, путем вызова метода [Deferred.await]
 * После вызова [Deferred.await] программа приостаноавливается в точке вызова, ожидая результат
 * Используется для долгой вычислительной работы в памяти
 *
 * errorHandler бесполезен, так как async.await() перехватывает ошибку
 *
 * @return [Deferred] отложенный вызов
 */
fun <T> CoroutineScope.asyncDefault(
    block: suspend CoroutineScope.() -> T,
): Deferred<T> =
    async(concatenateContexts(Dispatcher.Default, null), block = block)

/**
 * Синхронный вызов в [kotlinx.coroutines.Dispatchers.IO] потоке
 *
 *
 * @param errorHandler если нужен свой собственный обработчик ошибок,
 *                     но рекомендуется использовать [PresenterCoroutine.errorHandler]
 *
 * @return [Unit]
 */
fun CoroutineScope.launchIO(
    errorHandler: CoroutineExceptionHandler? = null,
    block: suspend CoroutineScope.() -> Unit,
): Job =
    launch(concatenateContexts(Dispatcher.IO, errorHandler), block = block)

/**
 * Синхронный вызов в [kotlinx.coroutines.Dispatchers.Default] потоке
 *
 *
 * @param errorHandler если нужен свой собственный обработчик ошибок,
 *                     но рекомендуется использовать [PresenterCoroutine.errorHandler]
 *
 * @return [Unit]
 */
fun CoroutineScope.launchDefault(
    errorHandler: CoroutineExceptionHandler? = null,
    block: suspend CoroutineScope.() -> Unit,
): Job =
    launch(concatenateContexts(Dispatcher.Default, errorHandler), block = block)

/**
 * Синхронный вызов в [kotlinx.coroutines.Dispatchers.Unconfined] потоке
 *
 *
 * @param errorHandler если нужен свой собственный обработчик ошибок,
 *                     но рекомендуется использовать [PresenterCoroutine.errorHandler]
 *
 * @return [Unit]
 */
fun CoroutineScope.launchUnconfined(
    errorHandler: CoroutineExceptionHandler? = null,
    block: suspend CoroutineScope.() -> Unit,
): Job =
    launch(concatenateContexts(Dispatcher.Unconfined, errorHandler), block = block)

/**
 * Запуск корутины с операторами событий жизненного цикла
 *
 * @param doFirst действие, вызываемое при старте корутины
 * @param doFinally действие, вызываемое при окончании корутины или после обработки ошибки в CoroutineExceptionHandler
 *
 * @return [Unit]
 */
fun CoroutineScope.launchWithEvents(
    context: CoroutineContext? = null,
    doFirst: () -> Unit = {},
    doFinally: () -> Unit = {},
    block: suspend CoroutineScope.() -> Unit,
): Job {
    return launch(concatenateContexts(this.coroutineContext, context)) {
        doFirst()
        block()
    }.apply { invokeOnCompletion { doFinally() } }
}

/**
 * Для обработки кастомных ошибок при запуске корутин
 *
 * Для уменьшения кода, пример
 * launch(CoroutineExceptionHandler(Logger::error))
 */
inline fun CoroutineExceptionHandler(crossinline handler: (Throwable) -> Unit): CoroutineExceptionHandler =
    CoroutineExceptionHandler { _, throwable -> if (throwable !is CancellationException) handler.invoke(throwable) }

private fun concatenateContexts(
    coroutineContext1: CoroutineContext,
    coroutineContext2: CoroutineContext?,
): CoroutineContext {
    if (coroutineContext2 == null) return coroutineContext1
    return coroutineContext1 + coroutineContext2
}

/**
 * Для перехвата ошибок внутри корутины
 *
 * Происходит правильная обработка CancellationException
 */
inline fun <T, R> T.runSuspendCatching(block: T.() -> R): Result<R> =
    runCatching { block() }
        .onFailure {
            if (it is CancellationException)
                throw it
        }