package com.example.composeclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.composeclock.composables.Clock
import com.example.composeclock.ui.theme.ComposeClockTheme
import com.example.composeclock.util.TimeProvider
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeClockTheme {
                var time by remember {
                    mutableStateOf(TimeProvider.getCurrentTime())
                }
                LaunchedEffect(0) {
                    while (true) {
                        time = TimeProvider.getCurrentTime()
                        delay(1000)
                    }
                }
                Clock(time = time)
            }
        }
    }
}
