package com.thomasliao.proceduremarker

import AnimationScreen
import androidx.compose.animation.Crossfade
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.thomasliao.proceduremarker.theme.*
import com.thomasliao.proceduremarker.ui.demoapps.DemoUIList
import com.thomasliao.proceduremarker.ui.home.HomeScreen
import com.thomasliao.proceduremarker.ui.learnwidgets.WidgetScreen
import com.thomasliao.proceduremarker.ui.templates.TemplateScreen
import com.thomasliao.proceduremarker.ui.utils.RotateIcon
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import com.intellij.util.ui.JBUI
import java.awt.BorderLayout
import java.awt.Container
import java.awt.Dimension
import javax.swing.JButton

class ProcedureMarkerWindow : ToolWindowFactory, DumbAware {

    @ExperimentalMaterialApi
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        ApplicationManager.getApplication().invokeLater {
            val panel = JBUI.Panels.simplePanel()
            panel.addNavigation()
            val contentFactory = ContentFactory.SERVICE.getInstance()
            val content = contentFactory.createContent(panel, "", false)
            toolWindow.contentManager.addContent(content)
        }
    }
}

@ExperimentalMaterialApi
fun Container.addNavigation() {
    val composePanel = ComposePanel()
    // addind ComposePanel on JFrame
    add(composePanel, BorderLayout.CENTER)
    val appThemeState = mutableStateOf(AppThemeState(false, ColorPallet.GREEN))
    composePanel.setContent {
        BaseView(appThemeState.value) {
            MainAppContent(appThemeState)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun DefaultPreview() {
    val appThemeState = mutableStateOf(AppThemeState(false, ColorPallet.GREEN))
    BaseView(appThemeState.value) {
        MainAppContent(appThemeState)
    }
}

@Composable
fun BaseView(
    appThemeState: AppThemeState,
    content: @Composable () -> Unit
) {
    val color = when (appThemeState.pallet) {
        ColorPallet.GREEN -> green700
        ColorPallet.BLUE -> blue700
        ColorPallet.ORANGE -> orange700
        ColorPallet.PURPLE -> purple700
    }
    ComposeCookBookTheme(darkTheme = appThemeState.darkTheme, colorPallet = appThemeState.pallet) {
        content()
    }
}

@ExperimentalMaterialApi
@Composable
fun MainAppContent(appThemeState: MutableState<AppThemeState>) {
    //Default home screen state is always HOME
    val homeScreenState = rememberSaveable { mutableStateOf(BottomNavType.HOME) }
    val bottomNavBarContentDescription = "Bottom navigation bar with Having actions"

    Column {
        HomeScreenContent(
            homeScreen = homeScreenState.value,
            appThemeState = appThemeState,
            modifier = Modifier.weight(1f)
        )
        BottomNavigationContent(
            modifier = Modifier
                .semantics { contentDescription = bottomNavBarContentDescription }
                .testTag("bottom_navigation_bar"),
            homeScreenState = homeScreenState
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun HomeScreenContent(
    homeScreen: BottomNavType,
    appThemeState: MutableState<AppThemeState>,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Crossfade(homeScreen) { screen ->
            Surface(color = MaterialTheme.colors.background) {
                when (screen) {
                    BottomNavType.HOME -> HomeScreen(appThemeState)
                    BottomNavType.WIDGETS -> WidgetScreen()
                    BottomNavType.ANIMATION -> AnimationScreen()
                    BottomNavType.DEMOUI -> DemoUIList()
                    BottomNavType.TEMPLATE -> TemplateScreen(appThemeState.value.darkTheme)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationContent(
    modifier: Modifier = Modifier,
    homeScreenState: MutableState<BottomNavType>
) {
    var animate by remember { mutableStateOf(false) }
    BottomNavigation(modifier = modifier) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.Home, contentDescription = null) },
            selected = homeScreenState.value == BottomNavType.HOME,
            onClick = {
                homeScreenState.value = BottomNavType.HOME
                animate = false
            },
            label = { Text(text = "home") },
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.Info, contentDescription = null) },
            selected = homeScreenState.value == BottomNavType.WIDGETS,
            onClick = {
                homeScreenState.value = BottomNavType.WIDGETS
                animate = false
            },
            label = { Text(text = "widgets") }
        )
        BottomNavigationItem(
            icon = {
                RotateIcon(
                    state = animate,
                    asset = Icons.Default.PlayArrow,
                    angle = 720f,
                    duration = 2000
                )
            },
            selected = homeScreenState.value == BottomNavType.ANIMATION,
            onClick = {
                homeScreenState.value = BottomNavType.ANIMATION
                animate = true
            },
            label = { Text(text = "animation") }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.Favorite, contentDescription = null) },
            selected = homeScreenState.value == BottomNavType.DEMOUI,
            onClick = {
                homeScreenState.value = BottomNavType.DEMOUI
                animate = false
            },
            label = { Text(text = "demo") }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.Phone, contentDescription = null) },
            selected = homeScreenState.value == BottomNavType.TEMPLATE,
            onClick = {
                homeScreenState.value = BottomNavType.TEMPLATE
                animate = false
            },
            label = { Text(text = "profile") }
        )
    }
}

fun Container.addComposeSample() {
    val composePanel = ComposePanel()
    val northClicks = mutableStateOf(0)
    val westClicks = mutableStateOf(0)
    val eastClicks = mutableStateOf(0)
    add(actionButton("NORTH") { northClicks.value++ }, BorderLayout.NORTH)
    add(actionButton("WEST") { westClicks.value++ }, BorderLayout.WEST)
    add(actionButton("EAST", { eastClicks.value++ }), BorderLayout.EAST)
    add(
        actionButton(
            text = "SOUTH/REMOVE COMPOSE",
            action = {
                remove(composePanel)
            }
        ),
        BorderLayout.SOUTH
    )
    // addind ComposePanel on JFrame
    add(composePanel, BorderLayout.CENTER)
    composePanel.setContent {
        ComposeContent(westClicks, northClicks, eastClicks)
    }
}

fun actionButton(text: String, action: (() -> Unit)? = null): JButton {
    val button = JButton(text)
    button.setToolTipText("Tooltip for $text button.")
    button.setPreferredSize(Dimension(100, 100))
    button.addActionListener { action?.invoke() }
    return button
}

@Composable
fun ComposeContent(westClicks: MutableState<Int>, northClicks: MutableState<Int>, eastClicks: MutableState<Int>) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Counter("West", westClicks)
            Spacer(modifier = Modifier.width(25.dp))
            Counter("North", northClicks)
            Spacer(modifier = Modifier.width(25.dp))
            Counter("East", eastClicks)
        }
    }
}

@Composable
fun Counter(text: String, counter: MutableState<Int>) {
    Surface(
        modifier = Modifier.size(130.dp, 130.dp),
        color = Color(180, 180, 180),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column {
            Box(
                modifier = Modifier.height(30.dp).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "${text}Clicks: ${counter.value}")
            }
            Spacer(modifier = Modifier.height(25.dp))
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { counter.value++ }) {
                    Text(text = text, color = Color.White)
                }
            }
        }
    }
}