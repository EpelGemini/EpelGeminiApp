package com.epelgemini.epelgeminiapp

import android.widget.ToggleButton
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun secondPhaseReportCase () {
    var name by remember { mutableStateOf("") }
    var jenisKelamin by remember { mutableStateOf("Laki-Laki") }
    var noTelpOrEmail by remember { mutableStateOf("") }
    var domisili by remember { mutableStateOf("") }

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
    var expanded by remember { mutableStateOf(false) }
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
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(121.dp, 12.dp)
                    .background(color = redColorUse, shape = RoundedCornerShape(12.dp))
            )
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
        }
        Text(
            text = "Detail Informasi",
            fontSize = 20.sp,
            color = redColorUse,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

//        var showLabel by remember { mutableStateOf(true) }
//        var isFocused by remember { mutableStateOf(false) }

        TextField(
            value = name,
            onValueChange = {
                name = it
//                showLabel = it.isEmpty() && !isFocused
            },
            label = {
//                if (showLabel)
                    Text("Nama Pelapor") },
            modifier = Modifier.fillMaxWidth()
//                .onFocusChanged { focusState ->
//                isFocused = focusState.isFocused
//                if (focusState.isFocused) {
//                    showLabel = name.isEmpty() // Hide immediately on focus if empty
//                }}
                ,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray,

            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Jenis Kelamin",
            fontSize = 18.sp,
            color = Color.Black
            )
        Spacer(modifier = Modifier.height(12.dp))

        val radioOptions = listOf("Laki-laki" , "Perempuan")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(60.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            radioOptions.forEach{
                    text ->
                        Row (
                            Modifier
                                .selectable(
                                    selected = (text == selectedOption),
                                    onClick = {
                                        onOptionSelected(text)
                                    }
                                )


                        ){
                            val context = LocalContext.current
                            RadioButton(
                                selected = (text == selectedOption) ,
                                onClick = { onOptionSelected(text) },
                                enabled = true,
                                colors = RadioButtonDefaults.colors(
                                    redColorUse
                                ),
                                interactionSource = remember { MutableInteractionSource() }

                            )
                            Text(
                                text = text,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 16.dp),
                                fontWeight = FontWeight.Normal
                            )
                        }
            }


        }
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = noTelpOrEmail,
            onValueChange = { noTelpOrEmail = it },
            label = { Text("No Telp/Email") },
            modifier = Modifier.fillMaxWidth() ,
            colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.LightGray,
            unfocusedContainerColor = Color.LightGray,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        // sementara pake Text Field dlu ini dropDownnya masih error
//        ExposedDropdownMenuBox(
//            expanded = expanded,
//            onExpandedChange = { expanded = !expanded }
//        ){
//            TextField(
//                value = provinsiList,
//                onValueChange = {},
//                readOnly = true,
//                label = { Text("Provinsi Domisili") },
//                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .menuAnchor(),
//                colors = TextFieldDefaults.colors(
//                    focusedContainerColor = Color.LightGray,
//                    unfocusedContainerColor = Color.LightGray
//                )
//            )
//
//            ExposedDropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false }
//            ) {
//                provinsiList.forEach { provinsi ->
//                    DropdownMenuItem(
//                        text = { Text(provinsi) },
//                        onClick = {
//                            provinsiList = provinsi
//                            expanded = false
//                        }
//                    )
//                }
//            }
//        }
//        }
        TextField(
            value = domisili,
            onValueChange = { domisili = it },
            label = { Text("Domisili") },
            modifier = Modifier.fillMaxWidth() ,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray,
            )
        )




    }
}



@Preview(showBackground = true)
@Composable
private fun checkSecondPhase () {
    secondPhaseReportCase()
}
