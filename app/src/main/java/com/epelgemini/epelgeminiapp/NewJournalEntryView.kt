package com.epelgemini.epelgeminiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewJournalEntryView(
    onBackClicked: () -> Unit,
    onSaveClicked: () -> Unit
) {
    val emojis: List<String> = listOf(
        "\uD83D\uDE23",
        "☹️",
        "\uD83D\uDE10",
        "\uD83D\uDE00",
        "\uD83D\uDE01"
    )
    var text by remember {
        mutableStateOf("")
    }
    var selectedEmoji: String? by remember {
        mutableStateOf(null)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jurnal Baru", color = Color.Black, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            modifier = Modifier
                                .size(32.dp),
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onSaveClicked) {
                        Icon(
                            modifier = Modifier
                                .size(32.dp),
                            painter = painterResource(id = R.drawable.ic_check),
                            contentDescription = "Save",
                            tint = Color.Black
                        )
                    }
                },
                modifier = Modifier

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
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Add your emojis here
                emojis.forEach { emoji ->
                    Text(
                        modifier = Modifier
                            .background(
                                color = if (
                                    selectedEmoji == emoji
                                ) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent,
                                shape = CircleShape
                            )
                            .padding(8.dp)
                            .clip(CircleShape)
                            .clickable {
                                selectedEmoji = emoji
                            },
                        text = emoji,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ceritakan pengalamanmu hari ini",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Journal entry text field (Placeholder)
            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
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
private fun JournalViewPreview() {
    NewJournalEntryView(
        onBackClicked = {  },
        onSaveClicked = {  }
    )
}