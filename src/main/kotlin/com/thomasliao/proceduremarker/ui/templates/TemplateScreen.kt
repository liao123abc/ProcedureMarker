package com.thomasliao.proceduremarker.ui.templates

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp

@Composable
fun TemplateScreen(darkTheme: Boolean) {
    LazyColumn(modifier = Modifier.fillMaxSize().semantics { testTag = "Template Screen" }) {
        items(templates.size) { index ->
            val template = templates[index]
            Button(
                onClick = {
                },
                modifier = Modifier.fillMaxWidth().padding(12.dp)
            ) {
                Text(text = template, modifier = Modifier.padding(8.dp))
            }
        }
    }

}


val templates = listOf(
    "Login",
    "Profiles",
    "Settings",
    "On-boarding",
    "Empty Screens",
    "Loaders",
    "Canvas Drawing",
    "Animations",
    "Charts",
)