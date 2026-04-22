package com.gartenkalender.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gartenkalender.app.ui.GartenkalenderNavHost
import com.gartenkalender.app.ui.theme.GartenkalenderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GartenkalenderTheme {
                GartenkalenderNavHost()
            }
        }
    }
}
