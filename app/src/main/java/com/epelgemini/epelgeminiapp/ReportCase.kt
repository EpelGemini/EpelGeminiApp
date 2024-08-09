package com.epelgemini.epelgeminiapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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


@Composable
fun  reportCase(){
    var selectedIndex = remember {
        mutableStateOf<Int?>(null)
    }
    var isButtonEnabled = selectedIndex.value != null
    var redColorUse = Color(0xFFAF4646)



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp))
        {

        }
        Text(
            text = "Pilih Kategori",
            fontSize = 20.sp,
            color = redColorUse,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Yuk lengkapi laporanmu! Detail yang kamu berikan membantu kami memberikan perlindungan yang kamu butuhkan.",
            fontSize = 14.sp,
            fontWeight = FontWeight.Light)
        Spacer(modifier = Modifier.height(16.dp))



        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(490.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(6) { index ->
                ReportCardType(
                    isSelected = selectedIndex.value == index,
                    onClick = { selectedIndex.value = index }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {} ,// goto next question,
            enabled = isButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFAF4646),
                disabledContainerColor = Color.Gray
            ),
        ) {
            Text(text = "Lanjut")
        }


    }
}


@Composable
fun ReportCardType(isSelected: Boolean, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
        ,
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
//            Image(
//                painter = painterResource(id = R.drawable.your_image), // Replace with your image resource
//                contentDescription = null,
//                modifier = Modifier.size(64.dp)
//            )
            //tanpa gambar dlu
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
                text = "Pembulian",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    reportCase()
}