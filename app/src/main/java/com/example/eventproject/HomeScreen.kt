package com.example.eventproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    val eventList = listOf(
        Event(1, "Title", "Description 1", "Date 1","Place 1"),
        Event(2, "Title", "Description 2", "Date 2","Place 2"),
        Event(3, "Title", "Description 3", "Date 3","Place 3"),
        Event(4, "Title", "Description 4", "Date 4","Place 4"),
        Event(5, "Title", "Description 5", "Date 5","Place 5"),
        Event(6, "Title", "Description 6", "Date 6","Place 6"),
    )

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Event GET",
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp)
        ) {
            EventList(eventList, navController)
        }
        FloatingActionButton(
            onClick = {
                navController.navigate("addEventScreen")
            },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add", fontSize = 20.sp)
        }
    }
}

@Composable
fun EventList(eventList: List<Event>, navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(eventList) { event ->
            EventCard(event = event, navController)
        }
    }
}

@Composable
fun EventCard(event: Event, navController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                navController.navigate("detailCardScreen")
            },
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Column {

                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Image(painter = painterResource(id = R.drawable.event),
                        contentDescription = null,
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .clickable { }
                    )
                    Column {
                        Text(text = "Title: ${event.title}")
                        Text(text = "Description: ${event.description}")
                        Text(text = "Date: ${event.date}")
                        Text(text = "Place: ${event.place}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Delete",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Red,
                            modifier = Modifier.clickable {
                                showDialog = true
                            }
                        )
                    }
                }

            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Confirmation") },
            text = { Text(text = "Do you really want to delete this item ?") },
            confirmButton = {
                Button(
                    onClick = {
                        // Action de suppression
                        showDialog = false
                    }
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}


data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val place: String
)
