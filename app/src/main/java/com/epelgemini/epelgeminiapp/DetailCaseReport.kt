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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCaseReport(){

    var name by remember { mutableStateOf("") }
    var noTelpOrEmail by remember { mutableStateOf("") }
    var domisili by remember { mutableStateOf("") }
    var isTextFieldEnabled by remember { mutableStateOf(false) }
    var disabilityExplanation by remember { mutableStateOf("") }
    var isDisabled by remember { mutableStateOf<String?>(null) }
    var expandedPelapor by remember { mutableStateOf(false) }
    var expandedTerduga by remember { mutableStateOf(false) }
    var selectedGenderPelapor by remember { mutableStateOf("") }
    var selectedGenderTerduga by remember { mutableStateOf("") }

    val provinsiList = listOf(
        "Aceh", "Sumatera Utara", "Sumatera Barat", "Riau", "Kepulauan Riau", "Jambi",
        "Sumatera Selatan", "Bangka Belitung", "Bengkulu", "Lampung", "DKI Jakarta",
        "Jawa Barat", "Banten", "Jawa Tengah", "DI Yogyakarta", "Jawa Timur",
        "Bali", "Nusa Tenggara Barat", "Nusa Tenggara Timur", "Kalimantan Barat",
        "Kalimantan Tengah", "Kalimantan Selatan", "Kalimantan Timur",
        "Kalimantan Utara", "Sulawesi Utara", "Gorontalo", "Sulawesi Tengah",
        "Sulawesi Barat", "Sulawesi Selatan", "Sulawesi Tenggara", "Maluku",
        "Maluku Utara", "Papua Barat", "Papua", "Papua Tengah", "Papua Pegunungan",
        "Papua Selatan", "Papua Barat Daya"
    )
    var text by remember { mutableStateOf("") }


    var namaTerduga by remember { mutableStateOf("") }
    var domisiliTerduga by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    val redColorUse = Color(0xFFAF4646)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "ID Laporan  : " + "A-3NFCS2YFTC", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(6.dp))

        Text(text = "Waktu          : " + "1 Agustus 2024 12.27WIB", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        InfoCard(imageRes = R.drawable.grammar_correction, title = "Dalam Proses pemeriksaan", description = "Laporanmu sedang diselidiki, mohon menunggu update situasi lewat Safey" )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Kategori",
            fontSize = 20.sp,
            color = redColorUse,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        CategoryReport(reportType = ReportType.BULLYING)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Detail Informasi",
            fontSize = 20.sp,
            color = redColorUse,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Nama Pelapor", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(4.dp))

        CustomTextField(value = name, onValueChange = { name = it }, label = "Masukkan Nama Pelapor")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Jenis Kelamin", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        GenderSelection(selectedOption = {selectedGenderPelapor}, onOptionSelected = { selectedGenderPelapor = it }, redColorUse)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "No. Telepon/Alamat email", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(4.dp))

        CustomTextField(value = noTelpOrEmail, onValueChange = { noTelpOrEmail = it }, label = "Masukkan No. Telepon/Alamat email")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Domisili", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(4.dp))

        ExposedDropdownMenuBox(
            expanded = expandedPelapor,
            onExpandedChange = { expandedPelapor = !expandedPelapor }
        ) {
            TextField(
                value = domisili,
                onValueChange = {},
                readOnly = true,
                label = { Text("Pilih Domisili") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPelapor) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.LightGray
                )
            )

            ExposedDropdownMenu(
                expanded = expandedPelapor,
                onDismissRequest = { expandedPelapor = false }
            ) {
                provinsiList.forEach { provinsi ->
                    DropdownMenuItem(
                        text = { Text(provinsi) },
                        onClick = {
                            domisili = provinsi
                            expandedPelapor = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        SectionHeader(text = "Status Disabilitas")

        Spacer(modifier = Modifier.height(12.dp))

        DisabilityStatusSelection(
            isDisabled = isDisabled,
            onStatusSelected = { status ->
                isDisabled = status
                isTextFieldEnabled = status == "Iya"
                if (status == "Tidak") disabilityExplanation = ""
            },
            redColorUse = redColorUse
        )

        Spacer(modifier = Modifier.height(12.dp))

        CustomTextField(
            value = disabilityExplanation,
            onValueChange = { disabilityExplanation = it },
            label = "Penjelasan Status Disabilitas",
            enabled = isTextFieldEnabled
        )

        Spacer(modifier = Modifier.height(18.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(Color.Black)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Nama Pelaku", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(4.dp))

        CustomTextField(value = namaTerduga, onValueChange = { namaTerduga = it }, label = "Masukkan Nama Terduga Pelaku")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Jenis Kelamin Pelaku", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(8.dp))

        GenderSelection(
            selectedOption = { selectedGenderTerduga },
            onOptionSelected = { selectedGenderTerduga = it },
            redColorUse = redColorUse
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Domisili Terduga Pelaku", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(8.dp))
        ExposedDropdownMenuBox(
            expanded = expandedTerduga,
            onExpandedChange = { expandedTerduga = !expandedTerduga }
        ) {
            TextField(
                value = domisiliTerduga,
                onValueChange = {},
                readOnly = true,
                label = { Text("Pilih Domisili Terduga") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTerduga) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.LightGray
                )
            )

            ExposedDropdownMenu(
                expanded = expandedTerduga,
                onDismissRequest = { expandedTerduga = false }
            ) {
                provinsiList.forEach { provinsi ->
                    DropdownMenuItem(
                        text = { Text(provinsi) },
                        onClick = {
                            domisiliTerduga = provinsi
                            expandedTerduga = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Bukti Laporan",
            fontSize = 20.sp,
            color = redColorUse,
            fontWeight = FontWeight.Bold
        )

        Text(text = "Kronologi Kejadian", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(4.dp))

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
            maxLines = 15,
            colors = TextFieldDefaults.colors(

                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray,
                disabledContainerColor = Color.LightGray,
                disabledTextColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Bukti Foto (Opsional)", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Kamu bisa menambahkan maksimal 3 foto bukti dari kejadian yang kamu lihat / alami.", fontSize = 13.sp, color = Color.Red)

        // Row function goes here to put image
        Spacer(modifier = Modifier.height(20.dp))

        ImagePickerRow()

        Spacer(modifier = Modifier.height(20.dp))



        Text(text = "Bukti Video (Opsional)", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Durasi video maksimal 10 menit.", fontSize = 14.sp, color = Color.Red)

        // Row function goes here to put video
        Spacer(modifier = Modifier.height(20.dp))

        VideoPickerRow()

    }
}

@Composable
fun CategoryReport(reportType: ReportType) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .size(width = 180.dp, height = 141.dp)
            .shadow(elevation = 7.dp, shape = RoundedCornerShape(8.dp))
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

@Composable
fun InfoCard(imageRes: Int, title: String, description: String) {
    Card(
        modifier = Modifier
            .size(width = 378.dp, height = 140.dp)
            .shadow(elevation = 7.dp, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(98.dp, 96.dp)
                    .padding(end = 16.dp)
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = title, style = MaterialTheme.typography.titleSmall, fontSize = 20.sp , fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = description, style = MaterialTheme.typography.bodySmall, fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailCaseReportPreview() {
    DetailCaseReport()
}