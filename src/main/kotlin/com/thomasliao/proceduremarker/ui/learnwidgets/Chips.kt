package com.thomasliao.proceduremarker.ui.learnwidgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.thomasliao.proceduremarker.theme.typography
import com.thomasliao.proceduremarker.ui.utils.SubtitleText

@Composable
fun Chips() {
    // There is no in-built chips but you can make yours like below
    Text(text = "Custom Chips", style = typography.h6, modifier = Modifier.padding(8.dp))
    SubtitleText(subtitle = "Custom chips with surface")
    SubtitleText(subtitle = "Buttons with circle clipping.")
    Row(modifier = Modifier.padding(8.dp)) {
        Button(
            onClick = {},
            modifier = Modifier.padding(8.dp).clip(CircleShape)
        ) {
            Text(text = "Chip button")
        }
        Button(
            onClick = {},
            enabled = false,
            modifier = Modifier.padding(8.dp).clip(CircleShape)
        ) {
            Text(text = "Disabled chip")
        }
    }
}


//Inspired from jetcaster sample. I hope compose can add simple Chip UI element that can
// support images or icons with multiple states.
@Composable
private fun CustomImageChip(
    text: String,
    imageId: Int,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        color = when {
            selected -> MaterialTheme.colors.primary
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colors.onPrimary
            else -> Color.LightGray
        },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> MaterialTheme.colors.primary
                else -> Color.LightGray
            }
        ),
        modifier = modifier
    ) {
        Row(modifier = Modifier) {
            Text(
                text = text,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}