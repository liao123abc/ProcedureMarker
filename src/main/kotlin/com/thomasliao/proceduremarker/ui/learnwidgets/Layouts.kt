package com.thomasliao.proceduremarker.ui.learnwidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.thomasliao.proceduremarker.theme.green200
import com.thomasliao.proceduremarker.theme.green500
import com.thomasliao.proceduremarker.theme.green700
import com.thomasliao.proceduremarker.theme.typography

@Composable
fun Layouts() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        TypesOfRows()
        TypeOfColumns()
        TypeOfBoxs()
//        ConstraintLayouts() todo
    }
}

@Composable
fun TypesOfRows() {
    Text(text = "Rows", style = typography.h6, modifier = Modifier.padding(8.dp))
    Text(
        text = "Arrangement.Start ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.End ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.Center ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceAround ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceBetween ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceEvenly ", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        MultipleTexts()
    }
}

@Composable
fun TypeOfColumns() {
    val columnModifier = Modifier.padding(8.dp)
        .fillMaxWidth()
        .height(150.dp)
        .background(Color.LightGray)
    Text(text = "Column", style = typography.h6, modifier = Modifier.padding(8.dp))
    Text(
        text = "Arrangement.Top", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Column(verticalArrangement = Arrangement.Top, modifier = columnModifier) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.Bottom", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Column(verticalArrangement = Arrangement.Bottom, modifier = columnModifier) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.Center + Alignment.CenterHorizontally", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = columnModifier
    ) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceAround", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Column(verticalArrangement = Arrangement.SpaceAround, modifier = columnModifier) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceEvenly", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = columnModifier) {
        MultipleTexts()
    }
    Text(
        text = "Arrangement.SpaceBetween", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = columnModifier) {
        MultipleTexts()
    }
}

@Composable
fun TypeOfBoxs() {
    Text(text = "Box", style = typography.h6, modifier = Modifier.padding(8.dp))
    val boxModifier = Modifier.padding(8.dp)
        .background(Color.LightGray)
        .fillMaxWidth()
        .height(250.dp)
    Text(
        text = "Children with no align", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Box(modifier = boxModifier) {
        Card(
            backgroundColor = green700,
            elevation = 4.dp,
            modifier = Modifier.requiredSize(200.dp)
        ) {}
        Card(
            backgroundColor = green500,
            elevation = 4.dp,
            modifier = Modifier.requiredSize(150.dp)
        ) {}
        Card(
            backgroundColor = green200,
            elevation = 4.dp,
            modifier = Modifier.requiredSize(100.dp)
        ) {}
    }
    Text(
        text = "Children with Topstart, center & bottomEnd align", style = typography.caption,
        modifier = Modifier.padding(8.dp)
    )
    Box(modifier = boxModifier) {
        Card(
            backgroundColor = green700, elevation = 4.dp, modifier = Modifier.requiredSize(
                200
                    .dp
            ).align(Alignment.TopStart)
        ) {}
        Card(
            backgroundColor = green500, elevation = 4.dp, modifier = Modifier.requiredSize(
                150
                    .dp
            ).align(Alignment.Center)
        ) {}
        Card(
            backgroundColor = green200, elevation = 4.dp, modifier = Modifier.requiredSize(
                100
                    .dp
            ).align(Alignment.BottomEnd)
        ) {}
    }
}

@Composable
fun MultipleTexts() {
    Text(text = "First", modifier = Modifier.padding(8.dp))
    Text(text = "Second", modifier = Modifier.padding(8.dp))
    Text(text = "Third", modifier = Modifier.padding(8.dp))

}

@Composable
fun PreviewLayouts() {
    Layouts()
}