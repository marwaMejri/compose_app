package com.example.compose_example.features.authentification.presentation.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_example.R
import com.example.compose_example.core.utils.titleTextStyle
import com.example.compose_example.features.authentification.data.AuthenticationState
import com.example.compose_example.features.authentification.presentation.AuthenticationEvent


@Composable
fun RememberAndForgetWidget(
    modifier: Modifier = Modifier,
    viewState: AuthenticationState,
    events: (event: AuthenticationEvent) -> Unit,
    navigateToForget: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 25.dp,
                vertical = 10.dp,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        RememberWidget(viewState, events)
        Text(
            text = stringResource(id = R.string.forget_password_text),
            Modifier.clickable { navigateToForget() },
            style = titleTextStyle.copy(
                color = colorResource(id = R.color.light_pink),
                fontSize = 14.sp,
            ),
        )
    }
}

@Composable
private fun RememberWidget(
    viewState: AuthenticationState,
    events: (event: AuthenticationEvent) -> Unit,
) {
    Switch(
        checked = viewState.toggleRemember,
        colors = SwitchDefaults.colors(
            checkedThumbColor = colorResource(id = R.color.dark_red),
            uncheckedThumbColor = Color.Gray,
            uncheckedTrackColor = Color.Gray.copy(alpha = 0.1f),
            checkedTrackColor = colorResource(id = R.color.dark_red).copy(alpha = 0.2f),
        ),
        onCheckedChange = {
            events(
                AuthenticationEvent.OnRememberPressed(it),
            )
        })

}