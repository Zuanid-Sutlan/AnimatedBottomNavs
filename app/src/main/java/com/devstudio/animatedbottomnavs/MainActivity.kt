package com.devstudio.animatedbottomnavs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.devstudio.animatedbottomnavs.ui.SampleOne
import com.devstudio.animatedbottomnavs.ui.navigation.NavigationGraph
import com.devstudio.animatedbottomnavs.ui.navigation.Sample1
import com.devstudio.animatedbottomnavs.ui.navigation.Sample2
import com.devstudio.animatedbottomnavs.ui.theme.AnimatedBottomNavsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AnimatedBottomNavsTheme {
                NavigationGraph(navController = navController)
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Button(onClick = { navController.navigate(Sample1) }) {
                Text(text = "Sample 1")
            }
            Button(onClick = { navController.navigate(Sample2) }) {
                Text(text = "Sample 2")
            }
        }

    }
}
