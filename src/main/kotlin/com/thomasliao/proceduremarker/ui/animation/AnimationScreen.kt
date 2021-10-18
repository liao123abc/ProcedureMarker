import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import com.thomasliao.proceduremarker.ui.utils.RotateIcon
import com.thomasliao.proceduremarker.ui.utils.TitleText
import com.thomasliao.proceduremarker.ui.animation.AnimatableSuspendedAnimations
import com.thomasliao.proceduremarker.ui.animation.AnimationsWithVisibilityApi
import com.thomasliao.proceduremarker.ui.animation.TransitionAnimationsWithMultipleStates

enum class MyAnimationState {
    START, MID, END
}

@Composable
fun AnimationScreen() {
    var animateIcon by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.semantics { testTag = "Animation Screen" },
        topBar = {
            TopAppBar(
                title = { Text(text = "Animations") },
                elevation = 8.dp,
                navigationIcon = {
                    IconButton(onClick = { animateIcon = !animateIcon }) {
                        RotateIcon(
                            state = animateIcon,
                            asset = Icons.Filled.PlayArrow,
                            angle = 1440f,
                            duration = 3000
                        )
                    }
                }
            )
        },
        content = {
            AnimationScreenContent()
        }
    )
}

@Composable
fun AnimationScreenContent() {
    LazyColumn(
        state = rememberLazyListState(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {  Spacer(modifier = Modifier.padding(4.dp)) }
        item { TitleText(title = "State Animations(Fire and forget)") }
        item { AnimationsWithVisibilityApi() }
        item { AnimatableSuspendedAnimations() }
        item { TransitionAnimationsWithMultipleStates() }
        item { Spacer(modifier = Modifier.padding(100.dp)) }
    }
}
