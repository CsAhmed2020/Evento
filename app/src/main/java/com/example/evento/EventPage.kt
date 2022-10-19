package com.example.evento

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.EventoViewModel

@Composable
fun eventPage(sessions: List<Session>,eventoViewModel: EventoViewModel){

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(sessions){ item ->
                SessionItem(session = item, eventoViewModel = eventoViewModel)
            }

        }

    }

}