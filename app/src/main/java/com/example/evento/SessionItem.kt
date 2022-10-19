package com.example.evento

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.EventoViewModel
import com.example.evento.ui.theme.CardBackgroundColor
import com.example.evento.ui.theme.SelectedCardBackgroundColor
import com.example.evento.ui.theme.liveIconColor


@Composable
fun SessionItem(
    session: Session,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    eventoViewModel: EventoViewModel
) {

    val cutCornerColor = Color.White
    val sessionCardColor = if (eventoViewModel.readSessionState(session.name) ) SelectedCardBackgroundColor else CardBackgroundColor
    var selectedSession by remember { mutableStateOf(session.isSelected) }
    val context = LocalContext.current

    Log.d("Ahmed0",selectedSession.toString())
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(cornerRadius),
        elevation = 5.dp,
        backgroundColor = if (selectedSession) SelectedCardBackgroundColor else CardBackgroundColor
    ) {
        Box(
            modifier = modifier
                .padding(end = 8.dp)
                .clip(RoundedCornerShape(cornerRadius))
                .background(if (selectedSession) SelectedCardBackgroundColor else CardBackgroundColor)
                .fillMaxSize()
        ) {
            Canvas(modifier = Modifier.matchParentSize()) {
                val clipPath = Path().apply {
                    lineTo(size.width - cutCornerSize.toPx(), 0f)
                    lineTo(size.width, cutCornerSize.toPx())
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                    close()
                }

                clipPath(clipPath) {
                    drawRoundRect(
                        color = if (selectedSession) SelectedCardBackgroundColor else CardBackgroundColor,
                        size = size,
                        cornerRadius = CornerRadius(cornerRadius.toPx())
                    )
                    //this Rect for cutting off part
                    drawRoundRect(
                        color = cutCornerColor,
                        topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                        size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                        cornerRadius = CornerRadius(cornerRadius.toPx())
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 30.dp)
                    .align(Alignment.CenterStart)
                    .clip(RoundedCornerShape(cornerRadius))
                    .background(if (selectedSession) SelectedCardBackgroundColor else CardBackgroundColor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                ) {
                    Text(
                        text =  session.name,
                        style = MaterialTheme.typography.h6,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = session.speaker,
                        style = MaterialTheme.typography.body1,
                        color = Color.White,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "${session.stage} Stage",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.White,
                            modifier = Modifier.padding(end = 5.dp)
                        )
                    }

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)) {
                        Text(
                                text = "${session.start}  -> ",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = liveIconColor,
                                modifier = Modifier.padding(end = 5.dp)
                            )
                            Text(
                                text = session.end,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = liveIconColor
                            )
                        }
                }
            }
            Column(modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.BottomEnd)
                .padding(end = 5.dp, bottom = 5.dp)) {
                IconButton(onClick = {
                    eventoViewModel.updateSessionState(key = session.name , value = !session.isSelected)
                    session.isSelected = ! session.isSelected
                    selectedSession = eventoViewModel.readSessionState(session.name)
                    if (session.isSelected){
                        Toast.makeText(context,"Added to favorite sessions",Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(context,"Removed from favorite sessions",Toast.LENGTH_SHORT).show()
                    }
                }) {
                 Image(imageVector = ImageVector.vectorResource(R.drawable.ic__add), contentDescription = "live session icon")
                }
                Spacer(modifier = Modifier.height(5.dp))

                Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_live),
                    modifier = Modifier.padding(start = 3.dp),
                    contentDescription = "add to favorite sessions",
                tint = Color.White)

            }
        }
    }
}


