package com.epelgemini.epelgeminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.epelgemini.core_ui.ui.theme.EpelGeminiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EpelGeminiAppTheme {
                AppNavigation()
            }
        }
    }
}