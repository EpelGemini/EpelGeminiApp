package com.epelgemini.epelgeminiapp


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondPhaseReportCases(
    onBackClicked: () -> Unit,
    onNext: () -> Unit
) {

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
    val redColorUse = Color(0xFFAF4646)
    val redLightColorUse = Color(0xFFF2CBC6)

    var namaTerduga by remember { mutableStateOf("") }
    var domisiliTerduga by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buat Laporan", color = Color.Black, fontWeight = FontWeight.Bold) },
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
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
        ) {
            ProgressBar(2,redColorUse, redLightColorUse)

            Spacer(modifier = Modifier.height(16.dp))

            SectionHeader(text = "Detail Informasi", color = redColorUse)

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

            ActionButtonInSecondPhase(
                onBackClicked = onBackClicked,
                onNext = onNext, redColorUse)
        }
    }
}
@Composable
fun ProgressBar(darkBoxCount: Int, redColorUse: Color, redLightColorUse: Color) {
    // Ensure darkBoxCount is within the range of the number of boxes
    val maxBoxes = 3
    val adjustedDarkBoxCount = darkBoxCount.coerceIn(0, maxBoxes)

    Row(
        modifier = Modifier
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        repeat(maxBoxes) { index ->
            val color = if (index < adjustedDarkBoxCount) redColorUse else redLightColorUse
            Box(
                modifier = Modifier
                    .size(121.dp, 12.dp)
                    .background(color = color, shape = RoundedCornerShape(12.dp))
            )
        }
    }
}

//@Composable
//fun ProgressBar(redColorUse: Color, redLightColorUse: Color) {
//    Row(
//        modifier = Modifier
//            .padding(vertical = 16.dp),
//        horizontalArrangement = Arrangement.spacedBy(4.dp),
//    ) {
//        Box(
//            modifier = Modifier
//                .size(121.dp, 12.dp)
//                .background(color = redColorUse, shape = RoundedCornerShape(12.dp))
//        )
//        Box(
//            modifier = Modifier
//                .size(121.dp, 12.dp)
//                .background(color = redColorUse, shape = RoundedCornerShape(12.dp))
//        )
//        Box(
//            modifier = Modifier
//                .size(121.dp, 12.dp)
//                .background(color = redLightColorUse, shape = RoundedCornerShape(12.dp))
//        )
//    }
//}

@Composable
fun SectionHeader(text: String, color: Color = Color.Black) {
    Text(
        text = text,
        fontSize = 20.sp,
        color = color,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, label: String, enabled: Boolean = true) {
//    TextField(
//        value = value,
//        onValueChange = onValueChange,
//        label = { Text(label) },
//        modifier = Modifier.fillMaxWidth(),
//        enabled = enabled,
//        colors = TextFieldDefaults.colors(
//            focusedContainerColor = Color.LightGray,
//            unfocusedContainerColor = Color.LightGray,
//            disabledContainerColor = Color.LightGray,
//            disabledTextColor = Color.Gray
//        )
//    )
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text =  label,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        textStyle = MaterialTheme.typography.bodyMedium.copy(lineHeight = 24.sp),
        singleLine = true,
        maxLines = 1,
        enabled = enabled,
        colors = TextFieldDefaults.colors(

            focusedContainerColor = Color.LightGray,
            unfocusedContainerColor = Color.LightGray,
            disabledContainerColor = Color.LightGray,
            disabledTextColor = Color.Gray
        )

    )


}

@Composable
fun GenderSelection(
    selectedOption: () -> String,
    onOptionSelected: (String) -> Unit,
    redColorUse: Color
) {
    val radioOptions = listOf("Laki-laki", "Perempuan")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        radioOptions.forEach { text ->
            Row(
                Modifier.selectable(
                    selected = (text == selectedOption()),
                    onClick = { onOptionSelected(text) }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption()),
                    onClick = { onOptionSelected(text) },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(selectedColor = redColorUse),
                    interactionSource = remember { MutableInteractionSource() }

                )
                Text(
                    text = text,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Composable
fun DisabilityStatusSelection(
    isDisabled: String?,
    onStatusSelected: (String) -> Unit,
    redColorUse: Color
) {
    val options = listOf("Iya", "Tidak")
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        options.forEach { option ->
            Box(
                modifier = Modifier
                    .background(
                        color = if (isDisabled == option) redColorUse else Color.LightGray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { onStatusSelected(option) }
                    .padding(16.dp)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = option,
                    fontWeight = FontWeight.Bold,
                    color = if (isDisabled == option) Color.White else Color.Black,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun ActionButtonInSecondPhase(
    onBackClicked: () -> Unit,
    onNext: () -> Unit,
    redColorUse: Color
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Button(
            onClick = onNext,
            modifier = Modifier
                .width(380.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = redColorUse)
        ) {
            Text("Lanjut", color = Color.White)
        }

        OutlinedButton(
            onClick = onBackClicked,
            modifier = Modifier
                .width(380.dp)
                .height(48.dp)
        ) {
            Text("Kembali ke halaman sebelumnya", color = redColorUse)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SecondPhaseReportCasesPreview() {
    SecondPhaseReportCases(
        onBackClicked = {

        },
        onNext = {

        }
    )
}
