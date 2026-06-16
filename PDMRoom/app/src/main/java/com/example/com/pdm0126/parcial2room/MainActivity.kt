package com.example.com.pdm0126.parcial2room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation3.ui.NavDisplay
import com.example.com.pdm0126.parcial2room.navigation.RankeUCA_App
import com.example.com.pdm0126.parcial2room.ui.theme.Parcial2RoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Parcial2RoomTheme {
                RankeUCA_App()
            }
        }
    }
}
