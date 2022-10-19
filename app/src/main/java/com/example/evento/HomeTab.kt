package com.example.evento

import android.hardware.Camera.open
import android.os.ParcelFileDescriptor.open
import android.system.Os.open
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.EventoViewModel
import com.example.evento.ui.theme.CardBackgroundColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.io.InputStreamReader


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeTab(eventoViewModel: EventoViewModel) {

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val eventInfoParser = EventInfoParser()
    val file = InputStreamReader(context.assets.open("droidcon.csv"))

    val data = eventInfoParser.parse(file)

    val pages = data.second

    Column(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = CardBackgroundColor,
            contentColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {

            pages.forEachIndexed { index, day ->
                Tab(
                    text = { Text(day) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
                )
            }
        }

        HorizontalPager(
            count = pages.size,
            state = pagerState
        ) { page ->
            when (page) {
                0 -> {
                    val sessions = data.first.filter {
                        it.day == pages.elementAt(0)
                    }
                    eventPage(sessions,eventoViewModel)
                }
                1 -> {
                    val sessions = data.first.filter {
                        it.day == pages.elementAt(1)
                    }
                    eventPage(sessions,eventoViewModel)
                }
                else ->{
                    val sessions = data.first.filter {
                        it.day == pages.elementAt(page)
                    }
                    eventPage(sessions, eventoViewModel)
            }

            }
        }


        }
}