package com.thomasliao.proceduremarker.ui.demoapps

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.thomasliao.proceduremarker.data.DemoDataProvider

@Composable
fun DemoUIList() {
    val demoUis = remember { DemoDataProvider.demoUiList }
    Scaffold(
        modifier = Modifier.semantics { testTag = "Demo UI List Screen" }
    ) {
        LazyColumn {
            items(
                count = demoUis.size,
                itemContent = { index ->
                    val title = demoUis[index]
                    Button(
                        onClick = {
                        },
                        modifier = Modifier.fillMaxWidth().padding(12.dp)
                    ) {
                        Text(text = title, modifier = Modifier.padding(8.dp))
                    }
                })
        }
    }
}

@Composable
fun PreviewDemoUis() {
    DemoUIList()
}