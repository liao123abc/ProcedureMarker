package com.thomasliao.proceduremarker.ui.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.thomasliao.proceduremarker.theme.green200
import com.thomasliao.proceduremarker.theme.green500
import com.thomasliao.proceduremarker.ui.utils.SubtitleText
import com.thomasliao.proceduremarker.ui.utils.TitleText

@Composable
fun AnimationsWithVisibilityApi() {
    Spacer(modifier = Modifier.height(50.dp))
    TitleText(title = "Using Visibility Apis(Experimental)")
    AnimateVisibilityAnim()
    Divider()
    AnimateVisibilityWithSlideInOutSample()
    Divider()
    VisibilityAnimationWithShrinkExpand()
    Divider()
    AnimateContentSize()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateVisibilityAnim() {
    SubtitleText(subtitle = "Animate view visibility via  AnimateVisibility()")
    var expanded by remember { mutableStateOf(true) }
    FloatingActionButton(
        onClick = { expanded = !expanded },
        modifier = Modifier.padding(16.dp)
    ) {
        Row(Modifier.padding(start = 16.dp, end = 16.dp)) {
            AnimatedVisibility(
                expanded,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(modifier = Modifier.padding(start = 8.dp), text = "Tweet")
            }
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VisibilityAnimationWithShrinkExpand() {
    SubtitleText(subtitle = "Visibility animation with expand/shrink as enter/exit")
    var visibility by remember { mutableStateOf(true) }

    Row(
        Modifier
            .padding(12.dp)
            .width(200.dp)
            .height(60.dp)
            .background(green200)
            .clickable(onClick = { visibility = !visibility })
    ) {
        AnimatedVisibility(
            visibility,
            modifier = Modifier.align(Alignment.CenterVertically),
            enter = expandIn(expandFrom=Alignment.Center, initialSize = { fullSize: IntSize -> fullSize * 4 }),
            exit = shrinkOut(shrinkTowards=Alignment.Center)
        ) {
            Button(modifier = Modifier.padding(start = 12.dp), onClick = {visibility = !visibility}) {
                Text(text = "Shrink/Expand")
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateVisibilityWithSlideInOutSample() {
    SubtitleText(subtitle = "Visibility animation with fade and slide as enter/exit")
    var visibility by remember { mutableStateOf(true) }
    Row(
        Modifier
            .padding(12.dp)
            .width(200.dp)
            .height(60.dp)
            .background(green500)
            .clickable(onClick = { visibility = !visibility })
    ) {
//        AnimatedVisibility(
//            visibility,
//            enter = slideIn({ IntOffset(0, 120) }, tween(500, easing = LinearOutSlowInEasing)) + fadeIn(1f,  tween(500, easing = LinearOutSlowInEasing)),
//            exit = slideOut(
//                { IntOffset(0, 120) },
//                tween(500, easing = FastOutSlowInEasing)
//            ) + fadeOut(0.5f,  tween(500, easing = LinearOutSlowInEasing))
//        ) {
//            // Content that needs to appear/disappear goes here:
//            Text("Tap for Sliding animation")
//        }
    }
}

@Composable
fun AnimateContentSize() {
    SubtitleText(subtitle = "Using Modifier.animateContentSize() to animate content change")
    var count by remember { mutableStateOf(1) }

    Row(modifier = Modifier
        .animateContentSize()
        .clickable { if (count < 10) count += 3 else count = 1 }) {
        (0..count).forEach {
            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)
        }
    }
}
