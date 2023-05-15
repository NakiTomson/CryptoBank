package com.example.presentation.core_compose

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.core.SideEffect
import com.example.presentation.ui.authentication.log_in.event.SignInSideEffect
import presentation.R

@Composable
@Preview(showSystemUi = true)
private fun CustomAlertPreview() {
    CustomAlert(rememberCustomAlertState(true))
}

@Composable
fun CustomAlert(
    state: CustomAlertState = rememberCustomAlertState(false),
) {
    if (state.isVisible) {
        AlertDialog(
            onDismissRequest = {
                state.onDismiss.invoke(state.negativeSideEffect)
            },
            title = { Text(state.title) },
            text = { Text(state.description) },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Button(
                        onClick = {
                            state.onPositive.invoke(state.positiveSideEffect)
                            state.onCompiled.invoke()
                        },
                        Modifier
                            .weight(1f)
                            .padding(end = 5.dp)
                    ) {
                        Text(text = state.positive, color = Color.Black)
                    }
                    Button(
                        onClick = {
                            state.onDismiss.invoke(state.negativeSideEffect)
                            state.onCompiled.invoke()
                        },
                        Modifier
                            .weight(1f)
                            .padding(start = 5.dp)
                    ) {
                        Text(text = state.negative, color = Color.Black)
                    }
                }
            }
        )
    }
}

@Composable
fun rememberUpdatedStateCustomAlertState(
    isVisible: Boolean,
    @StringRes titleRes: Int = R.string.create_now,
    @StringRes descriptionRes: Int = R.string.on_boarding_next_action,
    title: String = stringResource(titleRes),
    description: String = stringResource(descriptionRes),
    positiveValue: String = stringResource(id = R.string.ok),
    negativeValue: String = stringResource(id = R.string.cancel),
    onPositive: (SideEffect?) -> Unit = {},
    onDismiss: (SideEffect?) -> Unit = {},
    onCompiled: () -> Unit = {},
): CustomAlertState {
    val state = CustomAlertState(
        isVisibleValue = isVisible,
        titleValue = title,
        descriptionValue = description,
        onPositive = onPositive,
        onDismiss = onDismiss,
        onCompiled = onCompiled,
        positiveValue = positiveValue,
        negativeValue = negativeValue,
    )
    return rememberUpdatedState(state).value
}

@Composable
fun rememberCustomAlertState(
    isVisible: Boolean = false,
    @StringRes titleRes: Int = R.string.create_now,
    @StringRes descriptionRes: Int = R.string.on_boarding_next_action,
    title: String = stringResource(titleRes),
    description: String = stringResource(descriptionRes),
    positiveValue: String = stringResource(id = R.string.ok),
    negativeValue: String = stringResource(id = R.string.cancel),
    onPositive: (SideEffect?) -> Unit = {},
    onDismiss: (SideEffect?) -> Unit = {},
    onCompiled: () -> Unit = {},
): CustomAlertState {
    val state = rememberSaveable(isVisible, description, title, saver = CustomAlertState.Saver) {
        CustomAlertState(
            isVisibleValue = isVisible,
            titleValue = title,
            descriptionValue = description,
            onPositive = onPositive,
            onDismiss = onDismiss,
            onCompiled = onCompiled,
            positiveValue = positiveValue,
            negativeValue = negativeValue
        )
    }
    return state
}

@Composable
fun rememberCustomAlertState(
    state: CustomAlertState,
    onPositive: (SideEffect?) -> Unit = {},
    onDismiss: (SideEffect?) -> Unit = {},
    onCompiled: () -> Unit = {},
): CustomAlertState {
    val positiveValue = state.positive.ifBlank { stringResource(id = R.string.ok) }
    val negativeValue = state.negative.ifBlank { stringResource(id = R.string.cancel) }
    val newState = state.copy(
        onPositive = onPositive,
        onDismiss = onDismiss,
        onCompiled = onCompiled,
        positiveValue = positiveValue,
        negativeValue = negativeValue,
    )
    return rememberSaveable(newState, saver = CustomAlertState.Saver) { newState }
}


data class CustomAlertState(
    private val isVisibleValue: Boolean = false,
    private val titleValue: String = "",
    private val descriptionValue: String = "",
    private val positiveValue: String = "",
    private val negativeValue: String = "",
    val positiveSideEffect: SideEffect? = null,
    val negativeSideEffect: SideEffect? = null,
    val onPositive: (SideEffect?) -> Unit = {},
    val onDismiss: (SideEffect?) -> Unit = {},
    val onCompiled: () -> Unit = {},
) {
    var isVisible by mutableStateOf(isVisibleValue)
    var title by mutableStateOf(titleValue)
    var description by mutableStateOf(descriptionValue)

    var positive by mutableStateOf(positiveValue)
    var negative by mutableStateOf(negativeValue)
    
    fun copyDef(
        isVisibleValue: Boolean = false,
        titleValue: String = "",
        descriptionValue: String = "",
        positiveValue: String = "",
        negativeValue: String = "",
        positiveSideEffect: SideEffect? = null,
        negativeSideEffect: SideEffect? = null,
        onPositive: (SideEffect?) -> Unit = {},
        onDismiss: (SideEffect?) -> Unit = {},
        onCompiled: () -> Unit = {},
    ): CustomAlertState {
        return this.copy(
            isVisibleValue = isVisibleValue,
            titleValue = titleValue,
            descriptionValue = descriptionValue,
            positiveValue = positiveValue,
            negativeValue = negativeValue,
            positiveSideEffect = positiveSideEffect,
            negativeSideEffect = negativeSideEffect,
            onPositive = onPositive,
            onDismiss = onDismiss,
            onCompiled = onCompiled,
        )
    }

    companion object {
        val Saver: Saver<CustomAlertState, *> = listSaver(
            save = { listOf(it.isVisible, it.title, it.description) },
            restore = {
                CustomAlertState(
                    isVisibleValue = it[0] as Boolean,
                    titleValue = it[1] as String,
                    descriptionValue = it[2] as String,
                )
            }
        )
    }
}
