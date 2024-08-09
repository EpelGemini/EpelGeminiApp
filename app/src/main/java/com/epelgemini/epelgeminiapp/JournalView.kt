package com.epelgemini.epelgeminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

class JournalView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Jurnal", color = Color.White, fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = { /* Handle menu click */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_menu),
                                contentDescription = "Menu",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color(0xFF424FA2)
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { /* Handle click */ },
                    containerColor = Color(0xFFEE6B66),
                    shape = CircleShape) {
                    Icon(painterResource(id = R.drawable.ic_add), contentDescription = "Add", modifier = Modifier
                        .size(24.dp),
                        tint = Color.White
                    )
                }
            }
        ) { paddingValues ->
            Content(paddingValues)
        }
    }
}

@Composable
fun Content(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_journal_illustration),
            contentDescription = "Journal Illustration",
            modifier = Modifier
                .size(200.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Mulai Jurnal",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Keluarkanlah perasaan dan pengalaman harianmu lewat penulisan pada jurnal. " +
                    "Hal ini akan membantu kamu mencatat peristiwa dan memproses emosi kamu",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewJournalEntryScreen(onSaveClicked: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jurnal Baru", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onSaveClicked) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_check),
                            contentDescription = "Save",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color(0xFF424FA2)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Minggu 4 Agustus",
                color = Color.Red,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Bagaimana perasaanmu hari ini?",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Emoji reaction row (Placeholder)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Add your emojis here
            }

            Text(
                text = "Ceritakan pengalamanmu hari ini",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Journal entry text field (Placeholder)
            TextField(
                value = "",
                onValueChange = { /* Handle text input */ },
                placeholder = {
                    Text("Tuangkanlah isi pikiranmu disini")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun JournalViewPreview() {
    MyApp()
}
