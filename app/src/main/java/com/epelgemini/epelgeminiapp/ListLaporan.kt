package com.epelgemini.epelgeminiapp


import android.webkit.WebSettings.TextSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.epelgemini.core_ui.navigation.TopLevelDestination
import io.ktor.client.utils.EmptyContent
import java.time.format.TextStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListLaporan(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Laporan") },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.Help, contentDescription = "Help")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("report_case_route")
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
//        EmptyContent(paddingValues)

        LazyColumn(
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy((-80).dp)
        ) {
            item { Item1(paddingValues) }
            item { Item2(paddingValues) }
        }

    }
}

@Composable
fun EmptyContent(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(25.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Warning,
            contentDescription = "Warning",
            tint = Color.Red,
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = "Buat Perubahan",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "Setiap laporanmu adalah langkah kecil menuju keadilan, Jangan diam, Ayo laporkan sekarang!",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun Item1(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Description of Image",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    )
                    {
                        Text(
                            text = "A-3NFCS2YFT5",
                            style = androidx.compose.ui.text.TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Box(
                            modifier = Modifier
                                .background(Color.Red)
                                .padding(horizontal = 10.dp)
                        ) {
                            Text(
                                text = "Diselidiki",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.Center) // Center text in the box
                            )
                        }
                    }

                    Text(text = "Bullying")
                    Text(text = "1 Agustus 2024 | 12.27 WIB")
                }

            }
        }
    }
}

@Composable
fun Item2(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = CardColors(Color.White, Color.Black, Color.White, Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "A-3NFCS2YFT5", style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Tanggal Pembuatan")
                        Text(text = "Kategori Laporan")
                    }

                    Spacer(modifier = Modifier.width(0.dp))

                    Column {
                        Text(text = ":")
                        Text(text = ":")
                    }

                    Spacer(modifier = Modifier.width(0.dp))

                    Column {
                        Text(text = "11 Agustus 2024")
                        Text(text = "Pelecehan Seksual")
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ListLaporanPreview() {
    val navController = rememberNavController()

    ListLaporan(navController = navController)
}




