package com.example.evento

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.evento.ui.theme.EventoTheme
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.EventoViewModel
import com.example.evento.ui.theme.CardBackgroundColor
import com.example.evento.ui.theme.SelectedCardBackgroundColor

class MainActivity : ComponentActivity() {
    lateinit var viewModel : EventoViewModel

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            viewModel = ViewModelProvider(this).get(EventoViewModel::class.java)

            EventoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = SelectedCardBackgroundColor)
                        ) {
                            Icon(
                                Icons.Default.Notifications,
                                contentDescription = "notifications icon",
                                tint = Color.White
                            )
                            Text(text = "You will receive notifications during the event",
                                color = Color.White,
                            modifier = Modifier.padding(start = 1.dp))
                        }

                        HomeTab(viewModel)
                    }
                }
            }
        }
    }
}

