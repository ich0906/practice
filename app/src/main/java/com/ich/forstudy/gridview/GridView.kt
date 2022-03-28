package com.ich.forstudy.gridview

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ich.forstudy.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridViewAnimation(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "GridView Animation",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2)
        ){
            items(30){
                GridItemAnimation()
            }
        }
    }
}

@Composable
fun GridItemAnimation(){
    val animatedProgress = remember { Animatable(initialValue = -300f) }
    val opacityProgress = remember { Animatable(initialValue = 0f) }
    LaunchedEffect(Unit){
        animatedProgress.animateTo(
            targetValue = 0f,
            animationSpec = tween(300, easing = LinearEasing)
        )
        opacityProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(600)
        )
    }

    val animatedModifier = Modifier
        .padding(8.dp)
        .graphicsLayer(translationX = animatedProgress.value)
        .alpha(opacityProgress.value)
    
    Card(
        modifier = Modifier
            .padding(10.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = animatedModifier
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_photo_24),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(65.dp)
                    .padding(4.dp)
                    .clip(CircleShape),
                colorFilter = ColorFilter.tint(Color.Black)
            )

            Text(
                text = "Text",
                style = MaterialTheme.typography.h6.copy(fontSize = 16.sp)
            )
        }
    }
}