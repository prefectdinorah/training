package com.example.training.router

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey

class MainRouter<T : NavKey>(private val startKey: T) {

    private var mainRouterStacks: HashMap<T, SnapshotStateList<T>> = hashMapOf(
        startKey to mutableStateListOf(startKey)
    )

    var mainKey by mutableStateOf<T>(startKey)
        private set

    val backStack = mutableStateListOf<T>(startKey)

    fun switchTopLevel(key: T) {
        if (mainRouterStacks[key] == null) {
            mainRouterStacks[key] = mutableStateListOf(key)
        }
        mainKey = key
        updateBackStack()
    }

    fun add(key: T) {
        mainRouterStacks[mainKey]?.add(key)
        updateBackStack()
    }

    fun removeLast() {
        val currentStack = mainRouterStacks[mainKey] ?: return

        if (currentStack.size > 1) {
            currentStack.removeLastOrNull()
        } else if (mainKey != startKey) {
            mainKey = startKey
        }
        updateBackStack()
    }

    fun replaceStack(vararg keys: T) {
        mainRouterStacks[mainKey] = mutableStateListOf(*keys)
        updateBackStack()
    }

    private fun updateBackStack() {
        backStack.clear()
        val currentStack = mainRouterStacks[mainKey] ?: emptyList()

        if (mainKey == startKey) {
            backStack.addAll(currentStack)
        } else {
            val startStack = mainRouterStacks[startKey] ?: emptyList()
            backStack.addAll(startStack + currentStack)
        }
    }
}

@Composable
fun rememberMainRouter(
    startKey: NavKey
): MainRouter<NavKey> = remember {
    MainRouter(startKey = startKey)
}
