package com.epelgemini.epelgeminiapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThirdPhaseReportCases(){
    var text by remember { mutableStateOf("") }



    val redColorUse = Color(0xFFAF4646)
    val redLightColorUse = Color(0xFFF2CBC6)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(16.dp)
    ){
        ProgressBar(3, redColorUse, redLightColorUse)

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader(text = "Bukti Informasi", color = redColorUse)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Kronologi Kejadian", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text(
                    text = "Catatlah tanggal dan lokasi kejadian, urutan dan detail peristiwa, serta bentuk kekerasan yang dialami. \n" +
                            "\n" +
                            "Kronologi ini akan sangat membantu pihak berwenang dalam memahami situasi dan memastikan tindakan yang tepat dapat diambil. Harap tuliskan dengan jujur dan detail sesuai kemampuanmu, serta ingat bahwa dukungan selalu tersedia untuk kamu.",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(252.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(lineHeight = 24.sp),
            singleLine = false,
            maxLines = 15
        )

    }

}

@Preview(showBackground = true)
@Composable
fun ThirdPhaseReportCasesPreview(){
    ThirdPhaseReportCases()

}