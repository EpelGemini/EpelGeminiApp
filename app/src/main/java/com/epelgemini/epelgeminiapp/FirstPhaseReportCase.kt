package com.epelgemini.epelgeminiapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

enum class ReportType(val id: Int, val title: String) {
    BULLYING(1, "Pembulian"),
    HARASSMENT(2, "Pelecehan"),
    THEFT(3, "Pencurian"),
    VANDALISM(4, "Vandalisme"),
    FRAUD(5, "Penipuan"),
    ABUSE(6, "Penganiayaan")
}

@Composable
fun FirstPhaseReportCase() {
    val selectedIndex = remember { mutableStateOf<ReportType?>(null) }
    val isButtonEnabled = selectedIndex.value != null
    val redColorUse = Color(0xFFAF4646)
    val redLightColorUse = Color(0xFFF2CBC6)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(121.dp, 12.dp)
                    .background(color = redColorUse, shape = RoundedCornerShape(12.dp))
            )
            Box(
                modifier = Modifier
                    .size(121.dp, 12.dp)
                    .background(color = redLightColorUse, shape = RoundedCornerShape(12.dp))
            )
            Box(
                modifier = Modifier
                    .size(121.dp, 12.dp)
                    .background(color = redLightColorUse, shape = RoundedCornerShape(12.dp))
            )
        }
        Text(
            text = "Pilih Kategori",
            fontSize = 20.sp,
            color = redColorUse,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Yuk lengkapi laporanmu! Detail yang kamu berikan membantu kami memberikan perlindungan yang kamu butuhkan.",
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(490.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(ReportType.values().size) { index ->
                val reportType = ReportType.values()[index]
                ReportCardType(
                    reportType = reportType,
                    isSelected = selectedIndex.value == reportType,
                    onClick = { selectedIndex.value = reportType }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

                selectedIndex.value?.let {
                    // navController?.navigate("nextPage/${it.id}")
                }
            },
            enabled = isButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFAF4646),
                disabledContainerColor = Color.LightGray
            ),
        ) {
            Text(text = "Lanjut")
        }
    }
}

@Composable
fun ReportCardType(reportType: ReportType, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = if (isSelected) BorderStroke(2.dp, Color(0xFFAF4646)) else null, // Red outline if selected
        modifier = Modifier
            .size(width = 180.dp, height = 141.dp)
            .clickable { onClick() }
            .shadow(elevation = 7.dp, shape = RoundedCornerShape(8.dp)) // Add shadow with rounded corners
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = reportType.title,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstPhaseReportCase()
}
