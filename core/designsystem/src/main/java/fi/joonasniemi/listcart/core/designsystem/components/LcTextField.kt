package fi.joonasniemi.listcart.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LcTextField(
    state: TextFieldState,
    label: String,
    modifier: Modifier = Modifier,
    containerModifier: Modifier = Modifier,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
    enabled: Boolean = true,
    textStyle: TextStyle = TextStyle.Default,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = LcTextFieldDefaults.keyboardOptions,
    onKeyboardAction: KeyboardActionHandler? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    inputTransformation: InputTransformation? = null,
    outputTransformation: OutputTransformation? = null,
    error: Boolean = false,
) {
    InputElement(
        label = label,
        modifier = containerModifier,
    ) {
        BasicTextField(
            modifier = modifier,
            state = state,
            textStyle = textStyle,
            lineLimits = lineLimits,
            inputTransformation = inputTransformation,
            outputTransformation = outputTransformation,
            enabled = enabled,
            decorator = { innerTextField ->
                LcTextFieldDecorator(
                    innerTextField = innerTextField,
                    state = state,
                    placeholder = placeholder,
                    textStyle = textStyle,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    error = error,
                )
            },
            keyboardOptions = keyboardOptions,
            onKeyboardAction = onKeyboardAction,
        )
    }
}

@Composable
fun LcSecureTextField(
    state: TextFieldState,
    label: String,
    enabled: Boolean = true,
    textStyle: TextStyle = TextStyle.Default,
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = LcTextFieldDefaults.keyboardOptions,
    onKeyboardAction: KeyboardActionHandler? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    inputTransformation: InputTransformation? = null,
    textObfuscationMode: TextObfuscationMode = TextObfuscationMode.RevealLastTyped,
    textObfuscationCharacter: Char = LcTextFieldDefaults.OBFUSCATION_CHAR,
    error: Boolean = false,
) {
    InputElement(
        label = label,
    ) {
        BasicSecureTextField(
            state = state,
            textStyle = textStyle,
            inputTransformation = inputTransformation,
            enabled = enabled,
            decorator = { innerTextField ->
                LcTextFieldDecorator(
                    innerTextField = innerTextField,
                    state = state,
                    placeholder = placeholder,
                    textStyle = textStyle,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    error = error,
                )
            },
            keyboardOptions = keyboardOptions,
            onKeyboardAction = onKeyboardAction,
            textObfuscationMode = textObfuscationMode,
            textObfuscationCharacter = textObfuscationCharacter,
        )
    }
}

@Composable
private fun LcTextFieldDecorator(
    innerTextField: @Composable () -> Unit,
    state: TextFieldState,
    placeholder: String,
    textStyle: TextStyle,
    leadingIcon: (@Composable () -> Unit)?,
    trailingIcon: (@Composable () -> Unit)?,
    error: Boolean,
) {
    val borderColor = if (error) {
        MaterialTheme.colorScheme.error
    } else {
        MaterialTheme.colorScheme.onBackground
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(LcTextFieldDefaults.shape)
            .border(2.dp, borderColor, LcTextFieldDefaults.shape)
            .background(MaterialTheme.colorScheme.background)
            .minimumInteractiveComponentSize()
            .padding(vertical = 8.dp),
    ) {
        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onBackground,
        ) {
            if (leadingIcon != null) {
                Box(Modifier.padding(start = 12.dp)) {
                    leadingIcon()
                }
            }
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                contentAlignment = Alignment.CenterStart,
            ) {
                innerTextField()
                if (state.text.isEmpty()) {
                    CompositionLocalProvider(
                        LocalContentColor provides LocalContentColor.current.copy(alpha = 0.78f)
                    ) {
                        Text(
                            text = placeholder,
                            style = textStyle,
                        )
                    }
                }
            }
            if (trailingIcon != null) {
                Box(Modifier.padding(end = 12.dp)) {
                    trailingIcon()
                }
            }
        }
    }
}

object LcTextFieldDefaults {
    val shape = RoundedCornerShape(24.dp)

    val keyboardOptions = KeyboardOptions.Default.copy(
        capitalization = KeyboardCapitalization.Sentences
    )

    const val OBFUSCATION_CHAR = '\u2022'
}

@Preview
@Composable
private fun LcTextFieldPreview() {
    LcTextField(
        state = rememberTextFieldState("This is text"),
        label = "Label",
    )
}

@Preview
@Composable
private fun PlaceholderPreview() {
    LcTextField(
        state = rememberTextFieldState(),
        label = "Label",
        placeholder = "Placeholder",
    )
}

@Preview
@Composable
private fun TrailingIconPreview() {
    LcTextField(
        state = rememberTextFieldState(),
        label = "Label",
        trailingIcon = { Icon(Icons.Default.Add, null) },
    )
}

@Preview
@Composable
private fun LeadingIconPreview() {
    LcTextField(
        state = rememberTextFieldState(),
        label = "Label",
        leadingIcon = { Icon(Icons.Default.Add, null) },
    )
}

@Preview
@Composable
private fun BothIconsPreview() {
    LcTextField(
        state = rememberTextFieldState(),
        label = "Label",
        leadingIcon = { Icon(Icons.Default.Add, null) },
        trailingIcon = { Icon(Icons.Default.Email, null) },
    )
}