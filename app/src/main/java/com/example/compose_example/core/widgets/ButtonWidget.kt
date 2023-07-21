package com.example.compose_example.core.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.compose_example.R
import com.example.compose_example.core.utils.subTitleTextStyle


@Composable
fun RoundedButton(
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = subTitleTextStyle,
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.dark_red))
    ) {
        Text(
            buttonText, style = textStyle, textAlign = TextAlign.Center,
            modifier = Modifier.padding(5.dp),
        )
    }
}