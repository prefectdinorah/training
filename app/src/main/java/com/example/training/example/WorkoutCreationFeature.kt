package com.example.training.example

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable
import support.navigation.FeatureNavigator
import support.navigation.rememberFeatureNavigator

// ─── NavKey-и фичи ───────────────────────────────────────────────────────────

@Serializable data object WorkoutCreationEntryKey : NavKey
@Serializable data object WorkoutCreationStepOneKey : NavKey
@Serializable data object WorkoutCreationStepTwoKey : NavKey
@Serializable data object WorkoutCreationSummaryKey : NavKey

// ─── Фича ────────────────────────────────────────────────────────────────────

/**
 * Фича создания тренировки со своим NavDisplay и FeatureNavigator.
 * Dashboard знает только о [WorkoutCreationEntryKey] — точке входа.
 *
 * Граф навигации:
 * StepOne → StepTwo → Summary → onFinish (выход в Dashboard)
 */
@Composable
fun WorkoutCreationFeature(onBack: () -> Unit) {
    val navigator = rememberFeatureNavigator<NavKey>(startKey = WorkoutCreationStepOneKey)

    NavDisplay(
        backStack = navigator.backStack,
        onBack = {
            // Если стек пуст — выходим из фичи обратно в Dashboard
            if (!navigator.back()) onBack()
        },
        entryProvider = entryProvider {
            entry<WorkoutCreationStepOneKey> {
                WorkoutCreationStepOneScreen(
                    onNext = { navigator.navigate(WorkoutCreationStepTwoKey) },
                    onBack = onBack
                )
            }
            entry<WorkoutCreationStepTwoKey> {
                WorkoutCreationStepTwoScreen(
                    onNext = { navigator.navigate(WorkoutCreationSummaryKey) }
                )
            }
            entry<WorkoutCreationSummaryKey> {
                WorkoutCreationSummaryScreen(
                    onFinish = onBack
                )
            }
        }
    )
}

// ─── Экраны фичи ─────────────────────────────────────────────────────────────

@Composable
private fun WorkoutCreationStepOneScreen(onNext: () -> Unit, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Шаг 1 — Выбор типа тренировки")
        Button(onClick = onNext) { Text("Далее") }
        Button(onClick = onBack) { Text("Отмена") }
    }
}

@Composable
private fun WorkoutCreationStepTwoScreen(onNext: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Шаг 2 — Настройка упражнений")
        Button(onClick = onNext) { Text("Далее") }
    }
}

@Composable
private fun WorkoutCreationSummaryScreen(onFinish: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Итог — тренировка готова!")
        Button(onClick = onFinish) { Text("Сохранить и выйти") }
    }
}