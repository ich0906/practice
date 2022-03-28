package com.ich.forstudy.swipe_button

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

private val GreenColor = Color(0xFF2FD286)

@Composable
private fun DraggableControl(
    modifier: Modifier,
    progress: Float
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .shadow(
                elevation = 2.dp,
                CircleShape,
                clip = false
            )
            .background(Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        val isConfirmed = derivedStateOf { progress >= 0.8f }

        Crossfade(targetState = isConfirmed.value) {
            if (it) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Confirm Icon",
                    tint = GreenColor
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Forward Icon",
                    tint = GreenColor
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ConfirmationButton(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit
) {
    val width = 350.dp
    val dragSize = 50.dp
    val swipeableState = rememberSwipeableState(initialValue = ConfirmationState.DEFAULT)
    val sizePx = with(LocalDensity.current) {
        (width - dragSize).toPx()
    }
    val anchors = mapOf(0f to ConfirmationState.DEFAULT, sizePx to ConfirmationState.CONFIRMED)
    val progress = derivedStateOf {
        if (swipeableState.offset.value == 0f) 0f else swipeableState.offset.value / sizePx
    }

    LaunchedEffect(progress.value){
        if(progress.value == 1f){
            onConfirm()
        }
    }

    Box(
        modifier = modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .background(GreenColor, RoundedCornerShape(dragSize))
    ) {
        Column(
            Modifier
                .align(Alignment.Center)
                .alpha(1f - progress.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Main Text",
                color = Color.White,
                fontSize = 18.sp
            )
            Text(
                text = "Sub Text",
                color = Color.White,
                fontSize = 12.sp
            )
        }

        DraggableControl(
            modifier = Modifier
                .offset {
                    IntOffset(swipeableState.offset.value.roundToInt(), 0)
                }
                .size(dragSize),
            progress = progress.value
        )
    }
}

enum class ConfirmationState{
    DEFAULT,
    CONFIRMED
}