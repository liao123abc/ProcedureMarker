package com.thomasliao.proceduremarker.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.thomasliao.proceduremarker.data.DemoDataProvider
import com.thomasliao.proceduremarker.theme.*

@Composable
fun HomeScreen(appThemeState: MutableState<AppThemeState>) {
    val showMenu = remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.semantics { testTag = "Home Screen" },
        topBar = {
            TopAppBar(
                title = { Text(text = "Compose CookBook") },
                elevation = 8.dp,
                actions = {
                    IconButton(onClick = {
                        appThemeState.value = appThemeState
                            .value.copy(darkTheme = !appThemeState.value.darkTheme)
                    }) {
                    }
                    IconButton(onClick = { showMenu.value = !showMenu.value }) {
                        Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                    }
                },
            )
        },
        content = {
            HomeScreenContent(appThemeState.value.darkTheme, showMenu) { newPalletSelected ->
                // Events can be and should be passed to as upper layer as possible here
                // we are just passing to till HomeScreen.
                appThemeState.value = appThemeState.value.copy(pallet = newPalletSelected)
                showMenu.value = false
            }
        }
    )
}

@Composable
fun HomeScreenContent(
    isDarkTheme: Boolean,
    showMenu: MutableState<Boolean>,
    onPalletChange: (ColorPallet) -> Unit
) {
    val list = remember { DemoDataProvider.homeScreenListItems }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.semantics { testTag = "Home Screen List of entries" }) {
            items(
                items = list,
                itemContent = {
                    HomeScreenListView(it, isDarkTheme)
                })
        }
        PalletMenu(
            modifier = Modifier.align(Alignment.TopEnd),
            showMenu.value,
            onPalletChange
        )
    }
}

@Composable
fun PalletMenu(
    modifier: Modifier,
    showMenu: Boolean,
    onPalletChange: (ColorPallet) -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp)
            .animateContentSize(),
        elevation = 8.dp
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            if (showMenu) {
                MenuItem(green500, "Green") {
                    onPalletChange.invoke(ColorPallet.GREEN)
                }
                MenuItem(purple, "Purple") {
                    onPalletChange.invoke(ColorPallet.PURPLE)
                }
                MenuItem(orange500, "Orange") {
                    onPalletChange.invoke(ColorPallet.ORANGE)
                }
                MenuItem(blue500, "Blue") {
                    onPalletChange.invoke(ColorPallet.BLUE)
                }
            } else {

            }
        }
    }
}

@Composable
fun MenuItem(color: Color, name: String, onPalletChange: () -> Unit) {
    Row(
        modifier = Modifier.padding(8.dp).clickable(onClick = onPalletChange),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Filled.List, tint = color, contentDescription = null)
        Text(text = name, modifier = Modifier.padding(8.dp))
    }
}


@Composable
fun HomeScreenListView(homeScreenItems: HomeScreenItems, isDarkTheme: Boolean) {
    Button(
        onClick = { homeItemClicked(homeScreenItems, isDarkTheme) },
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Text(
            text = homeScreenItems.name,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.button
        )
    }
}

fun homeItemClicked(homeScreenItems: HomeScreenItems, isDarkTheme: Boolean) {
    //TODO pass theme to following screens
}

@Composable
fun PreviewHomeScreen() {
    val state = mutableStateOf(AppThemeState(false, ColorPallet.GREEN))
    HomeScreen(state)
}

