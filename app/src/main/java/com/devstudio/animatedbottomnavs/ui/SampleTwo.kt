package com.devstudio.animatedbottomnavs.ui

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SampleTwo() {
    var currentScreen by rememberSaveable { mutableStateOf("Under development if you have any idea then implement it") }
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentScreen = currentScreen,
                onItemSelected = { currentScreen = it }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = currentScreen, fontSize = 32.sp)

        }
    }
}


@Composable
private fun BottomNavigationBar(currentScreen: String, onItemSelected: (String) -> Unit) {

    val color = animateColorAsState(
        targetValue = Color.White,
        label = "",
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(155.dp)
            .navigationBarsPadding()
            .graphicsLayer {
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                clip = true
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(MaterialTheme.colorScheme.background)
                .align(Alignment.TopCenter)
        ) {}
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .background(Color.Blue, RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
                .graphicsLayer {
                    shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
                    clip = true
                }
        ) {}

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            screens.forEach { screen ->
                if (screen.title == currentScreen) {
                    Box(
                        modifier = Modifier
                            .animateContentSize()
                            .padding(8.dp)
                            .wrapContentSize()
                            .border(
                                4.dp,
                                MaterialTheme.colorScheme.background,
                                RoundedCornerShape(25.dp)
                            )
                            .background(Color.Blue, RoundedCornerShape(25.dp))
                            .align(Alignment.Top)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { onItemSelected(screen.title) }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier

                                .padding(12.dp),
                            imageVector = screen.icon,
                            contentDescription = screen.title,
                            tint = Color.White
                        )
                    }
                } else {
                    Icon(
                        modifier = Modifier
                            .animateContentSize()
                            .align(Alignment.Bottom)
                            .padding(8.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { onItemSelected(screen.title) }
                            ),
                        imageVector = screen.icon,
                        contentDescription = screen.title,
                        tint = Color.White
                    )
                }
            }
        }

    }
}
