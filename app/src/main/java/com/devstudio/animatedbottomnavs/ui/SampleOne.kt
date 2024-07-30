package com.devstudio.animatedbottomnavs.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devstudio.animatedbottomnavs.ui.theme.OrangeDark
import com.devstudio.animatedbottomnavs.ui.theme.OrangeLite

data class Screen(val title: String, val icon: ImageVector)

val screens = listOf<Screen>(
    Screen("Home", Icons.Default.Home),
    Screen("Profile", Icons.Default.Person),
    Screen("Search", Icons.Default.Search),
    Screen("Settings", Icons.Default.Settings),
)

@Composable
fun SampleOne() {
    var currentScreen by rememberSaveable { mutableStateOf("Search") }
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
        targetValue = MaterialTheme.colorScheme.background,
        label = "",
        animationSpec = tween(durationMillis = 500)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .background(
                if (isSystemInDarkTheme()) OrangeDark else OrangeLite,
                RoundedCornerShape(25.dp)
            )
            .padding(start = 18.dp, end = 18.dp, bottom = 12.dp, top = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        screens.forEach { screen ->
            if (screen.title == currentScreen) {
                Row(
                    modifier = Modifier
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessHigh
                            )
                        )
                        .background(color.value, RoundedCornerShape(25.dp))
                        .padding(vertical = 8.dp, horizontal = 10.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onItemSelected(screen.title) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title,
                        tint = if (isSystemInDarkTheme()) OrangeDark else OrangeLite
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = screen.title,
                        fontSize = 16.sp,
                        color = if (isSystemInDarkTheme()) OrangeDark else OrangeLite
                    )
                }
            } else {
                Icon(
                    modifier = Modifier
                        .animateContentSize()
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { onItemSelected(screen.title) }),
                    imageVector = screen.icon,
                    contentDescription = screen.title,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSample1() {
    SampleOne()
}